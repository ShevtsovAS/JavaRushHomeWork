package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        for (String s : getFileTree("/home/sas/folder")) {
            System.out.println(s);
        }
    }
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.add(new File(root));
        while (!queue.isEmpty()) {
            File child = queue.poll();
            if (child.isDirectory()) {
                for (File file : child.listFiles()) {
                    queue.add(file);
                }
            }
            else result.add(child.getAbsolutePath());
        }
        return result;
    }
}
