package com.javarush.test.level25.lesson02.task02;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* Машину на СТО не повезем!
Инициализируйте поле wheels используя данные из loadWheelNamesFromDB.
Обработайте некорректные данные.
Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {
    public static void main(String[] args) {
        Car car = new Car();
        for (Wheel wheel : car.wheels) {
            System.out.println(wheel);
        }
    }
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            wheels = new LinkedList<>();
            Set<String> wheelsSet = new HashSet<>();
            for (String wheel : loadWheelNamesFromDB()) {
                wheelsSet.add(wheel);
            }
            for (String wheelName : wheelsSet) {
                Wheel wheel = Wheel.valueOf(wheelName);
                if (wheel != null) wheels.add(wheel);
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }
}
