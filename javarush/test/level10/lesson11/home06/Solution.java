package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        private String name;
        private int age = 0;
        private float weight = 3.5f;
        private int height = 54;
        private Human mother;
        private Human father;

        public Human(String name) {
            this.name = name;
        }

        public Human(int age) {
            this.age = age;
        }

        public Human(int age, int height) {
            this.age = age;
            this.height = height;
        }

        public Human(float weight) {
            this.weight = weight;
        }


        public Human(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Human(String name, int age, float weight) {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }

        public Human(String name, int age, float weight, int height, Human mother) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.mother = mother;
        }

        public Human(String name, int age, float weight, int height, Human mother, Human father) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.mother = mother;
            this.father = father;
        }

        public Human(String name, Human mother, Human father) {
            this.name = name;
            this.mother = mother;
            this.father = father;
        }

        public Human(String name, int age, Human mother, Human father) {
            this.name = name;
            this.age = age;
            this.mother = mother;
            this.father = father;
        }
    }
}
