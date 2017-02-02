package com.javarush.test.level22.lesson09.task03;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:


Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        ArrayList<String> wordsList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        String s;
        while ((s = file.readLine()) != null)
            wordsList.addAll(Arrays.asList(s.replaceAll("\\s{2,}", " ").trim().split("\\s")));
        file.close();

        String[] words = wordsList.toArray(new String[wordsList.size()]);
        Arrays.sort(words);

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words)
    {
        StringBuilder result = new StringBuilder();
        if (words == null || words.length == 0) return result;
        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
        ArrayList<String> tmpList = new ArrayList<>(list);
        Collections.shuffle(tmpList);
        result.append(tmpList.get(0));
        tmpList.remove(0);

        int count = 0;
        int attempts = 0;
        for (int i = 0; i < tmpList.size();) {
            String word = tmpList.get(i);
            char c1 = Character.toUpperCase(result.charAt(0));
            char c2 = Character.toUpperCase(result.charAt(result.length()-1));
            char c3 = Character.toUpperCase(word.charAt(0));
            char c4 = Character.toUpperCase(word.charAt(word.length()-1));
            if (c2 == c3) {
                result.append(" ");
                result.append(word);
                tmpList.remove(i);
                count++;
            }
            else if (c4 == c1) {
                result.insert(0, word + " ");
                tmpList.remove(i);
                count++;
            }
            else i++;
            if (i >= tmpList.size()-1 && count > 0) {
                count = 0;
                i = 0;
            }
            else if (i >= tmpList.size()-1 && count == 0 && attempts < 50) {
                tmpList = new ArrayList<>(list);
                Collections.shuffle(tmpList);
                result = new StringBuilder(tmpList.get(0));
                tmpList.remove(0);
                i = 0;
                attempts++;
            }
        }
        return new StringBuilder(result.toString().trim());
    }
}