package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Human gr_father1 = new Human("Мефодий", true, 90, null, null);
        Human gr_father2 = new Human("Махор", true, 92, null, null);
        Human gr_mother1 = new Human("Валя", false, 91, null, null);
        Human gr_mother2 = new Human("Галя", false, 89, null, null);
        Human father = new Human("Сергей", true, 55, gr_father1, gr_mother1);
        Human mother = new Human("Елена", false, 52, gr_father2, gr_mother2);
        Human child1 = new Human("Тоня", false, 34, father, mother);
        Human child2 = new Human("Настя", false, 32, father, mother);
        Human child3 = new Human("Александр", true, 30, father, mother);
        ArrayList<Human> myFamily = new ArrayList<>();
        myFamily.add(gr_father1);
        myFamily.add(gr_father2);
        myFamily.add(gr_mother1);
        myFamily.add(gr_mother2);
        myFamily.add(father);
        myFamily.add(mother);
        myFamily.add(child1);
        myFamily.add(child2);
        myFamily.add(child3);
        for (Human human: myFamily) System.out.println(human);
    }

    public static class Human
    {
        //напишите тут ваш код
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
