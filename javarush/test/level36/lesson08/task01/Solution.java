package com.javarush.test.level36.lesson08.task01;

import java.io.*;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        TreeSet<String> treeSet = new TreeSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        int c;
        while ((c = reader.read()) != -1) {
            String symbol = String.valueOf((char) c).toLowerCase();
            if (symbol.matches("[a-z]")) treeSet.add(symbol);
        }
        reader.close();

        int count = 0;
        for (String s : treeSet) {
            System.out.print(s);
            if (++count == 5) break;
        }
    }
}
