package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        ArrayList<Human> children = new ArrayList<>();
        ArrayList<Human> parents = new ArrayList<>();
        ArrayList<Human> grParents = new ArrayList<>();
        ArrayList<Human> family = new ArrayList<>();
        children.add(new Human("Диана", false, 5, null));
        children.add(new Human("Даниил", true, 0, null));
        children.add(new Human("Илья", true, 8, null));
        parents.add(new Human("Александр", true, 30 , children));
        parents.add(new Human("Кристина", false, 30, children));
        grParents.add(new Human("Сергей", true, 59, parents));
        grParents.add(new Human("Алексей", true, 55, parents));
        grParents.add(new Human("Елена", false, 58, parents));
        grParents.add(new Human("Наташа", false, 57, parents));
        for (Human human: grParents) family.add(human);
        for (Human human: parents) family.add(human);
        for (Human human: children) family.add(human);
        for (Human human: family) System.out.println(human);
    }

    public static class Human
    {
        //напишите тут ваш код
        private String name;
        private boolean sex;
        private int age;
        private ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount;
            if (this.children != null) childCount = this.children.size();
            else childCount = 0;
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
