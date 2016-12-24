package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by sas on 19.12.16.
 */
public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        int x = getX() - getWidth() / 2;
        int y = getY() - getHeight() / 2;

        graphics.drawOval(x, y, getWidth(), getHeight());
        graphics.fillOval(x, y, getWidth(), getHeight());
    }
}
