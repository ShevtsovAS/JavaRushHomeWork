package com.javarush.test.level04.lesson06.task02;

/* Максимум четырех чисел
Ввести с клавиатуры четыре числа, и вывести максимальное из них.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s_num1 = br.readLine();
        String s_num2 = br.readLine();
        String s_num3 = br.readLine();
        String s_num4 = br.readLine();
        int N1 = Integer.parseInt(s_num1);
        int N2 = Integer.parseInt(s_num2);
        int N3 = Integer.parseInt(s_num3);
        int N4 = Integer.parseInt(s_num4);
        if (N1>N2&&N1>N3&&N1>N4)
            System.out.println(N1);
        else if (N2 > N3 && N2 > N4)
            System.out.println(N2);
        else if (N3 > N4)
            System.out.println(N3);
        else
            System.out.println(N4);
    }
}
