package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static void main(String[] args) {
        HashMap<String, Date> people = createMap();
        removeAllSummerPeople(people);
        for (Map.Entry<String, Date> pair: people.entrySet()){
            System.out.println(pair.getKey());
        }
    }
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Бузова", new Date("JULY 22 1947"));
        map.put("Светлаков", new Date("AUGUST 28 1976"));
        map.put("Гончаров", new Date("SEPTEMBER 17 3100"));
        map.put("Кличко", new Date("NOVEMBER 2 2033"));
        map.put("Ли", new Date("DECEMBER 1 2011"));
        map.put("Капоне", new Date("JANUARY 10 1876"));
        map.put("Торчилетти", new Date("FEBRUARY 5 1555"));
        map.put("Гамлет", new Date("SEPTEMBER 24 1678"));
        map.put("Шевцов", new Date("JUNE 15 1985"));
        //напишите тут ваш код
        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        //напишите тут ваш код
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            int month = iterator.next().getValue().getMonth();
            if (month > 4 && month < 8) iterator.remove();
        }
    }
}
