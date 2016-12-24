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

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String key = reader.readLine();
        ArrayList<String> urlParamList = getURLParam(key);
        print(urlParamList);
    }

    public static void alert(double value) { System.out.println("double " + value); }
    public static void alert(String value) {
        System.out.println("String " + value);
    }
    private static ArrayList<String> getURLParam(String s) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        int j;
        while (true) {
            if (i == 0) {
                i = s.indexOf('?');
                j = s.indexOf('&');
            }
            else {
                i = s.indexOf('&', i+1);
                j = s.indexOf('&', i+1);
            }
            if (i == -1) break;
            if (j != -1) {list.add(s.substring(i+1, j));}
            else {list.add(s.substring(i+1, s.length()));}
        }
        return list;
    }
    private static void print(ArrayList<String> list) {
        int i;
        ArrayList<String> obj = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            i = list.get(j).indexOf('=');
            if (i != -1) {
                System.out.print(list.get(j).substring(0,i));
                if (list.get(j).contains("obj")) obj.add(list.get(j).substring(i+1));
            }
            else System.out.print(list.get(j));
            if (j != list.size() - 1) System.out.print(" ");
            else System.out.println();
        }
        for (String s : obj) {
            if (isDouble(s)) alert(Double.parseDouble(s));
            else alert(s);
        }
    }
    private static boolean isDouble(String str) {
        char[] simbols = str.toCharArray();
        int pointCount = 0;
        for (char c : simbols) {
            if (c == '.') pointCount++;
        }
        return !(str.charAt(0) == '.' || str.charAt(str.length() - 1) == '.') && pointCount == 1 && str.matches("^\\S+$");
    }
}
