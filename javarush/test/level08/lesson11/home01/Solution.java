package com.javarush.test.level08.lesson11.home01;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* Set из котов
1. Внутри класса Solution создать public static класс кот – Cat.
2. Реализовать метод createCats, он должен создавать множество (Set) котов и добавлять в него 3 кота.
3. В методе main удалите одного кота из Set cats.
4. Реализовать метод printCats, он должен вывести на экран всех котов, которые остались во множестве. Каждый кот с новой строки.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Set<Cat> cats = createCats();

        //напишите тут ваш код. пункт 3
        Iterator<Cat> iterator = cats.iterator();
        for (int i = 0; i < 1; i++) {
            String name = iterator.next().getName();
            iterator.remove();
//            System.out.println(name + " is removed");
        }
        printCats(cats);
    }

    public static Set<Cat> createCats() {
        //напишите тут ваш код. пункт 2
        Set<Cat> tmp_cats = new HashSet<Cat>();
        for (int i = 0; i < 3; i++) {
            tmp_cats.add(new Cat("Cat"+i, i));
        }
        return tmp_cats;
    }
    public static void printCats(Set<Cat> cats) {
        // пункт 4
        for (Cat cat: cats) System.out.println(cat);
    }
    // пункт 1
    public static class Cat {
        private String name;
        private int age = 0;

        public Cat(String name) {
            this.name = name;
        }

        public Cat(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

/*
        public int getAge() {
            return age;
        }
*/

/*
        public String toString(){
            return "Cat " + name + " age is " + age;
        }
*/
    }
}
