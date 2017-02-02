package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Cook cookAmigo = new Cook("Amigo");
        Cook cookDiego = new Cook("Diego");

        cookAmigo.setQueue(ORDER_QUEUE);
        cookDiego.setQueue(ORDER_QUEUE);

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i+1);
            tablet.setQueue(ORDER_QUEUE);
            tablets.add(tablet);
        }

        Waitor waitor = new Waitor();
        cookAmigo.addObserver(waitor);
        cookDiego.addObserver(waitor);

        Thread cookAmigoThread = new Thread(cookAmigo);
        Thread cookDiegoThread = new Thread(cookDiego);
        cookAmigoThread.start();
        cookDiegoThread.start();

        Thread OrderCreator = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        OrderCreator.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        } finally {
            OrderCreator.interrupt();
        }

        while (!ORDER_QUEUE.isEmpty()){
            Thread.sleep(1);
        }

        while ((cookAmigo.isBusy())||(cookDiego.isBusy())) { Thread.sleep(1);}
        cookAmigoThread.interrupt();
        cookDiegoThread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}