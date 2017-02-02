package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

// ВЫЕБАЛ СЕБЕ ВСЮ ГОЛОВУ
public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedReader file2 = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        ArrayList<String> file1List = new ArrayList<>();
        ArrayList<String> file2List = new ArrayList<>();

        while (file1.ready()) file1List.add(file1.readLine());
        while (file2.ready()) file2List.add(file2.readLine());
        file1.close();
        file2.close();

        for (int i = 0; i < file1List.size(); i++) {
            if (file2List.size() == 0) {
                lines.add(new LineItem(Type.REMOVED, file1List.get(i)));
                continue;
            }
            String line1 = file1List.get(i);
            String line2 = file2List.get(0);
            if (line1.equals(line2)) {
                lines.add(new LineItem(Type.SAME, line1));
                file2List.remove(0);
            }
            else if (file2List.size() > 1 && line1.equals(file2List.get(1))) {
                lines.add(new LineItem(Type.ADDED, line2));
                file2List.remove(0);
                i--;
            }
            else lines.add(new LineItem(Type.REMOVED, line1));
        }

        for (String s : file2List) lines.add(new LineItem(Type.ADDED, s));

        for (LineItem line : lines) System.out.println(line.type + " " + line.line);

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
