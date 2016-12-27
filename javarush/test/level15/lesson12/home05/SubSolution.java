package com.javarush.test.level15.lesson12.home05;

/**
 * Created by SAS on 26.03.2016.
 */
public class SubSolution extends Solution {
    SubSolution(int n) {
        super(n);
    }

    SubSolution(Short s) {
        super(s);
    }

    SubSolution(boolean b) {
        super(b);
    }

    protected SubSolution(String s) {
        super(s);
    }

    protected SubSolution(byte b) {
        super(b);
    }

    protected SubSolution(char c) {
        super(c);
    }

    public SubSolution(Integer n) {
        super(n);
    }

    public SubSolution(Float f) {
        super(f);
    }

    public SubSolution(Double d) {
        super(d);
    }
    private SubSolution (long l) {
        super((byte) l);
    }
    private SubSolution (Boolean b) {
        super(b);
    }
    private SubSolution (short s) {
        super(s);
    }
}
