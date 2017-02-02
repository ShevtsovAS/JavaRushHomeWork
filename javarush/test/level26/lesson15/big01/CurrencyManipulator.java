package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int newCount = denominations.get(denomination) + count;
            denominations.put(denomination, newCount);
        }
        else denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();
            amount += denomination * count;
        }
        return amount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();
        ArrayList<Integer> currencyList = new ArrayList<>(denominations.keySet());
        Collections.sort(currencyList, Collections.<Integer>reverseOrder());
        int rest = expectedAmount;
        for (Integer currentCurrency : currencyList) {
            if (rest < currentCurrency) continue;
            int count = denominations.get(currentCurrency);
            int expectedCount = rest / currentCurrency;
            if (expectedCount > count) {
                result.put(currentCurrency, count);
                rest -= currentCurrency * count;
            }
            else {
                result.put(currentCurrency, expectedCount);
                rest -= currentCurrency * expectedCount;
            }
            if (rest == 0) break;
        }
        if (rest != 0) throw new NotEnoughMoneyException();
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            int currency = entry.getKey();
            int count = entry.getValue();
            int newCount = denominations.get(currency) - count;
            if (newCount == 0) denominations.remove(currency);
            else denominations.put(currency, newCount);
        }
        return result;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }
}