package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

/*
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.fillInPropertiesMap();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            System.out.println(String.format("%s %s", entry.getKey(), entry.getValue()));
        }
        OutputStream outputStream = new FileOutputStream(new File("properties.save"));
        solution.save(outputStream);
        outputStream.close();
    }
*/

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        load(new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine()));
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties propertiesFile = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet()) propertiesFile.put(entry.getKey(), entry.getValue());
        propertiesFile.store(outputStream, null);
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties propertiesFile = new Properties();
        propertiesFile.load(inputStream);
        for (Map.Entry<Object, Object> entry : propertiesFile.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            properties.put(key, value);
        }
    }
}
