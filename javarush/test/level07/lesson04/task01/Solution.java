package com.javarush.test.level07.lesson04.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Максимальное среди массива на 20 чисел
1. В методе initializeArray():
1.1. Создайте массив на 20 чисел
1.2. Считайте с консоли 20 чисел и заполните ими массив
2. Метод max(int[] array) должен находить максимальное число из элементов массива
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int[] array = initializeArray();
        int max = max(array);
        System.out.println(max);
    }
    public static int[] initializeArray() throws IOException {
        //Инициализируйте (создайте и заполните) массив тут
        int[] int_array = new int[20];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < int_array.length; i++) {
            int_array[i] = Integer.parseInt(br.readLine());
        }
        return int_array;
    }

    public static int max(int[] array) {
        //Найдите максимальное значение в этом методе
        int max_N = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max_N)
                max_N = array[i];
        }
        return max_N;
    }
}
