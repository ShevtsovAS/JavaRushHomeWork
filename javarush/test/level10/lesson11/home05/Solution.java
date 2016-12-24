package com.javarush.test.level10.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Количество букв
Ввести с клавиатуры 10 строчек и подсчитать в них количество различных букв (для 33 букв алфавита).  Вывести результат на экран.
Пример вывода:
а 5
б 8
в 3
г 7
д 0
…
я 9
*/

public class Solution
{
    public static void main(String[] args)  throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //алфавит
        String abc = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        char[] abcArray = abc.toCharArray();

        ArrayList<Character> alphabet = new ArrayList<>();
        for (char ch : abcArray) {
            alphabet.add(ch);
        }

        //ввод строк
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }


        //напишите тут ваш код
        for (char ch: alphabet) {
            int count = 0;
            for (String s: list) {
                count += countChar(s, ch);
            }
            System.out.println(ch + " " + count);
        }
    }


    public static int countChar(String s, char ch) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) result++;
        }
        return result;
    }
}
