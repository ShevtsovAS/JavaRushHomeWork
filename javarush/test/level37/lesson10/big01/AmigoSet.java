package com.javarush.test.level37.lesson10.big01;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sas on 12.01.17.
 */
public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
