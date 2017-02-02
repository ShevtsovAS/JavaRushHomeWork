package com.javarush.test.level05.lesson07.task04;

/* Создать класс Circle
Создать класс (Circle) круг, с тремя инициализаторами:
- centerX, centerY, radius
- centerX, centerY, radius, width
- centerX, centerY, radius, width, color
*/

import java.awt.*;

public class Circle
{
    //напишите тут ваш код
    private double centerX, centerY, radius, width;
    private Color color;

    public void initialize(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.width = 1;
        this.color = Color.black;
    }
    public void initialize(double centerX, double centerY, double radius, double width) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.width = width;
        this.color = Color.black;
    }
    public void initialize(double centerX, double centerY, double radius, double width, Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.width = width;
        this.color = color;
    }
}
