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
    public static volatile Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        while (!(fileName = reader.readLine()).equals("exit")) new ReadThread(fileName).start();
        reader.close();
/*
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(String.format("%s %d", entry.getKey(), entry.getValue()));
        }
*/
    }

    public static class ReadThread extends Thread {
        private String fileName;
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут

        @Override
        public void run() {
            try {
                FileInputStream file = new FileInputStream(fileName);
                Map<Integer, Integer> bytesMap = new HashMap<>();
                int current, max = 0;
                while (file.available() > 0) {
                    current = file.read();
                    if (bytesMap.containsKey(current)) {
                        int count = bytesMap.get(current) + 1;
                        bytesMap.put(current, count);
                        if (max < count) max = count;
                    }
                    else bytesMap.put(current, 1);
                }
                file.close();
                synchronized (resultMap) {
                    if (max == 0) resultMap.put(fileName, 0);
                    else {
                        for (Map.Entry<Integer, Integer> entry : bytesMap.entrySet()) {
                            int value = entry.getValue();
                            if (value == max) resultMap.put(fileName, entry.getKey());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
