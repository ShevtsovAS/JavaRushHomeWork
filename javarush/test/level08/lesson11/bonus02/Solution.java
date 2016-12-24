package com.javarush.test.level08.lesson11.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Нужно добавить в программу новую функциональность
Задача: Программа определяет, какая семья (фамилию) живёт в доме с указанным номером.
Новая задача: Программа должна работать не с номерами домов, а с городами:
Пример ввода:
Москва
Ивановы
Киев
Петровы
Лондон
Абрамовичи

Лондон

Пример вывода:
Абрамовичи
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
/*
        //list of addresses
        List<String> addresses = new ArrayList<>();
        while (true)
        {
            String family = reader.readLine();
            if (family.isEmpty()) break;

            addresses.add(family);
        }

        //read home number
        int houseNumber = Integer.parseInt(reader.readLine());

        if (0 <= houseNumber && houseNumber < addresses.size())
        {
            String familySecondName = addresses.get(houseNumber);
            System.out.println(familySecondName);
        }
*/

        //map of family
        HashMap<String, String> family = new HashMap<>();
        while (true){
            String lastName = reader.readLine();
            if (lastName.isEmpty()) break;
            String city = reader.readLine();
            if (city.isEmpty()) break;
            family.put(lastName, city);
        }

        //read city name
        String familyCity = reader.readLine();
        if (family.containsKey(familyCity)){
            for (Map.Entry<String, String> pair: family.entrySet()){
                String key = pair.getKey();
                if (key.equals(familyCity)) System.out.println(pair.getValue());
            }
        }
        else System.out.println("Family in " + familyCity + " were not found");
    }
}
