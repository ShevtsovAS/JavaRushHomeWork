package com.javarush.test.level34.lesson15.big01.model;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sas on 19.12.16.
 */
public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        if (level > 60) level = level % 60;

        try (RandomAccessFile file = new RandomAccessFile(levels.toFile(), "r")) {
            String line;
            while ((line = file.readLine()) != null) {
                if (line.equals("Maze: " + level)) {
                    String coordinates = file.readLine();
                    int sizeX = Integer.valueOf(file.readLine().split("\\s")[2]);
                    int sizeY = Integer.valueOf(file.readLine().split("\\s")[2]);
                    String end = file.readLine();
                    int length = Integer.valueOf(file.readLine().split("\\s")[1]);
                    file.readLine();

                    final int sellSize = GameObject.FIELD_SELL_SIZE;
                    int x = sellSize / 2;
                    int y = sellSize / 2;

                    for (int i = 0; i < sizeY; i++) {
                        line = file.readLine();
                        x = sellSize / 2;
                        char[] chars = line.toCharArray();
                        for (char c : chars) {
                            switch (c) {
                                case 'X': walls.add(new Wall(x, y)); break;
                                case '*': boxes.add(new Box(x, y)); break;
                                case '.': homes.add(new Home(x, y)); break;
                                case '&':
                                    boxes.add(new Box(x, y));
                                    homes.add(new Home(x, y));
                                    break;
                                case '@': player = new Player(x, y); break;
                            }
                            x += sellSize;
                        }
                        y += sellSize;
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
