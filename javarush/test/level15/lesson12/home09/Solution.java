package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
        // Получаем url из консоли
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        reader.close();

        ArrayList<String> paramList = new ArrayList<>();
        ArrayList<String> objList = new ArrayList<>();

        // Формируем список параметров
        Collections.addAll(paramList, url.substring(url.indexOf('?') + 1).split("&"));

        // Формируем список параметров obj
        for (String s : paramList) {
            if (s.startsWith("obj=")) objList.add(s);
        }

        // Выводим результат на экран
        for (int i = 0; i < paramList.size(); i++) {
            String[] splitParam = paramList.get(i).split("=");
            if (i < paramList.size()-1) System.out.print(splitParam[0]+" ");
            else System.out.println(splitParam[0]);
        }
        for (String s : objList) {
            String[] splitObj = s.split("=");
            try {
                alert(Double.parseDouble(splitObj[1]));
            } catch (NumberFormatException e) {
                alert(splitObj[1]);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
