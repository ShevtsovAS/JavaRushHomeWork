package com.javarush.test.level15.lesson12.bonus01;

/**
 * Created by SAS on 29.03.2016.
 */
public class Plane implements Flyable {
    private int passengers;
    @Override
    public void fly() {

    }

    public Plane(int passengers) {
        this.passengers = passengers;
    }
}
