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
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        for (Word word : detectAllWords(crossword, "home", "same", "eejj", "sf", "vok", "glp", "df")) {
            System.out.println(word);
        }

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
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
                        for (int direction = 0; direction < 8; direction++)
                            if (endPointFound(crossword, current, direction)) list.add(current);
                    }
                }
            }
        }
        return list;
    }

    private static boolean endPointFound(int[][] crossword, Word word, int direction) {
        String text = word.text;
        int X = word.startX, x = X;
        int Y = word.startY, y = Y;
        int index = 0;
        // Поиск по оси X
        if (direction == 0) {
            for (; x < crossword[0].length; x++, index++) {
                if (crossword[y][x] != text.charAt(index)) return false;
                if (index == text.length()-1) {
                    word.setEndPoint(x, y);
                    return true;
                }
            }
        }
        // Поиск по оси XY
        else if (direction == 1) {
            for (; x < crossword[0].length && y < crossword.length; x++, y++, index++) {
                if (crossword[y][x] != text.charAt(index)) return false;
                if (index == text.length()-1) {
                    word.setEndPoint(x, y);
                    return true;
                }
            }
        }
        // Поиск по оси Y
        else if (direction == 2) {
            for (; y < crossword.length; y++, index++) {
                if (crossword[y][x] != text.charAt(index)) return false;
                if (index == text.length()-1) {
                    word.setEndPoint(x, y);
                    return true;
                }
            }
        }
        // Поиск по оси -XY
        else if (direction == 3) {
            for (; x >= 0 && y < crossword.length ; x--, y++, index++) {
                if (crossword[y][x] != text.charAt(index)) return false;
                if (index == text.length()-1) {
                    word.setEndPoint(x, y);
                    return true;
                }
            }
        }
        // Поиск по оси -X
        else if (direction == 4) {
            for (; x >= 0 ; x--, index++) {
                if (crossword[y][x] != text.charAt(index)) return false;
                if (index == text.length()-1) {
                    word.setEndPoint(x, y);
                    return true;
                }
            }
        }
        // Поиск по оси -X-Y
        else if (direction == 5) {
            for (; x >= 0 && y >= 0 ; x--, y--, index++) {
                if (crossword[y][x] != text.charAt(index)) return false;
                if (index == text.length()-1) {
                    word.setEndPoint(x, y);
                    return true;
                }
            }
        }
        // Поиск по оси -Y
        else if (direction == 6) {
            for (; y >= 0 ; y--) {
                if (crossword[y][x] != text.charAt(index)) return false;
                if (index == text.length()-1) {
                    word.setEndPoint(x, y);
                    return true;
                }
                index++;
            }
        }
        // Поиск по оси X-Y
        else if (direction == 7) {
            for (; x < crossword[0].length && y >= 0 ; x++, y--, index++) {
                if (crossword[y][x] != text.charAt(index)) return false;
                if (index == text.length()-1) {
                    word.setEndPoint(x, y);
                    return true;
                }
            }
        }
        return false;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
