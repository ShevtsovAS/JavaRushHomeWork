package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s_num1 = br.readLine();
        String s_num2 = br.readLine();
        String s_num3 = br.readLine();
        int N1 = Integer.parseInt(s_num1);
        int N2 = Integer.parseInt(s_num2);
        int N3 = Integer.parseInt(s_num3);
        sort(N1,N2,N3);
    }
    public static void sort(int a, int b, int c) {
        int n1,n2,n3;
        if (a>b&&a>c) {
            n1 = a;
            if (b>c) {
                n2 = b;
                n3 = c;
            }
            else {
                n2 = c;
                n3 = b;
            }
        }
        else if (b>c) {
            n1 = b;
            if (a>c) {
                n2 = a;
                n3 = c;
            }
            else {
                n2 = c;
                n3 = a;
            }
        }
        else {
            n1 = c;
            if (a>b) {
                n2 = a;
                n3 = b;
            }
            else {
                n2 = b;
                n3 = a;
            }
        }
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
    }
}
