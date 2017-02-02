package com.javarush.test.level07.lesson04.task05;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Один большой массив и два маленьких
1. Создать массив на 20 чисел.
2. Ввести в него значения с клавиатуры.
3. Создать два массива на 10 чисел каждый.
4. Скопировать большой массив в два маленьких: половину чисел в первый маленький, вторую половину во второй маленький.
5. Вывести второй маленький массив на экран, каждое значение выводить с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        int[] big_array = new int[20];
        int[] small_array1 = new int[10];
        int[] small_array2 = new int[10];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < big_array.length; i++) {
            big_array[i] = Integer.parseInt(br.readLine());
            if (i < 10)
                small_array1[i] = big_array[i];
            else
                small_array2[i-10] = big_array[i];
        }

        for (int N: small_array2) {
            System.out.println(N);
        }
    }
}
