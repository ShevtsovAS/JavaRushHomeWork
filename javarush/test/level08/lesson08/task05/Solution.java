package com.javarush.test.level08.lesson08.task05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static void main(String[] args) {
        HashMap<String, String> namesMap = createMap();
        removeTheFirstNameDuplicates(namesMap);
        for (Map.Entry<String, String> pair: namesMap.entrySet()) System.out.println(pair.getKey());
    }
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String > map = new HashMap<>();
        map.put("Ivanov", "Alexandr");
        map.put("Petrov", "Sergey");
        map.put("Sidorov", "Daniil");
        map.put("Puchkov", "Alexandr");
        map.put("Monastirev", "dfgh");
        map.put("Botyaikin", "Anya");
        map.put("Sichev", "Anya");
        map.put("Veselov", "wer");
        map.put("Limarenko", "Anya");
        map.put("Shevcov", "Anya");
        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        //напишите тут ваш код
        ArrayList<String> namesList = new ArrayList<>();        // Список всех имён
        ArrayList<String> dublicatedNames = new ArrayList<>();  // Список имён дубликатов
        // Заполняем список всех имён
        for (Map.Entry<String, String> pair: map.entrySet()){
            namesList.add(pair.getValue());
        }
        // Ищем имена дубликаты
        for (int i = 0; i < namesList.size(); i++) {
            for (int j = i+1; j < namesList.size(); j++) {
                String name1 = namesList.get(i);
                String name2 = namesList.get(j);
                if (name1.equals(name2) && !dublicatedNames.contains(name1)) dublicatedNames.add(name1);
            }
        }
        // Удаляем все имена дубликаты из карты
        for (String name: dublicatedNames) removeItemFromMapByValue(map, name);
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
