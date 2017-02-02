package com.javarush.test.level12.lesson04.task05;

/* Три метода возвращают максимальное из двух переданных в него чисел
Написать public static методы: int max(int, int), long max (long, long), double max (double, double).
Каждый метод должен возвращать максимальное из двух переданных в него чисел.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    //Напишите тут ваши методы
    public static int max(int x, int y) {
        if (x>y) return x;
        return y;
    }

    public static long max(long x, long y) {
        if (x>y) return x;
        return y;
    }

    public static double max(double x, double y) {
        if (x>y) return x;
        return y;
    }

}
