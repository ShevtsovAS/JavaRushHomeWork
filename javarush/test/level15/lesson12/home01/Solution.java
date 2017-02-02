package com.javarush.test.level15.lesson12.home01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Разные методы для разных типов
1. Считать с консоли данные, пока не введено слово "exit".
2. Для каждого значения, кроме "exit", вызвать метод print. Если значение:
2.1. содержит точку '.', то вызвать метод print для Double;
2.2. больше нуля, но меньше 128, то вызвать метод print для short;
2.3. больше либо равно 128, то вызвать метод print для Integer;
2.4. иначе, вызвать метод print для String.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while (!(str = reader.readLine()).equals("exit")) {
            if (!str.equals("")) {
                str = str.trim();
                if (str.matches("\\d*")) {
                    int N = Integer.parseInt(str);
                    if (N > 0 && N < 128) print((short)N);
                    else if (N >= 128) print(N);
                }
                else if (isDouble(str)) print(Double.parseDouble(str));
                else print(str);
            }
        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }

    public static boolean isDouble(String str) {
        char[] simbols = str.toCharArray();
        int pointCount = 0;
        for (char c : simbols) {
            if (c == '.') pointCount++;
        }
        if (str.charAt(0) == '.' || str.charAt(str.length()-1) == '.') return false;
        if (pointCount != 1) return false;
        if (!str.matches("^\\S+$")) return false;
        return true;
    }
}
