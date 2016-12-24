package com.javarush.test.level32.lesson04.home01;

import java.io.*;

/* Читаем из потока
Реализуйте логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter stringWriter = new StringWriter();
        if (is != null) {
            try (InputStreamReader reader = new InputStreamReader(is)) {
                char[] buffer = new char[8 * 1024];
                int len;
                while ((len = reader.read(buffer)) > 0) stringWriter.write(buffer, 0, len);
            }
        }
        return stringWriter;
    }
}
