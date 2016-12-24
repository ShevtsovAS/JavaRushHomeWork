package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        int cookingTime = order.getTotalCookingTime();
        Tablet tablet = order.getTablet();
        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %dmin", order, cookingTime));
        try {
            Thread.sleep(cookingTime * 10);
        } catch (InterruptedException e) {
        }
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(tablet.toString(), this.toString(), cookingTime * 60, order.getDishes()));
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!isBusy())
                    startCookingOrder(queue.take());
                else
                    Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}