package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by sas on 19.12.16.
 */
public class Model {
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private File levelFile = new File("src/com/javarush/test/level34/lesson15/big01/res/levels.txt");
    private LevelLoader levelLoader = new LevelLoader(Paths.get(levelFile.getAbsolutePath()));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollision(direction)) return;
        int sellSize = GameObject.FIELD_SELL_SIZE;
        switch (direction) {
            case LEFT: player.move(-sellSize, 0); break;
            case RIGHT: player.move(sellSize, 0); break;
            case UP: player.move(0, -sellSize); break;
            case DOWN: player.move(0, sellSize); break;
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            // Проверяем столкновение игрока с ящиком
            if (player.isCollision(box, direction)) {
                // Возвращаем true если ящик упирается в стену
                if (checkWallCollision(box, direction)) return true;
                // Возвращаем true если ящик упирается в другой ящик
                for (Box box2 : gameObjects.getBoxes()) {
                    if (box.isCollision(box2, direction)) return true;
                }
                // Сдвигаем ящик на следующую клетку
                int sellSize = GameObject.FIELD_SELL_SIZE;
                switch (direction) {
                    case LEFT: box.move(-sellSize, 0); break;
                    case RIGHT: box.move(sellSize, 0); break;
                    case UP: box.move(0, -sellSize); break;
                    case DOWN: box.move(0, sellSize); break;
                }
            }
        }
        return false;
    }

    public void checkCompletion() {
        int boxInHomeCount = 0;
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) boxInHomeCount++;
            }
        }
        if (boxInHomeCount == gameObjects.getHomes().size()) eventListener.levelCompleted(currentLevel);
    }
}
