package com.javarush.test.level05.lesson05.task02;

/* Реализовать метод fight
Реализовать метод boolean fight(Cat anotherCat):
реализовать механизм драки котов в зависимости от их веса, возраста и силы.
Зависимость придумать самому. Метод должен определять, выиграли ли мы (this) бой или нет,
т.е. возвращать true, если выиграли и false - если нет.
Должно выполняться условие:
если cat1.fight(cat2) = true , то cat2.fight(cat1) = false
*/

public class Cat
{
    public String name;
    public int age;
    public int weight;
    public int strength;

    public Cat()
    {
        this.name = "NoName";
        this.age = 0;
        this.weight = 500;
        this.strength = 1;
    }

    public boolean fight(Cat anotherCat)
    {
        //напишите тут ваш код
        int myChance = this.age + this.weight + this.strength;
        int anotherCatChance = anotherCat.age + anotherCat.weight + anotherCat.strength;
        return myChance > anotherCatChance;
    }

    public static void main(String[] args) {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        cat1.name = "Vaska";
        cat2.name = "Barsik";
        cat1.strength = 5;
        cat2.strength = 10;
        if (cat1.fight(cat2))
            System.out.println("Победил " + cat1.name);
        else
            System.out.println("Победил " + cat2.name);
    }
}
