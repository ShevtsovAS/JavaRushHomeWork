package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        //напишите тут ваш код
        char[] my_string = s.toCharArray();
        for (int i = 0; i < my_string.length; i++) {
            if (i > 0){
                if (my_string[i] != ' ' && my_string[i-1] == ' ') my_string[i] = Character.toUpperCase(my_string[i]);
            }
            else
                if (my_string[i] != ' ') my_string[i] = Character.toUpperCase(my_string[i]);
        }
        s = "";
        for (char ch: my_string) {
            s += ch;
        }
        System.out.println(s);
    }
}
