package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static void main(String[] args) {
        HashMap<String, String> peopleMap = createMap();
        System.out.println(getCountTheSameFirstName(peopleMap, "Alexandr"));
        System.out.println(getCountTheSameLastName(peopleMap, "E"));
    }

    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<String, String>();
        map.put("A", "Alexandr");
        map.put("B", "Sergey");
        map.put("C", "Daniil");
        map.put("D", "Alexandr");
        map.put("E", "Alexandr");
        map.put("F", "Anya");
        map.put("G", "Anya");
        map.put("H", "Anya");
        map.put("I", "Anya");
        map.put("J", "Anya");
        return (HashMap)map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        //напишите тут ваш код
        int count = 0;
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            if (name.equals(iterator.next().getValue())) count++;
        }
        return count;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName)
    {
        //напишите тут ваш код
        int count = 0;
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            if (lastName.equals(iterator.next().getKey())) count++;
        }
        return count;
    }
}
