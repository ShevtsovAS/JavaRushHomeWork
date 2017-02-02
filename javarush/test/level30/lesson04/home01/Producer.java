package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by sas on 01.11.16.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                for (int i = 1; i <= 9; i++) {
                    System.out.format("Элемент 'ShareItem-%d' добавлен%n", i);
                    queue.offer(new ShareItem("ShareItem-" + i, i));
                    Thread.sleep(100);
                    if (queue.hasWaitingConsumer()) System.out.println("Consumer в ожидании!");
                }
                break;
            }
        } catch (InterruptedException e) { }
    }
}
