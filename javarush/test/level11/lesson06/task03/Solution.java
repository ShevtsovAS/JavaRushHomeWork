package com.javarush.test.level11.lesson06.task03;

/* В гостях у бабушки
Написать шесть классов: Animal (животное), Cow(корова) и Pig(свинья), Sheep(овца), Bull(бык), Goat(козел).
Унаследовать корову, свинью, овцу, быка и козла от животного.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class Animal
    {
        String name;

        public void walk() {
            System.out.println("I'm walking...");
        }
    }

    public class Cow extends Animal
    {
        public void mooo() {
            System.out.println("Mooooo");
        }
    }

    public class Pig extends Animal
    {
        public void hruhru() {
            System.out.println("hru-hru");
        }
    }

    public class Sheep extends Animal
    {
        public void bebebe() {
            System.out.println("Be-e-e-e");
        }
    }

    public class Bull extends Animal
    {
        public void mooo() {
            System.out.println("Mooooo");
        }
    }

    public class Goat extends Animal
    {
        public void bebebe() {
            System.out.println("Be-e-e-e-e");
        }
    }

}
