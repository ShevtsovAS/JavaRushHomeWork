package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        //Сортируем массив по возрастанию
        Arrays.sort(array);
        //Ищем медиану
        final double MEDIAN;
        if (array.length % 2 == 0) {
            MEDIAN = ((double)array[array.length/2-1]+(double)array[array.length/2])/2;
        }
        else {
            MEDIAN = array[array.length/2];
        }
        //Сортируе массив по удалённости от медианы
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double n = Math.abs(o1-MEDIAN) - Math.abs(o2-MEDIAN);
                if (n == 0) n = o1 - o2;
                return (int)n;
            }
        });
        return array;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(sort(array)));
    }
}
