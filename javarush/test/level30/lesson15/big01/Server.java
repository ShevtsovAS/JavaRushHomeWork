package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alexandr on 01.11.16.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket socket = null;
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int serverPort = ConsoleHelper.readInt();
        try {
            socket = new ServerSocket(serverPort);
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true) {
                Socket clientSocket = socket.accept();
                new Handler(clientSocket).start();
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Ошибка! Не удаётся выполнить соединение.");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            String userName = entry.getKey();
            Connection connection = entry.getValue();
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Не удалось отправить сообщение пользователю " + userName);
            }
        }
    }

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Установленно соединение с адресом " + socket.getRemoteSocketAddress());
            String userName = null;

            try (Connection connection = new Connection(socket)) {
                ConsoleHelper.writeMessage("Подключение к порту: " + connection.getRemoteSocketAddress());
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Ошибка при обмене данными с удаленным адресом");
            }

            connectionMap.remove(userName);
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));

            ConsoleHelper.writeMessage("Cоединение с удаленным адресом закрыто");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message userReply = connection.receive();
                if (userReply.getType() == MessageType.USER_NAME) {
                    String userName = userReply.getData();
                    if (userName != null && !userName.isEmpty() && !connectionMap.containsKey(userName)) {
                        connectionMap.put(userName, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return userName;
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String userAddedName : connectionMap.keySet()) {
                if (userAddedName.equals(userName)) continue;
                connection.send(new Message(MessageType.USER_ADDED, userAddedName));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String text = userName + ": " + message.getData();
                    Message broadcastMessage = new Message(MessageType.TEXT, text);
                    sendBroadcastMessage(broadcastMessage);
                }
                else ConsoleHelper.writeMessage("Ошибка. Неверный тип сообщения");
            }
        }
    }
}