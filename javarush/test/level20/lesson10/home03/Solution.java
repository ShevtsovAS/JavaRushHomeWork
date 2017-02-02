package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution implements Serializable{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution solution = new Solution();
        Solution.B b = solution.new B("first");
        System.out.println(b.name);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tmpFile"));
        outputStream.writeObject(b);
        outputStream.flush();
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("tmpFile"));
        Solution.B b2 = (B) inputStream.readObject();
        System.out.println(b2.name);
    }

    public static class A {
        protected String name = "A";

        public A() {
        }

        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {
        public B(String name) {
            super(name);
            this.name += name;

        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeObject(name);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            name = (String) in.readObject();
        }
    }
}
