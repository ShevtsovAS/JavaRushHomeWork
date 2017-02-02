package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by sas on 19.12.16.
 */
public abstract class GameObject {
    public static int FIELD_SELL_SIZE = 20;

    private int x;
    private int y;
    private int width;
    private int height;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = FIELD_SELL_SIZE;
        this.height = FIELD_SELL_SIZE;
    }

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void draw(Graphics graphics);
}
