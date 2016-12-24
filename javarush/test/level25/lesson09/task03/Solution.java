package com.javarush.test.level25.lesson09.task03;

import java.util.ArrayList;

/* Живем своим умом
В классе Solution реализуйте интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений начиная с самого вложенного.
Пример исключения: new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/
public class Solution implements Thread.UncaughtExceptionHandler {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
                } catch (Exception e){
                    getUncaughtExceptionHandler().uncaughtException(currentThread(), e);
                }
            }
        };
        thread.setUncaughtExceptionHandler(new Solution());
        thread.start();
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        ArrayList<Throwable> throwables = new ArrayList<>();
        Throwable throwable = e;
        while (throwable != null) {
            throwables.add(0, throwable);
            throwable = throwable.getCause();
        }
        for (Throwable currentCause : throwables) {
            System.out.println(currentCause.toString());
        }
    }
}