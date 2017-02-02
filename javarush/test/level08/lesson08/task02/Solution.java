package com.javarush.test.level08.lesson08.task02;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* Удалить все числа больше 10
Создать множество чисел(Set<Integer>), занести туда 20 различных чисел.
Удалить из множества все числа больше 10.
*/

public class Solution
{

    public static void main(String[] args) {
        for (int x: removeAllNumbersMoreThan10(createSet())) System.out.println(x);
    }

    public static HashSet<Integer> createSet()
    {
        //напишите тут ваш код
        HashSet<Integer> intSet = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            intSet.add(i+1);
        }
        return intSet;
    }

    public static HashSet<Integer> removeAllNumbersMoreThan10(HashSet<Integer> set)
    {
        //напишите тут ваш код
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer x = iterator.next();
            if (x > 10) iterator.remove();
        }
        return set;
    }
}
