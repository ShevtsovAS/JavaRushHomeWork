package com.javarush.test.level04.lesson07.task04;

/* Положительные и отрицательные числа
Ввести с клавиатуры три целых числа. Вывести на экран количество положительных и количество отрицательных чисел в исходном наборе,
в следующем виде:
"количество отрицательных чисел: а", "количество положительных чисел: б", где а, б - искомые значения.
Пример для чисел 2 5 6:
количество отрицательных чисел: 0
количество положительных чисел: 3
Пример для чисел -2 -5 6:
количество отрицательных чисел: 2
количество положительных чисел: 1
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] N;
        N = new int[3];
        N[0] = Integer.parseInt(br.readLine());
        N[1] = Integer.parseInt(br.readLine());
        N[2] = Integer.parseInt(br.readLine());

        int positive_sum = 0;
        int negative_sum = 0;
        for (int i=0; i<3; i++)
            if (N[i]>0)
                positive_sum++;
            else if (N[i]<0)
                negative_sum++;
        System.out.println("количество отрицательных чисел: " + negative_sum);
        System.out.println("количество положительных чисел: " + positive_sum);
    }
}
