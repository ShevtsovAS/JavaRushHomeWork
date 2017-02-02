package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        ArrayList<String> list = new ArrayList<>();
        fileName = bufferedReader.readLine();
        String s = "";
        while (!s.equals("exit")) {
            s = bufferedReader.readLine();
            list.add(s);
        }
        bufferedReader.close();
        OutputStream outStream = new FileOutputStream(fileName);
        byte[] lineSeparator = System.lineSeparator().getBytes();
/*
        for (String str : list) {
            byte[] bytes = str.getBytes();
            byte[] lineSeparator = System.lineSeparator().getBytes();
            outStream.write(bytes);
            outStream.write(lineSeparator);
        }
*/
        for (int i = 0; i < list.size(); i++) {
            byte[] bytes = list.get(i).getBytes();
            outStream.write(bytes);
            if (i != (list.size()-1)) outStream.write(lineSeparator);
        }
        outStream.close();
    }
}
