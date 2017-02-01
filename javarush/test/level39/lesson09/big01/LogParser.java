package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<String> fileLines;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        fileLines = getLinesList();
    }

    private List<String> getLinesList() {
        String[] files = logDir.toFile().list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".log");
            }
        });

        List<String> lines = new ArrayList<>();
        if (files == null) return lines;

        for (String file : files)
        {
            try
            {
                lines.addAll(Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return lines;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> ips = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String ip = columns[0];
            Date date = getDate(columns[2]);

            if (isMatchesToDate(date, after, before)) ips.add(ip);
        }

        return ips;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> ips = new HashSet<>();
        if (user == null) return ips;

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String ip = columns[0];
            Date date = getDate(columns[2]);
            String currentUser = columns[1];

            if (currentUser.equals(user) && isMatchesToDate(date, after, before)) ips.add(ip);
        }

        return ips;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> ips = new HashSet<>();
        if (event == null) return ips;

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String ip = columns[0];
            Date date = getDate(columns[2]);
            Event currentEvent = getEvent(columns[3]);

            if (currentEvent.equals(event) && isMatchesToDate(date, after, before)) ips.add(ip);
        }

        return ips;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> ips = new HashSet<>();
        if (status == null) return ips;

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String ip = columns[0];
            Date date = getDate(columns[2]);
            Status currentStatus = Status.valueOf(columns[4]);

            if (currentStatus.equals(status) && isMatchesToDate(date, after, before)) ips.add(ip);
        }

        return ips;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> users = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");
            String user = columns[1];
            users.add(user);
        }

        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String user = columns[1];
            Date date = getDate(columns[2]);

            if (isMatchesToDate(date, after, before)) users.add(user);
        }

        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentUser = columns[1];
            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (currentUser.equals(user) && isMatchesToDate(date, after, before)) events.add(event);
        }

        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> users = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentIp = columns[0];
            String user = columns[1];
            Date date = getDate(columns[2]);

            if (currentIp.equals(ip) && isMatchesToDate(date, after, before)) users.add(user);
        }

        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUsersForEvent(Event.LOGIN, after, before);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUsersForEvent(Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUsersForEvent(Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUsersForEvent(Event.SOLVE_TASK, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> users = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String user = columns[1];
            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (event.equals(Event.SOLVE_TASK)) {
                int currentTask = Integer.parseInt(columns[3].split("\\s")[1]);
                if (currentTask == task && isMatchesToDate(date, after, before)) users.add(user);
            }
        }

        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUsersForEvent(Event.DONE_TASK, after, before);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> users = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String user = columns[1];
            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (event.equals(Event.DONE_TASK)) {
                int currentTask = Integer.parseInt(columns[3].split("\\s")[1]);
                if (currentTask == task && isMatchesToDate(date, after, before)) users.add(user);
            }
        }

        return users;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentUser = columns[1];
            Date date = getDate(columns[2]);
            Event currentEvent = getEvent(columns[3]);

            if (currentUser.equals(user) && currentEvent.equals(event) && isMatchesToDate(date, after, before)) dates.add(date);
        }

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getDatesForStatus(Status.FAILED, after, before);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getDatesForStatus(Status.ERROR, after, before);
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date logedFirstTimeDate = null;

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentUser = columns[1];
            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (currentUser.equals(user) && event.equals(Event.LOGIN) && isMatchesToDate(date, after, before)) {
                if (logedFirstTimeDate == null) logedFirstTimeDate = date;
                else if (logedFirstTimeDate.getTime() > date.getTime()) logedFirstTimeDate = date;
            }
        }

        return logedFirstTimeDate;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentUser = columns[1];
            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (currentUser.equals(user) && isMatchesToDate(date, after, before)) {
                if (event.equals(Event.SOLVE_TASK)) {
                    int currentTask = Integer.parseInt(columns[3].split("\\s")[1]);
                    if (currentTask == task) return date;
                }
            }
        }
        return null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentUser = columns[1];
            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (currentUser.equals(user) && isMatchesToDate(date, after, before)) {
                if (event.equals(Event.DONE_TASK)) {
                    int currentTask = Integer.parseInt(columns[3].split("\\s")[1]);
                    if (currentTask == task) return date;
                }
            }
        }
        return null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getDatesForUserAndEvent(user, Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getDatesForUserAndEvent(user, Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (isMatchesToDate(date, after, before)) events.add(event);
        }

        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> events = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentIp = columns[0];
            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (currentIp.equals(ip) && isMatchesToDate(date, after, before)) {
                events.add(event);
            }
        }

        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentUser = columns[1];
            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (currentUser.equals(user) && isMatchesToDate(date, after, before)) {
                events.add(event);
            }
        }

        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getEventsForStatus(Status.FAILED, after, before);
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getEventsForStatus(Status.ERROR, after, before);
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        Map<Integer, Integer> solvedTasks = getAllSolvedTasksAndTheirNumber(after, before);
        if (solvedTasks != null && solvedTasks.containsKey(task)) return solvedTasks.get(task);
        else return 0;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        Map<Integer, Integer> doneTasks = getAllDoneTasksAndTheirNumber(after, before);
        if (doneTasks != null && doneTasks.containsKey(task)) return doneTasks.get(task);
        else return 0;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> solvedTasks = new HashMap<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (event.equals(Event.SOLVE_TASK) && isMatchesToDate(date, after, before)) {
                int task = Integer.parseInt(columns[3].split("\\s")[1]);
                if (!solvedTasks.containsKey(task)) solvedTasks.put(task, 1);
                else solvedTasks.put(task, solvedTasks.get(task) + 1);
            }
        }

        return solvedTasks;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> solvedTasks = new HashMap<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (event.equals(Event.DONE_TASK) && isMatchesToDate(date, after, before)) {
                int task = Integer.parseInt(columns[3].split("\\s")[1]);
                if (!solvedTasks.containsKey(task)) solvedTasks.put(task, 1);
                else solvedTasks.put(task, solvedTasks.get(task) + 1);
            }
        }

        return solvedTasks;
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> objects = new HashSet<>();

        switch (query) {
            case "get ip": objects.addAll(getUniqueIPs(null, null)); return objects;
            case "get user": objects.addAll(getAllUsers()); return objects;
            case "get date": objects.addAll(getAllDates()); return objects;
            case "get event": objects.addAll(getAllEvents(null, null)); return objects;
            case "get status": objects.addAll(getAllStatus()); return objects;
        }

        if (!isQueryValid(query)) {
            System.out.println("Query is invalid!");
            return null;
        }

        String[] vars = query.split("\\s");
        String field1 = "";
        String field2 = "";
        String value1;

        if (vars.length >= 2) field1 = vars[1];
        if (vars.length >= 6) field2 = vars[3];

        List<String> values = new ArrayList<>();
        Pattern valuePattern = Pattern.compile("\".+?\"");
        Matcher valueMatcher = valuePattern.matcher(query);

        while (valueMatcher.find()) values.add(valueMatcher.group().replaceAll("\"", ""));

        if (values.isEmpty()) {
            System.out.println("Query is invalid!");
            return null;
        }

        value1 = values.get(0);

        if (field1.equals("ip")) {
            switch (field2) {
                case "user":
                    objects.addAll(getIPsForUser(value1, null, null));
                    break;
                case "date":
                    Date date = getDate(value1);
                    objects.addAll(getUniqueIPs(date, date));
                    break;
                case "event":
                    objects.addAll(getIPsForEvent(getEvent(value1), null, null));
                    break;
                case "status":
                    Status status = Status.valueOf(value1);
                    objects.addAll(getIPsForStatus(status, null, null));
                    break;
            }
        }

        if (field1.equals("user")) {
            switch (field2) {
                case "ip":
                    objects.addAll(getUsersForIP(value1, null, null));
                    break;
                case "date":
                    Date date = getDate(value1);
                    objects.addAll(getUsersForDate(date));
                    break;
                case "event":
                    Event event = getEvent(value1);
                    objects.addAll(getUsersForEvent(event, null, null));
                    break;
                case "status":
                    Status status = Status.valueOf(value1);
                    objects.addAll(getUsersForStatus(status));
                    break;
            }
        }

        if (field1.equals("date")) {
            switch (field2) {
                case "ip":
                    objects.addAll(getDatesForIp(value1));
                    break;
                case "user":
                    Event[] events = Event.values();
                    for (Event event : events) {
                        objects.addAll(getDatesForUserAndEvent(value1, event, null, null));
                    }
                    break;
                case "event":
                    Set<String> users = getAllUsers();
                    Event event = getEvent(value1);
                    for (String user : users) {
                        objects.addAll(getDatesForUserAndEvent(user, event, null, null));
                    }
                    break;
                case "status":
                    Status status = Status.valueOf(value1);
                    objects.addAll(getDatesForStatus(status, null, null));
                    break;
            }
        }

        if (field1.equals("event")) {
            switch (field2) {
                case "ip": objects.addAll(getEventsForIP(value1, null, null)); break;
                case "user": objects.addAll(getEventsForUser(value1, null, null)); break;
                case "date":
                    Date date = getDate(value1);
                    objects.addAll(getEventsForDate(date));
                    break;
                case "status": objects.addAll(getEventsForStatus(Status.valueOf(value1), null, null));
            }
        }

        if (field1.equals("status")) {
            switch (field2) {
                case "ip": objects.addAll(getStatusesForIp(value1)); break;
                case "user": objects.addAll(getStatusesForUser(value1)); break;
                case "date": objects.addAll(getStatusesForDate(getDate(value1))); break;
                case "event": objects.addAll(getStatusesForEvent(getEvent(value1))); break;
            }
        }

        return objects;
    }

    private Date getDate(String part) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try
        {
            date = dateFormat.parse(part);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    private boolean isMatchesToDate(Date date, Date after, Date before) {
        if (date == null) return false;
        if (after != null && date.getTime() < after.getTime()) return false;
        if (before != null && date.getTime() > before.getTime()) return false;
        return true;
    }

    private Event getEvent(String s) {
        if (s.startsWith("SOLVE_TASK") || s.startsWith("DONE_TASK")) return Event.valueOf(s.split("\\s")[0]);
        return Event.valueOf(s);
    }

    private Set<String> getUsersForEvent(Event event, Date after, Date before) {
        Set<String> users = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String user = columns[1];
            Date date = getDate(columns[2]);
            Event currentEvent = getEvent(columns[3]);

            if (currentEvent == event && isMatchesToDate(date, after, before)) users.add(user);
        }

        return users;
    }

    private Set<Date> getDatesForStatus(Status status, Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            Status currentStatus = Status.valueOf(columns[4]);
            Date date = getDate(columns[2]);

            if (currentStatus.equals(status) && isMatchesToDate(date, after, before)) dates.add(date);
        }

        return dates;
    }

    private Set<Event> getEventsForStatus(Status status, Date after, Date before) {
        Set<Event> events = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            Date date = getDate(columns[2]);
            Event event = getEvent(columns[3]);
            Status currentStatus = Status.valueOf(columns[4]);

            if (currentStatus.equals(status) && isMatchesToDate(date, after, before)) {
                events.add(event);
            }
        }

        return events;
    }

    private Set<Date> getAllDates() {
        Set<Date> dates = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            dates.add(getDate(columns[2]));
        }
        return dates;
    }

    private Set<Status> getAllStatus() {
        Set<Status> statuses = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            statuses.add(Status.valueOf(columns[4]));
        }

        return statuses;
    }

    private boolean isQueryValid(String query) {
        String[] vars = query.split("\\s");
        if (vars.length == 2) return vars[0].equals("get");
        if (vars.length >= 6) return vars[0].equals("get") && vars[2].equals("for");
        return false;
    }

    private Set<String> getUsersForDate(Date date) {
        Set<String> users = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String user = columns[1];
            Date currentDate = getDate(columns[2]);

            if (currentDate.getTime() == date.getTime()) users.add(user);
        }

        return users;
    }

    private Set<String> getUsersForStatus(Status status) {
        Set<String> users = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String user = columns[1];
            Status currentStatus = Status.valueOf(columns[4]);

            if (currentStatus.equals(status)) users.add(user);
        }

        return users;
    }

    private Set<Date> getDatesForIp(String ip) {
        Set<Date> dates = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentIp = columns[0];
            Date currentDate = getDate(columns[2]);

            if (currentIp.equals(ip)) dates.add(currentDate);
        }

        return dates;
    }

    private Set<Event> getEventsForDate(Date date) {
        Set<Event> events = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            Date currentDate = getDate(columns[2]);
            Event event = getEvent(columns[3]);

            if (currentDate.getTime() == date.getTime()) events.add(event);
        }

        return events;
    }

    private Set<Status> getStatusesForIp(String ip) {
        Set<Status> statuses = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentIp = columns[0];
            Status status = Status.valueOf(columns[4]);

            if (currentIp.equals(ip)) statuses.add(status);
        }

        return statuses;
    }

    private Set<Status> getStatusesForUser(String user) {
        Set<Status> statuses = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            String currentUser = columns[1];
            Status status = Status.valueOf(columns[4]);

            if (currentUser.equals(user)) {
                statuses.add(status);
            }
        }

        return statuses;
    }

    private Set<Status> getStatusesForDate(Date date) {
        Set<Status> statuses = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            Date currentDate = getDate(columns[2]);
            Status status = Status.valueOf(columns[4]);

            if (currentDate.getTime() == date.getTime()) statuses.add(status);
        }

        return statuses;
    }

    private Set<Status> getStatusesForEvent(Event event) {
        Set<Status> statuses = new HashSet<>();

        for (String line : fileLines) {
            String[] columns = line.trim().split("\\t");

            if (columns.length < 5) continue;

            Event currentEvent = getEvent(columns[3]);
            Status status = Status.valueOf(columns[4]);

            if (currentEvent.equals(event)) statuses.add(status);
        }

        return statuses;
    }
}
