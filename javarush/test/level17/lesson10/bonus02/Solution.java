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
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
//        args = "-c Шевцов м 15/06/1985 Сидоров м 12/12/1987".replaceAll("\\s{2,}", " ").trim().split(" ");
        if (args.length == 0) {
            System.out.println("Неверные параметры!");
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

    private static synchronized void create(String[] args) {
        // Проверка количества введённых аргументов
        if ((args.length-1)%3 != 0) {
            System.out.println("Неверное число параметров!");
            return;
        }

        String name;
        String sex;
        Date bd = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Boolean isArgsOK = true;

        for (int i = 1; i < args.length; i+=3) {
            name = args[i];
            sex = args[i+1].toLowerCase();
            try {
                bd = dateFormat.parse(args[i+2]);
            } catch (ParseException e) {
                isArgsOK = false;
            }
            if (!sex.equals("м") && !sex.equals("ж")) isArgsOK = false;
            if (isArgsOK) {
                if (sex.equals("м")) allPeople.add(Person.createMale(name, bd));
                else allPeople.add(Person.createFemale(name, bd));
                System.out.println(allPeople.size()-1);
            }
            else System.out.println(String.format("Не удалось добавить %s, ошибка параметров!", name));
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
    private static synchronized void update(String[] args) {
        // Проверка количества введённых аргументов
        if ((args.length-1)%4 != 0) {
            System.out.println("Неверное число параметров!");
            return;
        }

        int id;
        String name;
        String sex;
        Date bd = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Boolean isArgsOK = true;
        Boolean idIsNotFound = false;

        for (int i = 1; i < args.length; i+=4) {
            try {
                id = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                id = -1;
            }

            name = args[i+1];
            sex = args[i+2].toLowerCase();

            try {
                bd = dateFormat.parse(args[i+3]);
            } catch (ParseException e) {
                isArgsOK = false;
            }

            if (id >= allPeople.size() || id < 0) {
                idIsNotFound = true;
                isArgsOK = false;
            }
            if (!sex.equals("м") && !sex.equals("ж")) isArgsOK = false;

            if (isArgsOK) {
                allPeople.get(id).setName(name);
                allPeople.get(id).setSex(sex.equals("м") ? Sex.MALE : Sex.FEMALE);
                allPeople.get(id).setBirthDay(bd);
            }
            else if (idIsNotFound) System.out.println(String.format("ID %s не найден!", args[i]));
            else System.out.println(String.format("Не удалось обновить ID №%d, ошибка параметров!", id));
        }
    }
    private static synchronized void delete(String[] args) {
        if (args.length < 2) {
            System.out.println("Неверное число параметров!");
            return;
        }

        int id;

        for (int i = 1; i < args.length; i++) {
            try {
                id = Integer.parseInt(args[i]);
                if (id >= allPeople.size() || id < 0) System.out.println(String.format("ID %s не найден!", args[i]));
                else {
                    allPeople.get(id).setName(null);
                    allPeople.get(id).setSex(null);
                    allPeople.get(id).setBirthDay(null);
                }
            } catch (NumberFormatException e) {
                System.out.println("неверный параметр: " + args[i]);
            }
        }
    }
    private static void info(String[] args) {
        if (args.length < 2) {
            System.out.println("Неверное число параметров!");
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
                if (id >= allPeople.size() || id < 0) System.out.println(String.format("ID %s не найден!", args[i]));
                else {
                    name = allPeople.get(id).getName();
                    sex = allPeople.get(id).getSex() == Sex.MALE ? "м" : "ж";
                    bd = allPeople.get(id).getBirthDay();
                    if (bd != null) System.out.println(String.format("%s %s %s", name, sex, dateFormat.format(bd)));
                    else System.out.println(String.format("Объект с id=%d удалён!", id));
                }
            } catch (NumberFormatException e) {
                System.out.println("неверный параметр: " + args[i]);
            }
        }
    }
}
