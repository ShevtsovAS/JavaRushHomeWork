package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

        int charCount = 0;
        for (byte b : buffer) {
            if ((b >= 65 && b <= 90) || (b >= 97 && b <= 122)) charCount++;
        }
        System.out.println(charCount);
    }
}
