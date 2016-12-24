package com.javarush.test.level05.lesson09.task02;

/* Создать класс Cat
Создать класс Cat (кот) с пятью конструкторами:
- Имя,
- Имя, вес, возраст
- Имя, возраст (вес стандартный)
- вес, цвет, (имя, адрес и возраст – неизвестные. Кот - бездомный)
- вес, цвет, адрес ( чужой домашний кот)
Задача конструктора – сделать объект валидным. Например, если вес не известен, то нужно указать какой-нибудь средний вес. Кот не может ничего не весить. То же касательно возраста. А вот имени может и не быть (null). То же касается адреса: null.
*/

import java.awt.*;

public class Cat
{
    //напишите тут ваш код
    private String name;
    private String address;
    private int age;
    private int weight;
    private Color color;

    public Cat(String name) {
        this.name = name;
        this.weight = 1;
        this.age = 1;
        this.color = Color.black;
    }

    public Cat(String name, int weight, int age) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = Color.black;
    }
    public Cat(String name, int age) {
        this.name = name;
        this.weight = 1;
        this.age = age;
        this.color = Color.black;
    }
    public Cat(int weight, Color color) {
        this.weight = weight;
        this.age = 1;
        this.color = color;
    }
    public Cat(int weight, Color color, String address) {
        this.weight = weight;
        this.age = 1;
        this.color = color;
        this.address = address;
    }
}
