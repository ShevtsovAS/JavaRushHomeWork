package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();
        Random random = new Random();
        int dishesCount = random.nextInt(4) + 1;
        Dish[] allDishes = Dish.values();
        for (int i = 0; i < dishesCount; i++) {
            int randomDish = random.nextInt(allDishes.length);
            dishes.add(allDishes[randomDish]);
        }
    }
}