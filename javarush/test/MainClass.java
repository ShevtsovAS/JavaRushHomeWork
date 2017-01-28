package com.javarush.test;

/**
 * Created by sas on 28.01.17.
 */
public class MainClass {
    public static void main(String[] args)
    {
        int n = 5;
        int sum = 0;

        for (int i = 0; i< n; i++)
        {
            sum += i;
        }

        System.out.println(sum);
    }
}
