package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file1 = new FileInputStream(reader.readLine());
        FileOutputStream file2 = new FileOutputStream(reader.readLine());
        reader.close();
        // Читаем файл с вещественными числами
        byte[] buffer = new byte[file1.available()];
        int count = file1.read(buffer);
        file1.close();
        // Округляем числа и пишем в другой файл результат
        String[] strings = new String(buffer).replaceAll("\\s{2,}", " ").trim().split(" ");
        for (int i = 0; i < strings.length; i++) strings[i] = (int) Math.round(Double.parseDouble(strings[i])) + "";
        StringBuilder builder = new StringBuilder();
        for (String s : strings) builder.append(s).append(" ");
        String s = builder.toString().trim();
        byte[] nums = s.getBytes();
        file2.write(nums);
        file2.close();
    }
}
