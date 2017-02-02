package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        args = "/home/alexandr/testZIP/file.txt /home/alexandr/testZIP/test.zip".trim().split("\\s");
        if (args.length != 2) {
            System.out.println("Укажите файл и zip-архив");
            return;
        }

        File file = new File(args[0]);
        File zipFile = new File(args[1]);

        Map<ZipEntry, byte[]> zipMap = new HashMap<>();

        zipToMap(zipFile, zipMap);
        addNewFileToZip(file, zipMap, zipFile);
    }

    private static void zipToMap(File zipFile, Map<ZipEntry, byte[]> zipMap) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int size;
                while ((size = zipInputStream.read(buffer)) > 0)
                    bytes.write(buffer, 0, size);
                zipMap.put(zipEntry, bytes.toByteArray());
            }
        }
    }

    private static void addNewFileToZip(File file, Map<ZipEntry, byte[]> zipMap, File zipFile) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (Map.Entry<ZipEntry, byte[]> entry : zipMap.entrySet()) {
                ZipEntry zipEntry = entry.getKey();
                byte[] bytes = entry.getValue();
                if (zipEntry.getName().equals(file.getName())) {
                    zipOutputStream.putNextEntry(new ZipEntry("new/" + file.getName()));
                    Files.copy(file.toPath(), zipOutputStream);
                } else {
                    zipOutputStream.putNextEntry(zipEntry);
                    zipOutputStream.write(bytes);
                }
            }
        }
    }
}
