package com.javarush.test.level27.lesson09.home01;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() throws InterruptedException {
        while (!isValuePresent) this.wait();
        System.out.println("Got: " + value);
        isValuePresent = false;
        this.notifyAll();
        return value;
    }

    public synchronized void put(int value) throws InterruptedException {
        while (isValuePresent) this.wait();
        this.value = value;
        isValuePresent = true;
        System.out.println("Put: " + value);
        this.notifyAll();
    }
}
