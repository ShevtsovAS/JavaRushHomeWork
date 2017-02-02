package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file1 = new FileInputStream(reader.readLine());
        FileOutputStream file2 = new FileOutputStream(reader.readLine());
        FileOutputStream file3 = new FileOutputStream(reader.readLine());
        reader.close();

        byte[] buffer = new byte[file1.available()];

        int count = file1.read(buffer);
        int file2Length;
        int file3Length;

        if ((count%2) != 0) {
            file2Length = (count + 1) / 2;
            file3Length = (count - 1) / 2;
        }
        else {
            file2Length = count / 2;
            file3Length = count / 2;
        }

        file2.write(buffer, 0, file2Length);
        file3.write(buffer, file2Length, file3Length);

        file1.close();
        file2.close();
        file3.close();
    }
}