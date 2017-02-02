package com.javarush.test.level14.lesson08.bonus01;

import java.util.*;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //Add your code here
        exceptions.add(new RuntimeException());
        exceptions.add(new ConcurrentModificationException());
        exceptions.add(new EmptyStackException());
        exceptions.add(new FormatFlagsConversionMismatchException("2", 'a'));
        exceptions.add(new FormatterClosedException());
        exceptions.add(new IllegalFormatCodePointException(1));
        exceptions.add(new IllegalFormatWidthException(2));
        exceptions.add(new InputMismatchException());
        exceptions.add(new ArithmeticException());
//        exceptions.add(new IllegalFormatPrecisionException(5));
    }
}
