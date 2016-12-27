package com.javarush.test.level19.lesson10.home03;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {
//        String[] myArgs = "myFile1.txt".trim().split("\\s+");
//        args = myArgs;

        String fileName = args[0];
        BufferedReader file = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = file.readLine()) != null) {
            String[] values = line.trim().split("\\s+");
            String name = "";
            String day = values[values.length - 3];
            String month = values[values.length - 2];
            String year = values[values.length -1];
            Date bd = new SimpleDateFormat("dd MM yyyy").parse(String.format("%s %s %s", day, month, year));
            for (int i = 0; i < values.length - 3; i++) {
                name += values[i] + " ";
            }
            name = name.trim();
            PEOPLE.add(new Person(name, bd));
        }
        file.close();

/*
        for (Person person : PEOPLE) {
            System.out.println(person.getName() + " " + person.getBirthday());
        }
*/
    }

}
