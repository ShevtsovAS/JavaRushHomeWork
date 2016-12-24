package com.javarush.test.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки. Не использовать try-with-resources

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
//        String[] myArgs = new String[] {"15"};
//        args = myArgs;
        if (args.length < 1) {
            System.out.println("Не указан ID!");
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        ArrayList<String> strList = new ArrayList<>();
        String s;
        while ((s = fileReader.readLine()) != null) strList.add(s);
        fileReader.close();

        for (String s1 : strList) {
            String[] columns = s1.split(" ");
            int current_id = Integer.parseInt(columns[0]);
            int id = Integer.parseInt(args[0]);
            if (current_id == id) System.out.println(s1);
        }
    }
}
