package com.javarush.test.level04.lesson07.task01;

/* Строка - описание
Ввести с клавиатуры целое число. Вывести на экран его строку-описание следующего вида:
«отрицательное четное число» - если число отрицательное и четное,
«отрицательное нечетное число» - если число отрицательное и нечетное,
«нулевое число» - если число равно 0,
«положительное четное число» - если число положительное и четное,
«положительное нечетное число» - если число положительное и нечетное.
Пример для числа 100:
положительное четное число
Пример для числа -51:
отрицательное нечетное число
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean positive = N > 0;
        boolean negative = N < 0;
        boolean zero = N == 0;
        boolean even = N%2==0;
        boolean odd = N%2!=0;
        boolean type1 = negative&&even;
        boolean type2 = negative&&odd;
        boolean type3 = zero;
        boolean type4 = positive&&even;
        boolean type5 = positive&&odd;
        if (type1)
            System.out.println("отрицательное четное число");
        if (type2)
            System.out.println("отрицательное нечетное число");
        if (type3)
            System.out.println("нулевое число");
        if (type4)
            System.out.println("положительное четное число");
        if (type5)
            System.out.println("положительное нечетное число");
    }
}
