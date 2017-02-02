package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
//        args = "-i 0".replaceAll("\\s{2,}", " ").trim().split(" ");
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

    private static void create(String[] args) {
        if (args.length != 4) {
            System.out.println("Неверное число параметров!");
            return;
        }
        String name = args[1];
        String sex = args[2].toLowerCase();
        Date bd;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        // Проверяем указанный пол
        if (!sex.equals("м") && !sex.equals("ж")) {
            System.out.println("неверный параметр: " + args[2]);
            return;
        }
        // Проверяем указанную дату рождения
        try {
            bd = dateFormat.parse(args[3]);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты: " + args[3]);
            System.out.println("Ожидаемый формат - дд/мм/гггг");
            return;
        }
        // Добавляем в базу нового человека
        if (args[2].equals("м")) allPeople.add(Person.createMale(name, bd));
        else allPeople.add(Person.createFemale(name, bd));
        System.out.println(allPeople.size()-1);
    }
    private static void update(String[] args) {
        if (args.length != 5) {
            System.out.println("Неверное число параметров!");
            return;
        }
        int id;
        String name = args[2];
        String sex = args[3].toLowerCase();
        Date bd;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        // Проверяем указанный id
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("неверный параметр: " + args[1]);
            return;
        }
        if (id > (allPeople.size()-1) || id < 0) {
            System.out.println("Человека с указанным id нет в базе данных! (id:" + id + ")");
            return;
        }
        // Проверяем указанный пол
        if (!sex.equals("м") && !sex.equals("ж")) {
            System.out.println("неверный параметр: " + args[2]);
            return;
        }
        // Проверяем указанную дату рождения
        try {
            bd = dateFormat.parse(args[4]);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты: " + args[4]);
            System.out.println("Ожидаемый формат - дд/мм/гггг");
            return;
        }
        allPeople.get(id).setName(name);
        if (sex.equals("м")) allPeople.get(id).setSex(Sex.MALE);
        else allPeople.get(id).setSex(Sex.FEMALE);
        allPeople.get(id).setBirthDay(bd);
    }
    private static void delete(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное число параметров!");
            return;
        }
        int id;
        // Проверяем указанный id
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("неверный параметр: " + args[1]);
            return;
        }
        if (id > (allPeople.size()-1) || id < 0) {
            System.out.println("Человека с указанным id нет в базе данных! (id:" + id + ")");
            return;
        }
        allPeople.get(id).setName(null);
        allPeople.get(id).setSex(null);
        allPeople.get(id).setBirthDay(null);
    }
    private static void info(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное число параметров!");
            return;
        }
        int id;
        String name;
        String sex;
        Date bd;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        // Проверяем указанный id
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("неверный параметр: " + args[1]);
            return;
        }
        if (id > (allPeople.size()-1) || id < 0) {
            System.out.println("Человека с указанным id нет в базе данных! (id:" + id + ")");
            return;
        }
        // Получаем данные по указанному id
        name = allPeople.get(id).getName();
        sex = allPeople.get(id).getSex().equals(Sex.MALE) ? "м" : "ж";
        bd = allPeople.get(id).getBirthDay();
        // Выводим информацию на экран
        System.out.println(String.format("%s %s %s", name, sex, dateFormat.format(bd)));
    }
}
