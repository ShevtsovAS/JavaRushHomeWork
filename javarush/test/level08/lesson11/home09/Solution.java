package com.javarush.test.level08.lesson11.home09;

import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Не забудьте учесть первый день года.
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
        String date = "JANUARY 1 2000";
        System.out.println(date + " = " + isDateOdd(date));
    }

    public static boolean isDateOdd(String date)
    {
        Date someDate = new Date(date);
        Date startYear = new Date(date);
        startYear.setHours(0);
        startYear.setMinutes(0);
        startYear.setSeconds(0);
        startYear.setDate(1);
        startYear.setMonth(0);
        long msTimeDistance = someDate.getTime() - startYear.getTime();
        long msDay = 24 * 60 * 60 * 1000;
        int dayCount = (int) (msTimeDistance/msDay);
        if (dayCount % 2 == 0) return true;
        else return false;
    }
}
