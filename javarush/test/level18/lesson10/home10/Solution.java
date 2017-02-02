package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> fileNames = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        while (!(fileName = reader.readLine()).equals("end")) {
            if (new File(fileName).exists()) fileNames.add(fileName);
            else System.out.println(String.format("Файл с именем %s не найден!", fileName));
        }
        reader.close();

        String[] SortedFileNames =new String[fileNames.size()];
        for (int i = 0; i < fileNames.size(); i++) SortedFileNames[i] = fileNames.get(i);
        sortFileNames(SortedFileNames);

        for (String name : SortedFileNames) {
            FileInputStream inputStream = new FileInputStream(name);
            FileOutputStream outputStream = new FileOutputStream(new File(name.substring(0, name.lastIndexOf("."))), true);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            outputStream.write(buffer);
            inputStream.close();
            outputStream.close();
        }
    }

    private static void sortFileNames(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            String s1 = arr[i];
            int N1 = Integer.parseInt(s1.substring(s1.lastIndexOf("t")+1));
            int min = N1;
            int min_i = i;
            for (int j = i+1; j < arr.length; j++) {
                String s2 = arr[j];
                int N2 = Integer.parseInt(s2.substring(s2.lastIndexOf("t")+1));
                if (N2 < min) {
                    min = N2;
                    min_i = j;
                }
            }
            if (min_i != i) {
                String tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
    }
}
