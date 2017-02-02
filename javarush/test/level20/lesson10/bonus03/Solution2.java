package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution2 {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'e', 'm', 'o', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        for (Word word : detectAllWords(crossword, "home", "same", "mgs", "mel")) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    private static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> list = new ArrayList<>();
        outer: for (String word : words) {
            Word current = new Word(word);
            for (int y = 0; y < crossword.length; y++) {
                for (int x = 0; x < crossword[y].length; x++) {
                    if (crossword[y][x] == word.charAt(0)) {
                        current.setStartPoint(x,y);
                        if (word.length() == 1) {
                            current.setEndPoint(x,y);
                            continue outer;
                        }
                        int[][] direction = new int[][]{{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
                        for (int[] aDirection : direction) {
                            int i = y;
                            int j = x;
                            int index = 0;
                            for (; i >= 0 && j >= 0 && i < crossword.length && j < crossword[i].length;
                                 i += aDirection[0], j += aDirection[1], index++) {
                                if (crossword[i][j] != word.charAt(index)) break;
                                if (index == word.length() - 1) {
                                    current.setEndPoint(j, i);
                                    list.add(current);
                                    current = new Word(word);
                                    current.setStartPoint(x, y);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    private static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        Word(String text) {
            this.text = text;
        }
        void setStartPoint(int i, int j) { startX = i; startY = j; }
        void setEndPoint(int i, int j) { endX = i; endY = j; }
        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
