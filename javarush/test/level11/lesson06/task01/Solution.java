package com.javarush.test.level11.lesson06.task01;

/* Лошадь и пегас
Написать два класса: Horse (лошадь) и Pegas (пегас).
Унаследовать пегаса от лошади.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class Horse {
        private String name;
        int height = 150;
        int weight = 100;

        public Horse(String name) {
            this.name = name;
        }

        public void run() {
            System.out.println("I'm running...");
            System.out.println("My name is " + name);
            System.out.println("My weight is " + weight);
            System.out.println("My height is " + height);
        }
    }

    public class Pegas extends Horse {
        public Pegas(String name) {
            super(name);
        }

        public void fly() {
            System.out.println("I'm flying...");
            System.out.println("My name is " + super.name);
            System.out.println("My weight is " + weight);
            System.out.println("My height is " + height);
        }
    }
}
