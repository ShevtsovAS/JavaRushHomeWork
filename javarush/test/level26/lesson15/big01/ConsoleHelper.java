package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common", CashMachine.locale);
    private final static BufferedReader READER;

    static {
        READER = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void writeMessage(String message) {
        System.out.print(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = READER.readLine();
        } catch (IOException ignored) {
        }
        if (s.toUpperCase().equals("EXIT")) throw new InterruptOperationException();
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result;
        do {
            writeMessage(res.getString("choose.currency.code")+"\n");
            result = readString();
        } while (!result.matches("[A-Za-z]{3}"));
        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] result;
        boolean dataIsValid;
        do {
            dataIsValid = true;
            writeMessage(String.format(res.getString("choose.denomination.and.count.format")+"\n", currencyCode));
            result = readString().split("\\s");
            if (result.length == 2) {
                for (String s : result) {
                    if (!s.matches("\\d+")){
                        dataIsValid = false;
                        break;
                    }
                    if (Integer.parseInt(s) <= 0) {
                        dataIsValid = false;
                        break;
                    }
                }
            }
            else
                dataIsValid = false;
            if (!dataIsValid) writeMessage(res.getString("invalid.data")+"\n");
        } while (!dataIsValid);
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation operation = null;
        boolean dataIsValid;
        do {
            dataIsValid = true;
            writeMessage(res.getString("choose.operation")+"\n");
            writeMessage(String.format("1 - %s\n2 - %s\n3 - %s\n4 - %s\n",
                    res.getString("operation.INFO"),
                    res.getString("operation.DEPOSIT"),
                    res.getString("operation.WITHDRAW"),
                    res.getString("operation.EXIT")));
            String s = readString();
            if (!s.matches("\\d")) {
                dataIsValid = false;
                writeMessage(res.getString("invalid.data")+"\n");
                continue;
            }
            try {
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(s));
            } catch (IllegalArgumentException e) {
                dataIsValid = false;
                writeMessage(res.getString("invalid.data")+"\n");
            }
        } while (!dataIsValid);
        return operation;
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end")+"\n");
    }
}