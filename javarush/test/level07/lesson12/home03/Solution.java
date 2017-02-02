package com.javarush.test.level07.lesson12.home03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Максимальное и минимальное числа в массиве
Создать массив на 20 чисел. Заполнить его числами с клавиатуры. Найти максимальное и минимальное числа в массиве.
Вывести на экран максимальное и минимальное числа через пробел.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int  maximum;
        int  minimum;

        //напишите тут ваш код
        ArrayList<Integer> int_list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int_list.add(Integer.parseInt(reader.readLine()));
        }
        maximum = int_list.get(0);
        minimum = int_list.get(0);
        for (int i = 0; i < int_list.size(); i++) {
            int x = int_list.get(i);
            if (x > maximum) maximum = x;
            if (x < minimum) minimum = x;
        }

        System.out.println(maximum + " " + minimum);
//        System.out.println(minimum);
    }
}
