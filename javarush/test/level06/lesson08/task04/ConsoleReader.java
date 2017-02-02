package com.javarush.test.level06.lesson08.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

/* Класс ConsoleReader
Сделать класс ConsoleReader, у которого будут 4 статических метода:
String readString() – читает с клавиатуры строку
int readInt() – читает с клавиатуры число
double readDouble() – читает с клавиатуры дробное число
boolean readBoolean() – читает с клавиатуры строку "true" или "false" и возвращает соответствующую логическую переменную true или false
Внимание: создавайте переменную для чтения данных с консоли (BufferedReader или Scanner) внутри каждого метода
*/

public class ConsoleReader
{
    public static void main(String[] args) {

    }
    public static String readString() throws Exception
    {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static int readInt() throws Exception
    {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(br.readLine());
    }

    public static double readDouble() throws Exception
    {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return Double.parseDouble(br.readLine());
    }

    public static boolean readBoolean() throws Exception
    {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s.equals("true") || s.equals("false"))
            return Boolean.parseBoolean(s);
        else
            return false;
    }
}
