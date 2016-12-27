package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by SAS on 20.03.2016.
 */
public class Singleton {
    private static Singleton singleton = null;

    private Singleton() {
    }

    static Singleton getInstance() {

        if (singleton == null) singleton = new Singleton();
        return singleton;
    }
}
