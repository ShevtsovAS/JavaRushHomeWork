package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login", CashMachine.locale);
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before")+"\n");
        boolean dataIsCorrect;
        String cardNumber = "";
        String pin = "";
        do {
            dataIsCorrect = true;
            ConsoleHelper.writeMessage(res.getString("specify.data")+"\n");
            String[] s = ConsoleHelper.readString().trim().split("\\s");
            if (s.length != 2 || s[0].length() != 12 || s[1].length() != 4) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details")+"\n");
                dataIsCorrect = false;
                continue;
            }
            cardNumber = s[0];
            pin = s[1];
            if (!validCreditCards.containsKey(cardNumber) || !validCreditCards.getString(cardNumber).equals(pin)) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format")+"\n", cardNumber));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit")+"\n");
                dataIsCorrect = false;
            }
        } while (!dataIsCorrect);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format")+"\n", cardNumber));
    }
}