package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Locale;
import java.util.ResourceBundle;


class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info", CashMachine.locale);
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before")+"\n");
        boolean hasMoney = false;
        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manipulator.hasMoney()) {
                System.out.println(String.format("%s - %d", manipulator.getCurrencyCode(), manipulator.getTotalAmount()));
                hasMoney = true;
            }
        }
        if (!hasMoney) ConsoleHelper.writeMessage(res.getString("no.money")+"\n");
    }
}