package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
/*
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        String fileName = getFileName();
        if (!fileExists(fileName)) {
            System.out.println("Файл не существует");
            return;
        }
        int[] numbersFromFile = getEvenNumbersFromFile(fileName);
        numbersFromFile = sort(numbersFromFile);
        for (int i : numbersFromFile) {
            System.out.println(i);
        }
    }

    private static int[] sort(int[] intArray) {
        for (int i = 0; i < intArray.length; i++) {
            int min = intArray[i];
            int min_i = i;
            for (int j = i+1; j < intArray.length; j++) {
                if (intArray[j] < min) {
                    min = intArray[j];
                    min_i = j;
                }
            }
            if (min_i != i) {
                int tmp = intArray[i];
                intArray[i] = intArray[min_i];
                intArray[min_i] = tmp;
            }
        }
        return intArray;
    }
    private static int[] getEvenNumbersFromFile(String fileName) throws IOException {
        ArrayList<Integer> N_list = new ArrayList<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while ((line = reader.readLine()) != null) {
            int N = new Integer(line);
            if (N%2 == 0) N_list.add(N);
        }
        int[] result = new int[N_list.size()];
        for (int i = 0; i < N_list.size(); i++) {
            result[i] = N_list.get(i);
        }
        return result;
    }
    private static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }
    private static String getFileName() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();
        return s;
    }
*/
}
