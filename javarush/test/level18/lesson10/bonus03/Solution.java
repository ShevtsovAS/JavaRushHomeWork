package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
//        String[] myArgs = "-d 3".split(" ");
//        args = myArgs;

        if ((args[0].equals("-u") && args.length < 5) || (args[0].equals("-d") && args.length < 2)) {
            System.out.println("Неверное кол-во параметров!");
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        String line;
        ArrayList<String> fileData = new ArrayList<>();
        while ((line = fileReader.readLine()) != null) fileData.add(line);
        fileReader.close();

        String id;
        String productName = "";
        String price;
        String quantity;
        String product;

        // ID
        id = args[1];
        while (id.length() < 8) id += " ";
        id = id.substring(0, 8);

        if (args[0].equals("-u")) {
            // Формируем productName
            for (int i = 0; i < args.length - 4; i++) productName += args[i + 2] + " ";
            while (productName.length() < 30) productName += " ";
            productName = productName.substring(0, 30);

            // Формируем price
            price = args[args.length - 2];
            while (price.length() < 8) price += " ";
            price = price.substring(0, 8);

            // Формируем quantity
            quantity = args[args.length - 1];
            while (quantity.length() < 4) quantity += " ";
            quantity = quantity.substring(0, 4);

            product = id + productName + price + quantity;
            for (int i = 0; i < fileData.size(); i++) {
                if (fileData.get(i).substring(0, 8).equals(id)) fileData.set(i, product);
            }

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < fileData.size(); i++) {
                fileWriter.write(fileData.get(i));
                if (i < fileData.size() - 1) fileWriter.newLine();
            }
            fileWriter.close();
        }

        if (args[0].equals("-d")) {
            for (int i = 0; i < fileData.size(); i++) {
                if (fileData.get(i).substring(0, 8).equals(id)) fileData.remove(i);
            }

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < fileData.size(); i++) {
                fileWriter.write(fileData.get(i));
                if (i < fileData.size() - 1) fileWriter.newLine();
            }
            fileWriter.close();
        }
    }
}
