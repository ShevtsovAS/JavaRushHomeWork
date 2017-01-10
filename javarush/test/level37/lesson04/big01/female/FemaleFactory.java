package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by alexandr on 10.01.17.
 */
public class FemaleFactory {
    public Human getPerson(int age) {
        if (age <= KidGirl.MAX_AGE ) return new KidGirl();
        else if (age <= TeenGirl.MAX_AGE) return new TeenGirl();
        return new Woman();
    }
}
