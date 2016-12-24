package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

class DepositCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit", CashMachine.locale);
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before")+"\n");
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);
        int currency = 0;
        int count = 0;
        boolean success = true;
        try {
            currency = Integer.parseInt(twoDigits[0]);
            count = Integer.parseInt(twoDigits[1]);
            CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode).addAmount(currency, count);
        } catch (Exception e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data")+"\n");
            success = false;
        }
        if (success) ConsoleHelper.writeMessage(String.format(res.getString("success.format")+"\n", currency * count, currencyCode));
    }
}