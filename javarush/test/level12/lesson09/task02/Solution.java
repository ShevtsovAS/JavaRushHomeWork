package com.javarush.test.level12.lesson09.task02;

/* Интерфейсы Fly, Run, Swim
Напиши public интерфейсы Fly(летать), Run(бежать/ездить), Swim(плавать).
Добавить в каждый интерфейс по одному методу.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    //add interfaces here - добавь интерфейсы тут
    public interface Fly {
        void moveUp();
        void moveDown();
    }

    public interface Run {
        void moveForward();
        void moveBack();
    }

    public interface Swim {
        void swimOnWater();
        void swimUnderWater();
    }
}
