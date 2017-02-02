package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
//        String[] myArgs = new String[] {"myFile.txt"};
//        args = myArgs;
        if (args.length < 1) {
            System.out.println("Не указан имя файла!");
            System.exit(1);
        }
        if (!new File(args[0]).exists()) {
            System.out.println(String.format("Файл с имненем %s не найден", args[0]));
            System.exit(2);
        }

        FileInputStream file = new FileInputStream(args[0]);
        byte[] buffer = new byte[file.available()];
        file.read(buffer);
        file.close();

        int spaceCount = 0;
        for (byte b : buffer) {
            if (b == 32) spaceCount++;
        }

        double spaceRatio = (double) (spaceCount)/buffer.length*100;
        System.out.println(String.format("%8.2f", spaceRatio).replace(',', '.'));
    }
}
