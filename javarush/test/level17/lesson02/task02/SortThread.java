package com.javarush.test.level17.lesson02.task02;

/**
 * Created by SAS on 09.04.2016.
 */
public class SortThread extends Thread {
    @Override
    public void run() {
        Solution.sort(Solution.testArray);
    }
}
