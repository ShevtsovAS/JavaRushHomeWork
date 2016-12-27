package com.javarush.test.level20.lesson10.bonus01;

import java.util.*;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static void main(String[] args) {
        long freeMemory = Runtime.getRuntime().freeMemory();
        long startTime = System.currentTimeMillis();
        int[] armstrongs = getNumbers(Integer.MAX_VALUE);
        long memoryEnd = Runtime.getRuntime().freeMemory();
        long delta = (memoryEnd - freeMemory)/1024/1024;
        long stopTime = System.currentTimeMillis();
        for (int armstrong : armstrongs) System.out.print(armstrong + " ");
        System.out.println();
        System.out.println("Calculating time: " + (stopTime - startTime)/1000);
        System.out.println("Used JVM memory: " + delta);
    }
    public static int[] getNumbers(int N) {
        int[] result;
        // Заполняем таблицу степеней
        long[][] SQRT = new long[10][11];
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 10; j++) {
                SQRT[i][j] = (long) Math.pow(i,j);
            }
        }

        List<Integer> armstrongs = new ArrayList<>();
        for (int S = 1; S < N; S++) {
            if (S < 10) {
                armstrongs.add(S);
                continue;
            }
            byte digit;
            int temp = S;
            long sum = 0;
            byte digitCount = (byte)(Math.log10(S)+1);
            while (temp > 0) {
                digit = (byte)(temp%10);
                long digitSQRT = SQRT[digit][digitCount];
                sum += digitSQRT;
                if (sum > S) break;
                temp/=10;
            }
            if (sum == S) {
                armstrongs.add(S);
                System.out.println(S);
            }
        }
        result = new int[armstrongs.size()];
        for (int i = 0; i < armstrongs.size(); i++) result[i] = armstrongs.get(i);
        return result;
    }
}
