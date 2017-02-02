package com.javarush.test.level04.lesson06.task01;

/* Минимум двух чисел
Ввести с клавиатуры два числа, и вывести на экран минимальное из них.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s_num1 = br.readLine();
        String s_num2 = br.readLine();
        int N1 = Integer.parseInt(s_num1);
        int N2 = Integer.parseInt(s_num2);
        if (N1 < N2)
            System.out.println(N1);
        else
            System.out.println(N2);
    }
}