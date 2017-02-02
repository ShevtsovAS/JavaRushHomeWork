package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.*;
import java.util.*;

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

        HashMap<Byte, Integer> symbols = new HashMap<>();

        for (byte b : buffer) {
            int count;
            if (symbols.containsKey(b)) {
                count = symbols.get(b);
                symbols.put(b, ++count);
            }
            else symbols.put(b, 1);
        }

        byte[] sortedBuffer = new byte[symbols.size()];
        int i =0;
        for (Map.Entry<Byte, Integer> entry : symbols.entrySet()) sortedBuffer[i++] = entry.getKey();
        Arrays.sort(sortedBuffer);

        for (byte b : sortedBuffer) {
            char ch = (char) b;
            System.out.println(String.format("%s %d", ch, symbols.get(b)));
        }
    }
}
