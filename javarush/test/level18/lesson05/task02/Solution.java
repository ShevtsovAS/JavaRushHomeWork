package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки. Не использовать try-with-resources

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file = new FileInputStream(console.readLine());
        console.close();
        byte[] buffer = new byte[file.available()];
        file.read(buffer);
        file.close();
        int count = 0;
        for (byte b : buffer) {
            if (b == 44) count++;
        }
        System.out.println(count);
    }
}
