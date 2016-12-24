package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by sas on 19.12.16.
 */
public abstract class CollisionObject extends GameObject{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case UP: x = getX(); y = getY() - FIELD_SELL_SIZE; break;
            case DOWN: x = getX(); y = getY() + FIELD_SELL_SIZE; break;
            case LEFT: x = getX() - FIELD_SELL_SIZE; y = getY(); break;
            case RIGHT: x = getX() + FIELD_SELL_SIZE; y = getY(); break;
        }

        return x == gameObject.getX() && y == gameObject.getY();
    }
}
