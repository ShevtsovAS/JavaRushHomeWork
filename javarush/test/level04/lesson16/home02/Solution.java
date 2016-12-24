package com.javarush.test.level04.lesson16.home02;

import java.io.*;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

public class Solution
{
    public static void main(String[] args)   throws Exception {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N1 = Integer.parseInt(br.readLine());
        int N2 = Integer.parseInt(br.readLine());
        int N3 = Integer.parseInt(br.readLine());
        int MAX_N = max(N1, N2, N3);
        int MIN_N = min(N1, N2, N3);
        int[] N = new int[3];
        N[0] = N1;
        N[1] = N2;
        N[2] = N3;
        for (int i = 0; i < 3 ; i++) {
            if (N[i]!=MAX_N&&N[i]!=MIN_N)
                System.out.println(N[i]);
        }
    }
    public static int max(int a, int b, int c) {
        int[] N = new int[3];
        N[0] = a;
        N[1] = b;
        N[2] = c;
        int max_n = N[0];
        for (int i = 0; i < 3; i++)
            if (N[i] > max_n)
                max_n = N[i];
//        System.out.println("MAX_N = "+ max_n);
        return max_n;
    }
    public static int min(int a, int b, int c) {
        int[] N = new int[3];
        N[0] = a;
        N[1] = b;
        N[2] = c;
        int min_n = N[0];
        for (int i = 0; i < 3; i++)
            if (N[i] < min_n)
                min_n = N[i];
//        System.out.println("MIN_N = " + min_n);
        return min_n;
    }
}
