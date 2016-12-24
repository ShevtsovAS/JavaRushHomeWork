package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        byte[] buffer1;
        byte[] buffer2;
        FileInputStream inputStream1;
        FileInputStream inputStream2;
        FileOutputStream outputStream1;
        FileOutputStream outputStream2;

        inputStream1 = new FileInputStream(file1);
        buffer1 = new byte[inputStream1.available()];
        inputStream1.read(buffer1);
        inputStream1.close();

        inputStream2 = new FileInputStream(file2);
        buffer2 = new byte[inputStream2.available()];
        inputStream2.read(buffer2);
        inputStream2.close();

        outputStream1 = new FileOutputStream(file1);
        outputStream1.write(buffer2);
        outputStream1.close();

        outputStream2 = new FileOutputStream(file1, true);
        outputStream2.write(buffer1);
        outputStream2.close();
    }
}
