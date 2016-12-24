package com.javarush.test.level08.lesson08.task01;

import java.util.HashSet;
import java.util.Set;

/* 20 слов на букву «Л»
Создать множество строк (Set<String>), занести в него 20 слов на букву «Л».
*/

public class Solution
{
    public static HashSet<String> createSet()
    {
        //напишите тут ваш код
        HashSet<String> setString = new HashSet<>();
        setString.add("Лера");
        setString.add("Лена");
        setString.add("Лампа");
        setString.add("Люди");
        setString.add("Лодка");
        setString.add("Леопард");
        setString.add("Лёха");
        setString.add("Леонардо");
        setString.add("Линия");
        setString.add("Лиса");
        setString.add("Лупа");
        setString.add("Лён");
        setString.add("Лекарство");
        setString.add("Луч");
        setString.add("Лама");
        setString.add("Лайка");
        setString.add("Лужа");
        setString.add("Лёжа");
        setString.add("Лежать");
        setString.add("Любовь");
        return setString;
    }
}
