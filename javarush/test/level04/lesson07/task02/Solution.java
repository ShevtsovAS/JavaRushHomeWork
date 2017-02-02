package com.javarush.test.level04.lesson07.task02;

/* Строка - описание
Ввести с клавиатуры целое число в диапазоне 1 - 999. Вывести его строку-описание следующего вида:
«четное однозначное число» - если число четное и имеет одну цифру,
«нечетное однозначное число» - если число нечетное и имеет одну цифру,
«четное двузначное число» - если число четное и имеет две цифры,
«нечетное двузначное число» - если число нечетное и имеет две цифры,
«четное трехзначное число» - если число четное и имеет три цифры,
«нечетное трехзначное число» - если число нечетное и имеет три цифры.
Если введенное число не попадает в диапазон 1 - 999, в таком случае ничего не выводить на экран.
Пример для числа 100:
четное трехзначное число
Пример для числа 51:
нечетное двузначное число
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean one_digit = N>0&&N<10;
        boolean two_digit = N>9&&N<100;
        boolean three_digit = N>99&&N<1000;
        boolean even = N%2==0;
        if (even&&one_digit)
            System.out.println("четное однозначное число");
        if (!even&&one_digit)
            System.out.println("нечетное однозначное число");
        if (even&&two_digit)
            System.out.println("четное двузначное число");
        if (!even&&two_digit)
            System.out.println("нечетное двузначное число");
        if (even&&three_digit)
            System.out.println("четное трехзначное число");
        if (!even&&three_digit)
            System.out.println("нечетное трехзначное число");
    }
}
