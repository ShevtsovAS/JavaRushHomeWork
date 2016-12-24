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

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        String myArgs = "-c Шевцов м 15/06/1985".replaceAll("\\s{2,}", " ").trim();
        args = myArgs.split(" ");
        if (args.length == 0) {
            System.out.println("Программа запускается с одним из следующих наборов параметров:");
            System.out.println("-c name sex bd");
            System.out.println("-u id name sex bd");
            System.out.println("-d id");
            System.out.println("-i id");
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        Date date = dateFormat.parse("15/06/1985");
        System.out.println(dateFormat.format(date));
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.println(dateFormat.format(date));
/*
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
                System.out.println("Неверный параметр:" + args[0]);
        }
*/
    }
}
