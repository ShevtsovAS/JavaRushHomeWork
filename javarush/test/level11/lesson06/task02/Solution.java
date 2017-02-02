package com.javarush.test.level11.lesson06.task02;

/* Домашние животные
Написать три класса: Pet (домашнее животное), Cat(кот) и Dog(собака).
Унаследовать кота и собаку от животного.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class Pet
    {
        String name;
        int weight;
        int heoght;
    }

    public class Cat extends Pet
    {
        public void myeow() {
            System.out.println("Myeow");
        }
    }

    public class Dog extends Pet
    {
        public void gaf() {
            System.out.println("gaf, gaf");
        }
    }
}
