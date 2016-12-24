package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String[] myArgs = "span".trim().split("\\s+");
        args = myArgs;

        if (args.length == 0) {
            System.out.println("Не указан тэг для поиска!");
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        String textFromFile = "";
        String line;
        while ((line = file.readLine()) != null) textFromFile += line;

        ArrayList<String> tags = new ArrayList<>();

        Pattern pTagBegin = Pattern.compile("<"+args[0]+".*?>");
        Pattern pTagEnd = Pattern.compile("</"+args[0]+">");
        Matcher tagBegin = pTagBegin.matcher(textFromFile);
        Matcher tagEnd = pTagEnd.matcher(textFromFile);

        int count = 0;
        while (tagBegin.find()) {
            while (tagEnd.find()) {
                String s = textFromFile.substring(tagBegin.end(), tagEnd.end());
                Matcher inside = pTagBegin.matcher(s);
                while (inside.find()) count++;
            }
        }

        System.out.println(count);
/*
        for (String tag : tags) {
            System.out.println(tag);
        }
*/
    }
}
