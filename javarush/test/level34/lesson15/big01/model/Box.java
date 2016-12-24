package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by sas on 19.12.16.
 */
public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        int x = getX() - getWidth() / 2;
        int y = getY() - getHeight() / 2;

        graphics.drawRect(x, y, getWidth(), getHeight());
        graphics.fillRect(x, y, getWidth(), getHeight());
    }
}
