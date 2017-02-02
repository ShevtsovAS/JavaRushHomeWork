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

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
//        String[] myArgs = "span".trim().split("\\s+");
//        args = myArgs;

        if (args.length < 1) {
            System.out.println("Не указан параметр имя тэга");
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(new FileReader(new BufferedReader(new InputStreamReader(System.in)).readLine()));
        String text = "";
        String line;
        while ((line = reader.readLine()) != null) text += line;
        reader.close();

        String tagName = args[0];
        Pattern pattern = Pattern.compile("<" + tagName + ".*?>|</" + tagName + ">");
        Matcher tag1 = pattern.matcher(text);

        while (tag1.find()) {
            if (tag1.group().startsWith("<" + tagName)) {
                int count = 0;
                Matcher tag2 = pattern.matcher(text.substring(tag1.end()));
                while (tag2.find()) {
                    if (tag2.group().startsWith("<" + tagName)) count++;
                    if (tag2.group().startsWith("</") && count > 0) count--;
                    if (count == 0) {
                        System.out.println(text.substring(tag1.start(), tag1.end()+tag2.end()));
                        break;
                    }
                }
            }
        }
    }
}
