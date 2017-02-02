package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by sas on 03.12.16.
 */
public class Entry<key, value> implements Serializable {
    int hash;
    Long key;
    String value;
    Entry<key, value> next;

    public Entry(int hash, Long key, String value, Entry<key, value> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return hash ^ Objects.hashCode(value);
    }

    @Override
    public java.lang.String toString() {
        return key + "=" + value;
    }
}
