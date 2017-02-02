package com.javarush.test.level40.lesson08.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

/* Отправка GET-запроса через сокет
Перепиши реализацию метода getSite, он должен явно создавать и использовать сокетное соединение Socket с сервером.
Адрес сервера и параметры для GET-запроса получи из параметра url.
Порт используй дефолтный для http.
Классы HttpURLConnection, HttpClient и т.д. не использовать.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            String host = url.getHost();
            String path = url.getPath();

            Socket socket = new Socket(host, url.getDefaultPort());

            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            writer.println("GET " + path + " HTTP/1.1");
            writer.println("Host: " + host);
            writer.println("User-Agent: Mozilla/5.0");
            writer.println("Accept: text/html");
            writer.println("Connection: close");
            writer.println();
            writer.println();
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response;

            while ((response = reader.readLine()) != null) System.out.println(response);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}