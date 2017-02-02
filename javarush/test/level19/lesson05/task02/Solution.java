package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader file = new FileReader(reader.readLine());
        reader.close();

        String text = "";
        while (file.ready()) {
            text += (char) file.read();
        }
        file.close();

        String[] splitedText = text.replaceAll("[?!,.]", "").trim().split("\\s+");

        int count = 0;
        for (String s : splitedText) {
            if (s.toLowerCase().equals("world")) count++;
        }
        System.out.println(count);


    }
}
