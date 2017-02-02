package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
//        String[] myArgs = "myFile1.txt".trim().split("\\s+");
//        args = myArgs;

        String fileName = args[0];
        BufferedReader file = new BufferedReader(new FileReader(fileName));

        Map<String, Double> salaries = new HashMap<>();
        String line;
        Double max = 0d;
        while ((line = file.readLine()) != null) {
            String[] columns = line.trim().split("\\s+");
            Double sum;
            if (salaries.containsKey(columns[0])) {
                sum = salaries.get(columns[0]) + Double.parseDouble(columns[1]);
                salaries.put(columns[0], sum);
            }
            else {
                sum = Double.parseDouble(columns[1]);
                salaries.put(columns[0], sum);
            }
            if (max < sum) max = sum;
        }
        file.close();

        for (Map.Entry<String, Double> entry : salaries.entrySet()) {
            double salary = entry.getValue();
            if (salary == max) System.out.println(entry.getKey());
        }
    }
}
