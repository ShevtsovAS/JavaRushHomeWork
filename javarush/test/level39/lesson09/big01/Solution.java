package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("src/com/javarush/test/level39/lesson09/big01/logs/"));
//        System.out.println(logParser.getLoggedUsers(null, null));
        System.out.println(logParser.execute("get event for date = \"03.01.2014 03:45:23\""));
    }
}
