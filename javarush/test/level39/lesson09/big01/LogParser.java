package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
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

            if (isMatchesToDateFilter(date, after, before)) ips.add(ip);
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

            if (currentUser.equals(user) && isMatchesToDateFilter(date, after, before)) ips.add(ip);
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

            if (currentEvent.equals(event) && isMatchesToDateFilter(date, after, before)) ips.add(ip);
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

            if (currentStatus.equals(status) && isMatchesToDateFilter(date, after, before)) ips.add(ip);
        }

        return ips;
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

    private boolean isMatchesToDateFilter(Date date, Date after, Date before) {
        if (date == null) return false;
        if (after != null && date.getTime() < after.getTime()) return false;
        if (before != null && date.getTime() > before.getTime()) return false;
        return true;
    }

    private Event getEvent(String s) {
        if (s.startsWith("SOLVE_TASK") || s.startsWith("DONE_TASK")) return Event.valueOf(s.split("\\s")[0]);
        return Event.valueOf(s);
    }
}
