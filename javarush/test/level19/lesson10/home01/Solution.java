package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
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
        while ((line = file.readLine()) != null) {
            String[] columns = line.trim().split("\\s+");
            if (salaries.containsKey(columns[0])) {
                Double sum = salaries.get(columns[0]) + Double.parseDouble(columns[1]);
                salaries.put(columns[0], sum);
            }
            else salaries.put(columns[0], Double.parseDouble(columns[1]));
        }
        file.close();

        String[] sortedLastNames = new String[salaries.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : salaries.entrySet()) {
            sortedLastNames[i++] = entry.getKey();
        }
        Arrays.sort(sortedLastNames);


        for (String lastName : sortedLastNames) {
            System.out.println(lastName + " " + salaries.get(lastName));
        }
    }
}
