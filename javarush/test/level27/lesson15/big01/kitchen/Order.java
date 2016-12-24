package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Order {
    protected List<Dish> dishes;
    private Tablet tablet;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    @Override
    public String toString() {
        return dishes == null || dishes.isEmpty() ? "" : String.format("Your order: %s of %s", Arrays.toString(dishes.toArray()), tablet);
    }

    public int getTotalCookingTime() {
        int totalDuration = 0;
        for (Dish dish : dishes) totalDuration += dish.getDuration();
        return totalDuration;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    protected void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Tablet getTablet() {
        return tablet;
    }
}