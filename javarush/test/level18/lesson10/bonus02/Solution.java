package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        String[] myArgs = "-c что то ещё, всё равно что! 123.45 678".split(" ");
        args = myArgs;

        if (args.length < 4) {
            System.out.println("Неверное кол-во параметров!");
            System.exit(1);
        }

        String fileName;
        String id;
        String productName = "";
        String price;
        String quantity;
        String product;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fileName = reader.readLine();
        reader.close();

        // Вычисляем ID
        reader = new BufferedReader(new FileReader(fileName));
        String line;
        int max_id = 0;
        while ((line = reader.readLine()) != null) {
            int current_id = 0;
            if (!line.equals("")) current_id = Integer.parseInt(line.substring(0, 8).trim());
            if (max_id < current_id) max_id = current_id;
        }
        reader.close();
        id = max_id + 1 + "";
        while (id.length() < 8) id += " ";

        // Формируем productName
        for (int i = 0; i < args.length - 3; i++) {
            productName += args[i+1] + " ";
        }
        while (productName.length() < 30) productName += " ";
        productName = productName.substring(0, 30);

        // Формируем price
        price = args[2 + (args.length - 4)];
        while (price.length() < 8) price += " ";
        price = price.substring(0, 8);

        // Формируем quantity
        quantity = args[3 + (args.length -4)];
        while (quantity.length() < 4) quantity += " ";
        quantity = quantity.substring(0, 4);

        // Добавляем новую строку в файл
        if (args[0].toLowerCase().equals("-c")) {
            product = id + productName + price + quantity;
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            if (max_id != 0) writer.newLine();
            writer.write(product);
            writer.close();
        }
    }
}
