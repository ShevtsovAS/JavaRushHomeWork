package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream file1 = new FileOutputStream(reader.readLine());
        FileInputStream file2 = new FileInputStream(reader.readLine());
        FileInputStream file3 = new FileInputStream(reader.readLine());
        reader.close();

        byte[] buffer1 = new byte[file2.available()];
        byte[] buffer2 = new byte[file3.available()];
        file2.read(buffer1);
        file3.read(buffer2);
        file2.close();
        file3.close();

        file1.write(buffer1);
        file1.write(buffer2);
        file1.close();
    }
}
