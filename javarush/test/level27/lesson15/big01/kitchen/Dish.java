package com.javarush.test.level27.lesson15.big01.kitchen;

public enum  Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        StringBuilder result = new StringBuilder();
        Dish[] dishes = values();
        for (int i = 0; i < dishes.length; i++) {
            if (i == dishes.length - 1) result.append(dishes[i]);
            else result.append(dishes[i] + ", ");
        }
        return result.toString();
    }
}
