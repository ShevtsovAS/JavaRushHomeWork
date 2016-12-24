package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file = new FileInputStream(reader.readLine());
        HashMap<Integer, Integer> bytes = new HashMap<>();
        ArrayList<Integer> rareBytes = new ArrayList<>();

        while (file.available() > 0) {
            int data = file.read();
            if (!bytes.containsKey(data)) bytes.put(data, 1);
            else {
                int value = bytes.get(data);
                bytes.put(data, ++value);
            }
        }
        file.close();

        for (Map.Entry<Integer, Integer> entry : bytes.entrySet()) {
            int value = entry.getValue();
            int key = entry.getKey();
            if (value == 1) rareBytes.add(key);
        }

        for (int i = 0; i < rareBytes.size(); i++) {
            System.out.print(rareBytes.get(i));
            System.out.print(i < (rareBytes.size() - 1) ? " " : "\n");
        }
    }
}
