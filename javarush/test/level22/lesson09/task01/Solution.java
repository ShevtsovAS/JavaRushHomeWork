package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> words = new ArrayList<>();
        try(
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(consoleReader.readLine()))
        )
        {
            String s;
            while ((s = file.readLine()) != null) {
                for (String word : s.replaceAll("\\s{2,}", " ").trim().split("\\s")) {
                    words.add(word);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < words.size(); i++) {
            for (int j = i+1; j < words.size(); j++) {
                String s1 = words.get(i);
                String s2 = new StringBuilder(words.get(j)).reverse().toString();
                if (s1.equals(s2)) {
                    Pair pair = new Pair();
                    pair.first = s1;
                    pair.second = words.get(j);
                    if (!result.contains(pair)) result.add(pair);
                }
            }
        }

        for (Pair pair : result) {
            System.out.println(pair);
        }
        }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }
    }

}
