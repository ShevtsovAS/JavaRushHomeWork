package com.javarush.test.level04.lesson02.task01;

/* Реализовать метод setName
Реализовать метод setName, чтобы с его помощью можно было устанавливать значение переменной private String name равное переданному параметру String name.
*/

public class Cat {
    private String name;

/*
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setName("murzik");
        cat.showName();
    }
*/

    public void setName(String name) {
        this.name = name;
    }

/*
    public void showName() {
        System.out.println(this.name);
    }
*/
}
