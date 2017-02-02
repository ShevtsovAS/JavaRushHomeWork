package com.javarush.test.level32.lesson02.task01;

import java.io.IOException;
import java.io.RandomAccessFile;

/* Запись в файл
В метод main приходят три параметра:
1) fileName - путь к файлу
2) number - число, позиция в файле
3) text - текст
Записать text в файл fileName начиная с позиции number.
Если файл слишком короткий, то записать в конец файла.
*/
public class Solution {
    public static void main(String... args) throws IOException {
        if (args.length < 3) {
            System.out.println("Неверное кол-во параметров.");
            return;
        }

        RandomAccessFile file = new RandomAccessFile(args[0], "rw");
        long n = Long.parseLong(args[1]);
        if (n > file.length())
            n = file.length();
        file.seek(n);
        file.writeBytes(args[2]);
    }
}
