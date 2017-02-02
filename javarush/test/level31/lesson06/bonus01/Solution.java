package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Неверное число параметров!");
            return;
        }

        File resultFile = new File(args[0]);
        String[] zipPartNames = new String[args.length - 1];
        List<FileInputStream> fisList = new ArrayList<>();

        System.arraycopy(args, 1, zipPartNames, 0, zipPartNames.length);
        Arrays.sort(zipPartNames);
        for (String zipPartName : zipPartNames) fisList.add(new FileInputStream(zipPartName));

        ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(fisList)));
        FileOutputStream outputStream = new FileOutputStream(resultFile);
        byte[] buffer = new byte[1024*1024];
        while (zipInputStream.getNextEntry() != null) {
            int size;
            while ((size = zipInputStream.read(buffer)) > 0)
                outputStream.write(buffer, 0, size);
        }
        outputStream.flush();

        zipInputStream.close();
        outputStream.close();
    }
}