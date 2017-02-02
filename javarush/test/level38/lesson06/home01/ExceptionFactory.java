package com.javarush.test.level38.lesson06.home01;

/**
 * Created by sas on 26.01.17.
 */
public class ExceptionFactory {
    public static Throwable getException(Enum e) {
        String message = "";
        if (e != null) message = e.name().charAt(0) + e.name().substring(1).replaceAll("_", " ").toLowerCase();

        if (e instanceof ExceptionApplicationMessage) return new Exception(message);
        else if (e instanceof ExceptionDBMessage) return new RuntimeException(message);
        else if (e instanceof ExceptionUserMessage) return new Error(message);

        return new IllegalArgumentException();
    }
}
