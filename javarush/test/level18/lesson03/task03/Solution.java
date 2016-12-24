package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName);
        HashMap<Integer, Integer> bytesMap = new HashMap<>();
        int max_value = 0;
        int keyWithMaxValue = 0;
        while (inputStream.available() > 0 ) {
            int data = inputStream.read();
            if (bytesMap.containsKey(data)) {
                int value = bytesMap.get(data);
                if (++value > max_value) {
                    max_value = value;
                    keyWithMaxValue = data;
                }
                bytesMap.put(data, value);
            }
            else bytesMap.put(data, 1);
        }
        inputStream.close();

        ArrayList<Integer> MaxValues = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : bytesMap.entrySet()) {
            if (entry.getValue() == max_value) MaxValues.add(entry.getKey());
        }

        if (max_value > 1) {
            for (Integer value : MaxValues) {
                System.out.print(value + " ");
            }
        }
    }
}
