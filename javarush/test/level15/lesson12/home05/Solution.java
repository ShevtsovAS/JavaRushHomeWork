package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    Solution(int n) {}
    Solution(Short s) {}
    Solution(boolean b) {}
    protected Solution(String s) {}
    protected Solution(byte b) {}
    protected Solution(char c) {}
    public Solution(Integer n) {}
    public Solution(Float f) {}
    public Solution(Double d) {}
    private Solution (long l) {}
    private Solution (Boolean b) {}
    private Solution (short s) {}
}
