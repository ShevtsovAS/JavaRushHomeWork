package com.javarush.test.level08.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Введи с клавиатуры 20 слов и выведи их в алфавитном порядке.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] array = new String[20];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = reader.readLine();
        }

        sort(array);

        for (String x : array)
        {
            System.out.println(x);
        }
    }

    public static void sort(String[] array)
    {
        //напишите тут ваш код
        for (int i = 0; i < array.length; i++) {
            String lessString = array[i];
            int lessString_i = i;
            for (int j = i + 1; j < array.length; j++) {
                if (isGreaterThan(lessString, array[j])){
                    lessString = array[j];
                    lessString_i = j;
                }
            }
            if (lessString_i != i){
                String tmp = array[i];
                array[i] = array[lessString_i];
                array[lessString_i] = tmp;
            }
        }
    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b)
    {
        return a.compareTo(b) > 0;
    }
}
