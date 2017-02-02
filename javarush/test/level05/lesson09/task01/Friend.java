package com.javarush.test.level05.lesson09.task01;

/* Создать класс Friend
Создать класс Friend (друг) с тремя конструкторами:
- Имя
- Имя, возраст
- Имя, возраст, пол
*/

public class Friend
{
    //напишите тут ваш код
    private String name;
    private int age;
    private String gender;

    public Friend(String name) {
        this.name = name;
        this.age = 10;
        this.gender = "boy";
    }
    public Friend(String name, int age) {
        this.name = name;
        this.age = age;
        this.gender = "boy";
    }
    public Friend(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}