package com.javarush.test.level10.lesson11.home08;

import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        //напишите тут ваш код
        ArrayList<String>[] result = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            ArrayList<String> s = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                String str = "Строка " + (j+1) + " в массиве " + (i + 1);
                s.add(j, str);
            }
            result[i] = s;
        }
        return result;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}