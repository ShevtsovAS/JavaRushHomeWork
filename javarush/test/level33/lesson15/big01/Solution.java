package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sas on 03.12.16.
 */
public class Solution {

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 1000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for (String string : strings) {
            Long id = shortener.getId(string);
            if (id != null) ids.add(id);
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long id : keys) {
            String string = shortener.getString(id);
            if (string != null) strings.add(string);
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> values = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            values.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date startTest = new Date();
        Set<Long> keys = getIds(shortener, values);
        Date endTest = new Date();
        long timeToGetIds = endTest.getTime() - startTest.getTime();
        Helper.printMessage("getIds: " + timeToGetIds);

        startTest = new Date();
        Set<String> strings = getStrings(shortener, keys);
        endTest = new Date();
        long timeToGetStrings = endTest.getTime() - startTest.getTime();
        Helper.printMessage("getStrings: " + timeToGetStrings);

        boolean testOk = true;
        for (String value : values) {
            if (!strings.contains(value)) {
                testOk = false;
                break;
            }
        }
        if (testOk) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}
