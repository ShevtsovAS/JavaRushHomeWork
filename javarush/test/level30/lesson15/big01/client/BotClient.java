package com.javarush.test.level30.lesson15.big01.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sas on 04.11.16.
 */
public class BotClient extends Client {
    private static int botCounter = 0;

    public static void main(String[] args) {
        new BotClient().run();
    }
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        if (botCounter >= 99) botCounter = 0;
        return "date_bot_" + botCounter++;
    }

    public class BotSocketThread extends SocketThread {
        SimpleDateFormat currentDate = new SimpleDateFormat("d.MM.YYYY");
        SimpleDateFormat currentDay = new SimpleDateFormat("d");
        SimpleDateFormat currentMonth = new SimpleDateFormat("MMMM");
        SimpleDateFormat currentYear = new SimpleDateFormat("YYYY");
        SimpleDateFormat currentTime = new SimpleDateFormat("H:mm:ss");
        SimpleDateFormat currentHour = new SimpleDateFormat("H");
        SimpleDateFormat currentMinute = new SimpleDateFormat("m");
        SimpleDateFormat currentSecond = new SimpleDateFormat("s");

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);

            String[] text = message.split(": ");

            if (text.length == 2) {
                String info = String.format("Информация для %s: ", text[0]);
                switch (text[1]) {
                    case "дата": sendTextMessage(info + currentDate.format(Calendar.getInstance().getTime())); break;
                    case "день": sendTextMessage(info + currentDay.format(Calendar.getInstance().getTime())); break;
                    case "месяц": sendTextMessage(info + currentMonth.format(Calendar.getInstance().getTime())); break;
                    case "год": sendTextMessage(info + currentYear.format(Calendar.getInstance().getTime())); break;
                    case "время": sendTextMessage(info + currentTime.format(Calendar.getInstance().getTime())); break;
                    case "час": sendTextMessage(info + currentHour.format(Calendar.getInstance().getTime())); break;
                    case "минуты": sendTextMessage(info + currentMinute.format(Calendar.getInstance().getTime())); break;
                    case "секунды": sendTextMessage(info + currentSecond.format(Calendar.getInstance().getTime())); break;
                }
            }
        }
    }
}
