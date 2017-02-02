package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;

public class CurrencyManipulatorFactory {
    private static HashMap<String, CurrencyManipulator> manipulators = new HashMap<>();
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (!manipulators.containsKey(currencyCode)) manipulators.put(currencyCode, new CurrencyManipulator(currencyCode));
        return manipulators.get(currencyCode);
    }

    private CurrencyManipulatorFactory() {
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return manipulators.values();
    }
}