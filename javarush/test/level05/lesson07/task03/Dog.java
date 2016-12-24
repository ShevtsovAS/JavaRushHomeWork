package com.javarush.test.level05.lesson07.task03;

/* Создать класс Dog
Создать класс Dog (собака) с тремя инициализаторами:
- Имя
- Имя, рост
- Имя, рост, цвет
*/

import java.awt.*;

public class Dog
{
    //напишите тут ваш код
    private String name;
    private int height;
    private Color color;

    public void initialize(String name) {
        this.name = name;
        this.height = 5;
        this.color = Color.black;
    }
    public void initialize(String name, int height) {
        this.name = name;
        this.height = height;
        this.color = Color.black;
    }
    public void initialize(String name, int height, Color color) {
        this.name = name;
        this.height = height;
        this.color = color;
    }
}
