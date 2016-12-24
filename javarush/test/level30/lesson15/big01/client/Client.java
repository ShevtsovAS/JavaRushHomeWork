package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by sas on 02.11.16.
 */
public class Client {
    protected Connection connection;
    protected volatile boolean clientConnected = false;

    public static void main(String[] args) {
        new Client().run();
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Ошибка соединения!");
                return;
            }
        }

        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            while (clientConnected) {
                String text = ConsoleHelper.readString();
                if (text.equals("exit")) return;
                if (shouldSentTextFromConsole()) sendTextMessage(text);
            }
        }
        else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
    }

    protected String getServerAddress() {
        while (true) {
            ConsoleHelper.writeMessage("Введите адрес сервера:");
            String serverAddress = ConsoleHelper.readString();
            if (addressIsCorrect(serverAddress)) return serverAddress;
        }
    }

    private boolean addressIsCorrect(String ip) {
        return ip.matches(
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])" +
                "|localhost");
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите ваше имя:");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSentTextFromConsole() { return true; }

    protected SocketThread getSocketThread() { return new SocketThread(); }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка! Не удалось отправить сообщение.");
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread{
        @Override
        public void run() {
            try {
                Socket socket = new Socket(getServerAddress(), getServerPort());
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (Exception e) {
                notifyConnectionStatusChanged(false);
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                switch (message.getType()) {
                    case NAME_REQUEST: connection.send(new Message(MessageType.USER_NAME, getUserName())); break;
                    case NAME_ACCEPTED: notifyConnectionStatusChanged(true); return;
                    default: throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                switch (message.getType()) {
                    case TEXT: processIncomingMessage(message.getData()); break;
                    case USER_ADDED: informAboutAddingNewUser(message.getData()); break;
                    case USER_REMOVED: informAboutDeletingNewUser(message.getData()); break;
                    default: throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void processIncomingMessage(String message) { ConsoleHelper.writeMessage(message); }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(String.format("Участник с именем %s присоединился к чату", userName));
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(String.format("Участник с именем %s покинул чат", userName));
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }
    }
}
