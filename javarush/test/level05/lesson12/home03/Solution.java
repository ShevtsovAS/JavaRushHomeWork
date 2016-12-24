package com.javarush.test.level05.lesson12.home03;

/* Создай классы Dog, Cat, Mouse
Создай классы Dog, Cat, Mouse. Добавь по три поля в каждый класс, на твой выбор. Создай объекты для героев мультика Том и Джерри. Так много, как только вспомнишь.
Пример:
Mouse jerryMouse = new Mouse(“Jerry”, 12 , 5), где 12 - высота в см, 5 - длина хвоста в см.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Mouse jerryMouse = new Mouse("Jerry", 12 , 5);

        //напишите тут ваш код
        Dog spykeDog = new Dog("Spyke", 120, 7);
        Cat tomCat = new Cat("Tom", 80, 25);
    }

    public static class Mouse {
        String name;
        int height;
        int tail;

        public Mouse(String name, int height, int tail)
        {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }
    public static class Dog {
        String name;
        int height = 10;
        int tail = 2;

        public Dog(String name) {
            this.name = name;
        }

        public Dog(String name, int height) {
            this.name = name;
            this.height = height;
        }

        public Dog(String name, int height, int tail) {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }
    public static class Cat {
        String name;
        int height = 5;
        int tail = 3;

        public Cat(String name) {
            this.name = name;
        }

        public Cat(String name, int height) {
            this.name = name;
            this.height = height;
        }

        public Cat(String name, int height, int tail) {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }
}
