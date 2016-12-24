package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = "com.javarush.test.level26.lesson15.big01.resources.";
    public static final Locale RUSSIAN = Locale.getDefault();
    public static final Locale ENGLISH = Locale.ENGLISH;
    public static Locale locale = null;
    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Выберете язык:");
            System.out.println("1 - ENGLISH");
            System.out.println("2 - РУССКИЙ");
            System.out.print(": ");
            try {
                String s = reader.readLine();
                if (s.equals("1")) locale = ENGLISH;
                if (s.equals("2")) locale = RUSSIAN;
            } catch (IOException ignored) { }
        } while (locale == null);
    }
    public static void main(String[] args) throws InterruptOperationException {
        try {
            Locale.setDefault(Locale.ENGLISH);
            Operation operation = Operation.LOGIN;
            CommandExecutor.execute(operation);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (!operation.equals(Operation.EXIT));
        } catch (InterruptOperationException e) {
            CommandExecutor.execute(Operation.EXIT);
        }
        ConsoleHelper.printExitMessage();
    }
}