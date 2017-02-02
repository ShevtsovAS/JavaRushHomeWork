package com.javarush.test.level19.lesson10.home05   ;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner file1 = new Scanner(new File(args[0]));
        FileWriter file2 = new FileWriter(args[1]);
        while (file1.hasNext()) {
            String s = file1.next();
            if (s.matches(".*\\d.*")) {
                file2.write(s);
                if (file1.hasNext()) file2.write(" ");
            }
        }
        file1.close();
        file2.close();
    }
}
