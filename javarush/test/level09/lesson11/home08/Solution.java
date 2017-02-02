package com.javarush.test.level09.lesson11.home08;

import java.util.ArrayList;

/* Список из массивов чисел
Создать список, элементами которого будут массивы чисел. Добавить в список пять объектов–массивов длиной 5, 2, 4, 7, 0 соответственно. Заполнить массивы любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList()
    {
        //напишите тут ваш код
        ArrayList<int[]> result = new ArrayList<>();
        int[] n1 = new int[] {1, 2, 3, 4, 5};
        int[] n2 = new int[] {6, 7};
        int[] n3 = new int[] {8, 9, 10, 11};
        int[] n4 = new int[] {12, 13, 14, 15, 16, 17, 18};
        int[] n5 = new int[0];
        result.add(n1);
        result.add(n2);
        result.add(n3);
        result.add(n4);
        result.add(n5);
        return result;
    }

    public static void printList(ArrayList<int[]> list)
    {
        for (int[] array: list )
        {
            for (int x: array)
            {
                System.out.println(x);
            }
        }
    }
}
