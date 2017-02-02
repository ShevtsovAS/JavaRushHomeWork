package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        //напишите тут ваш код
        int[] num_array = new int[5];
        for (int i = 0; i < 5; i++) {
            num_array[i] = Integer.parseInt(reader.readLine());
        }
        sort(num_array);
/*
        for (int i = 0; i < num_array.length; i++) {
            System.out.println(num_array[i]);
        }
*/
        for (int N: num_array) {
            System.out.println(N);
        }
    }

    public static void sort (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int min_i = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    min_i = j;
                }
            }
            if (min_i != i) {
                int tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
    }
}
