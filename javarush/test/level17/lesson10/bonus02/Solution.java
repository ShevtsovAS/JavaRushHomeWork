package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        args = "-c Шевцов м 15/06/1985 Сидоров м 12/04/1987".replaceAll("\\s{2,}", " ").trim().split(" ");
//        args = "-i 0 5 1".replaceAll("\\s{2,}", " ").trim().split(" ");
        if (args.length == 0) {
            System.out.println("Необходимо ввести параметры!");
            return;
        }
        switch (args[0]) {
            case "-c":
                create(args);
                break;
            case "-u":
                update(args);
                break;
            case "-d":
                delete(args);
                break;
            case "-i":
                info(args);
                break;
            default:
                System.out.println("Неверный параметр: " + args[0]);
        }
    }

    private static void create(String[] args) throws ParseException {
        if (((args.length-1) % 3) != 0 ) {
            System.out.println("Неверное кол-во параметров!");
            return;
        }
        String name;
        String sex;
        Date bd = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        Boolean isArgsOk = true;
        for (int i = 1; i < args.length; i+=3) {
            name = args[i];
            sex = args[i+1].toLowerCase();
            try {
                bd = dateFormat.parse(args[i+2]);
            } catch (ParseException e) {
                System.out.println(String.format("Неверный формат даты у параметра №%d = %s", i+2, args[i+2]));
                System.out.println("Ожидаемый формат - дд/мм/гггг");
                isArgsOk = false;
            }
            if (!sex.equals("м") && !sex.equals("ж")) {
                System.out.println(String.format("Неверный параметр №%d = %s, должен принимать значение м/ж", i, args[i]));
                isArgsOk = false;
            }
            if (isArgsOk) {
                if (sex.equals("м")) allPeople.add(Person.createMale(name, bd));
                else allPeople.add(Person.createFemale(name, bd));
                System.out.print(allPeople.size()-1);
                System.out.print(((i+3) < args.length) ? " " : "\n");
            }
        }
/*
        args = "-i 0 1 2 3 4".replaceAll("\\s{2,}", " ").trim().split(" ");
        info(args);
        args = "-u 2 Шевцова ж 09/12/1985 3 Сидорова ж 15/05/1988".replaceAll("\\s{2,}", " ").trim().split(" ");
        update(args);
        args = "-i 0 1 2 3".replaceAll("\\s{2,}", " ").trim().split(" ");
        info(args);
        args = "-d 0 1".replaceAll("\\s{2,}", " ").trim().split(" ");
        delete(args);
        args = "-i 0 1 2 3".replaceAll("\\s{2,}", " ").trim().split(" ");
        info(args);
*/
    }
    private static void update(String[] args) throws ParseException {
        if (((args.length-1) % 4) != 0 ) {
            System.out.println("Неверное кол-во параметров!");
            return;
        }
        int id;
        String name;
        String sex;
        Date bd;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        for (int i = 1; i < args.length; i+=4) {
            try {
                id = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.out.println(String.format("Неверный параметр №%d = %s, не числовое значение!", i, args[i]));
                return;
            }
            if (id >= allPeople.size() || id < 0) {
                System.out.println(String.format("Неверный параметр №%d = %s, человека с данным индексом нет в базе данных", i, args[i]));
                return;
            }
            sex = args[i+2].toLowerCase();
            if (!sex.equals("м") && !sex.equals("ж")) {
                System.out.println(String.format("Неверный параметр №%d = %s, должен принимать значение м/ж", i+2, args[i+2]));
                return;
            }

            try {
                bd = dateFormat.parse(args[i+3]);
            } catch (ParseException e) {
                System.out.println(String.format("Неверный формат даты у параметра №%d = %s", i+3, args[i+3]));
                System.out.println("Ожидаемый формат - дд/мм/гггг");
                return;
            }
        }
        for (int i = 1; i < args.length; i+=4) {
            id = Integer.parseInt(args[i]);
            name = args[i+1];
            sex = args[i+2];
            bd = dateFormat.parse(args[i+3]);
            allPeople.get(id).setName(name);
            allPeople.get(id).setSex(sex.equals("м") ? Sex.MALE : Sex.FEMALE);
            allPeople.get(id).setBirthDay(bd);
        }
    }
    private static void delete(String[] args) {
        if (args.length < 2 ) {
            System.out.println("Неверное кол-во параметров!");
            return;
        }
        int id;
        for (int i = 1; i < args.length; i++) {
            try {
                id = Integer.parseInt(args[i]);
                if (id >= allPeople.size() || id < 0) {
                    System.out.println(String.format("Неверный параметр №%d = %s, человека с данным индексом нет в базе данных", i, args[i]));
                }
                else {
                    allPeople.get(id).setName(null);
                    allPeople.get(id).setSex(null);
                    allPeople.get(id).setBirthDay(null);
                }
            } catch (NumberFormatException e) {
                System.out.println(String.format("Неверный параметр №%d = %s, не числовое значение!", i, args[i]));
            }
        }
    }
    private static void info(String[] args) {
        if (args.length < 2 ) {
            System.out.println("Неверное кол-во параметров!");
            return;
        }
        int id;
        String name;
        String sex;
        Date bd;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (int i = 1; i < args.length; i++) {
            try {
                id = Integer.parseInt(args[i]);
                if (id >= allPeople.size() || id < 0) {
                    System.out.println(String.format("Неверный параметр №%d = %s, человека с данным индексом нет в базе данных", i, args[i]));
                }
                else {
                    name = allPeople.get(id).getName();
                    sex = allPeople.get(id).getSex() == Sex.MALE ? "м" : "ж";
                    bd = allPeople.get(id).getBirthDay();
                    if (bd != null) System.out.println(String.format("%s %s %s", name, sex, dateFormat.format(bd)));
                    else System.out.println(String.format("Объект с id=%d удалён!", id));
                }
            } catch (NumberFormatException e) {
                System.out.println(String.format("Неверный параметр №%d = %s, не числовое значение!", i, args[i]));
            }
        }
    }
}

