package com.javarush.test.level04.lesson07.task03;

/* Положительные числа
Ввести с клавиатуры три целых числа. Вывести на экран количество положительных чисел в исходном наборе.
Пример для чисел -4 6 6:
2
Пример для чисел -6 -6 -3:
0
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
        for (int i=0; i<3; i++)
            if (N[i]>0)
                positive_sum++;
        System.out.println(positive_sum);
    }
}
