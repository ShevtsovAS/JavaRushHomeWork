package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner file1 = new Scanner(new File(args[0]));
        FileWriter file2 = new FileWriter(args[1]);

        String text = "";
        while (file1.hasNext()) {
            String s = file1.next();
            if (s.matches(".{7,}")) text += s + ",";
        }
        file1.close();

        if (text.endsWith(",")) text = text.substring(0, text.lastIndexOf(","));
        file2.write(text);
        file2.close();
    }
}
