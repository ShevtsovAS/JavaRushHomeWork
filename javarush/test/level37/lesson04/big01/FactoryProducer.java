package com.javarush.test.level37.lesson04.big01;

import com.javarush.test.level37.lesson04.big01.female.FemaleFactory;
import com.javarush.test.level37.lesson04.big01.male.MaleFactory;

/**
 * Created by alexandr on 10.01.17.
 */
public class FactoryProducer {
    public enum HumanFactoryType {
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType type) {
        AbstractFactory factory = null;
        switch (type) {
            case MALE: factory = new MaleFactory(); break;
            case FEMALE: factory =  new FemaleFactory(); break;
        }
        return factory;
    }
}
