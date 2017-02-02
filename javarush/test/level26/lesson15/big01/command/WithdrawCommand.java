package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw", CashMachine.locale);
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before")+"\n");
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String amount;
        boolean amountIsCorrect;
        Map<Integer, Integer> cash = new HashMap<>();
        do {
            amountIsCorrect = true;
            ConsoleHelper.writeMessage(res.getString("specify.amount")+"\n");
            amount = ConsoleHelper.readString();
            if (!amount.matches("\\d+")) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount")+"\n");
                amountIsCorrect = false;
                continue;
            }
            if (!manipulator.isAmountAvailable(Integer.parseInt(amount))) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money")+"\n");
                amountIsCorrect = false;
                continue;
            }
            try {
                cash = manipulator.withdrawAmount(Integer.parseInt(amount));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available")+"\n");
                amountIsCorrect = false;
            }
        } while (!amountIsCorrect);

        ArrayList<Integer> sortedCash = new ArrayList<>(cash.keySet());
        Collections.sort(sortedCash, Collections.<Integer>reverseOrder());
        for (Integer currency : sortedCash) {
            System.out.println(String.format("\t%d - %d", currency, cash.get(currency)));
        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format")+"\n", Integer.parseInt(amount), currencyCode));
    }
}