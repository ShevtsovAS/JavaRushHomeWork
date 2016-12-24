package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
Подсказка: Нить 4 можно проверить методом isAlive()
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);
    static {
        threads.add(new ThreadOne());
        threads.add(new ThreadTwo());
        threads.add(new ThreadThree());
        threads.add(new ThreadFour());
        threads.add(new ThreadFive());
    }

/*
    public static void main(String[] args) throws InterruptedException {
        threads.get(0).start();
        threads.get(1).start();
        threads.get(1).interrupt();
        threads.get(2).start();
        Thread.sleep(5000);
        threads.get(2).interrupt();
        System.out.println("Ждём завершения Ура...");
        threads.get(2).join();
        threads.get(3).start();
        Message message = (Message) threads.get(3);
        message.showWarning();
        if (threads.get(3).isAlive()) {
            System.out.println("Ждём завершения...");
            threads.get(3).join();
        }
        threads.get(4).start();
    }
*/
    public static class ThreadOne extends Thread {
        @Override
        public void run() {
            while (true){}
        }
    }
    public static class ThreadTwo extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
            }
        }
    }
    public static class ThreadThree extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Ура");
                    sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }
    public static class ThreadFour extends Thread implements Message{
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }

        @Override
        public void showWarning() throws InterruptedException {
            interrupt();
        }
    }
    public static class ThreadFive extends Thread {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        int sum = 0;
        @Override
        public void run() {
            try {
                while ((s = reader.readLine()).equals("N")) {
                    sum += Integer.parseInt(s);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(sum);
        }
    }
}


