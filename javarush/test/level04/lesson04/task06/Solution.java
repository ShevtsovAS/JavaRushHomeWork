package com.javarush.test.level04.lesson04.task06;

/* День недели
Ввести с клавиатуры номер дня недели, в зависимости от номера вывести название «понедельник», «вторник», «среда», «четверг», «пятница», «суббота», «воскресенье»,
если введен номер больше или меньше 7 – вывести «такого дня недели не существует».
Пример для номера 5:
пятница
Пример для номера 10:
такого дня недели не существует
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s_num = reader.readLine();
        int N = Integer.parseInt(s_num);
        if (N==1)
            System.out.println("понедельник");
        else if (N==2)
            System.out.println("вторник");
        else if (N==3)
            System.out.println("среда");
        else if (N==4)
            System.out.println("четверг");
        else if (N==5)
            System.out.println("пятница");
        else if (N==6)
            System.out.println("суббота");
        else if (N==7)
            System.out.println("воскресенье");
        else
            System.out.println("такого дня недели не существует");
    }

}