package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by sas on 19.12.16.
 */
public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        int x = getX() - getWidth() / 2;
        int y = getY() - getHeight() / 2;

        graphics.drawRect(x, y, getWidth(), getHeight());
        graphics.fillRect(x, y, getWidth(), getHeight());
    }
}
