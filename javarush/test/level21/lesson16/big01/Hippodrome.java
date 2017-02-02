package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

public class Hippodrome {
    private ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;
    public static void main(String[] args) {
        game = new Hippodrome();
        game.getHorses().add(new Horse("Horse1", 3, 0));
        game.getHorses().add(new Horse("Horse2", 3, 0));
        game.getHorses().add(new Horse("Horse3", 3, 0));
        game.run();
        game.printWinner();
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner() {
        int indexOfWinner = 0;
        double maxDistance = 0.0;
        for (int i = 0; i < horses.size(); i++) {
            double currentDistance = horses.get(i).getDistance();
            if (currentDistance > maxDistance) {
                maxDistance = currentDistance;
                indexOfWinner = i;
            }
        }
        return horses.get(indexOfWinner);
    }

    public void printWinner() {
        System.out.printf("Winner is %s!", getWinner().getName());
    }
}
