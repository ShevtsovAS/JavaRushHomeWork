package com.javarush.test.level07.lesson09.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Буква «р» и буква «л»
1. Создай список слов, заполни его самостоятельно.
2. Метод fix должен:
2.1. удалять из списка строк все слова, содержащие букву «р»
2.2. удваивать все слова содержащие букву «л».
2.3. если слово содержит и букву «р» и букву «л», то оставить это слово без изменений.
2.4. с другими словами ничего не делать.
Пример:
роза
лира
лоза
Выходные данные:
лира
лоза
лоза
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<String>();
        list.add("роза"); //0
        list.add("лира"); //1
        list.add("лоза"); //2
        list = fix(list);

        for (String s : list)
        {
            System.out.println(s);
        }
    }

    public static ArrayList<String> fix(ArrayList<String> list) {
        //напишите тут ваш код
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.contains("р") && !s.contains("л")) {
                list.remove(i);
                i--;
            }
            else if (!s.contains("р") && s.contains("л")) {
                list.add(i, s);
                i++;
            }
        }
        return list;
    }

    // Более замороченный способ:
/*
    public static ArrayList<String> fix(ArrayList<String> list) {
        //напишите тут ваш код
        boolean del_s = false;
        boolean copy_s = false;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length(); j++) {
                String s = list.get(i);
                if (s.charAt(j) == 'р') del_s = true;
                if (s.charAt(j) == 'л') copy_s = true;
            }
            if (del_s && !copy_s) {
                list.remove(i);
                i--;
            }
            else if (!del_s && copy_s) {
                list.add(i, list.get(i));
                i++;
            }
            del_s = false;
            copy_s = false;
        }
        return list;
    }
*/

}