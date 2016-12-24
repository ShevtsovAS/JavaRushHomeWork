package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String key;
        while (!(key = reader.readLine()).equals("exit")) {
            try {
                new ReadThread(key).start();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        reader.close();
/*
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(String.format("%s %d", entry.getKey(), entry.getValue()));
        }
*/
    }

    public static class ReadThread extends Thread {
        String fileName;
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run() {
            try {
                FileInputStream file = new FileInputStream(fileName);
                byte[] buffer = new byte[file.available()];
                file.read(buffer);
                file.close();

                Map<Byte, Integer> bytes = new HashMap<>();
                int max = 1;
                for (byte b : buffer) {
                    if (bytes.containsKey(b)) {
                        int count = bytes.get(b);
                        bytes.put(b, ++count);
                        if (count > max) max = count;
                    }
                    else bytes.put(b, 1);
                }

                for (Map.Entry<Byte, Integer> entry : bytes.entrySet()) {
                    int value = entry.getValue();
                    int searchedByte = entry.getKey();
                    if (value == max) {
                        resultMap.put(fileName, searchedByte);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
