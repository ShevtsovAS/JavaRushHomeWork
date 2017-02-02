package com.javarush.test.level34.lesson02.home01;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recursion(final String expression, int countOperation) {
        //implement
        Locale.setDefault(Locale.ENGLISH);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Проверяем на null
        if (expression == null) {
            System.out.println("null");
            return;
        }

        // Проверяем не передано ли нам готовое число
        if (isNumber(expression)) {
            double result = Double.parseDouble(expression);
            if (result >= 0) {
                System.out.println(decimalFormat.format(result) + " " + countOperation);
                return;
            }
        }

        List<String> RPN; //Reverse Polish Notation - Здесь будет список в обратной польской нотации
        String newExpression; //Новое выражение для использования в рекурсии
        try {
            // Если выражение уже представленно в виде польской нотации, то формируем нотацию из строки
            if (expression.startsWith("RPN:")) RPN = Arrays.asList(expression.substring(4).split(","));
            // Иначе конвертируем
            else {
                RPN = convertToPostfix(expression);
                countOperation++;
            }

            // Решаем одно подвыражение
            RPN = computeOneSubexpression(RPN);

            // Если остался один операнд, то решение готово, можно выходить из рекурсии
            if (RPN.size() == 1) {
                newExpression = RPN.get(0);
                double result = Double.parseDouble(newExpression);
                System.out.println(decimalFormat.format(result) + " " + countOperation);
                return;
            }
            // Иначе формируем новое выражение и входим с ним в рекурсию
            else {
                StringBuilder builder = new StringBuilder("RPN:");
                for (String s : RPN) {
                    builder.append(s);
                    builder.append(",");
                }
                newExpression = builder.toString();
            }
            recursion(newExpression, ++countOperation);
        } catch (EmptyStackException e) {
            System.out.println("Expression is incorrect!");
        } catch (Exception e) {
            System.out.println("Oops!");
        }
    }

    public Solution() {
        //don't delete
    }

    private List<String> convertToPostfix(String expression) {
        // Указатель на допустимые элементы в выражении
        Matcher pointer = Pattern.compile("\\d+\\.?\\d*|\\+|-|\\*|/|\\^|\\(|\\)|cos|sin|tan").matcher(expression);
        final List<String> RPN = new ArrayList<>(); // Список в виде польской нотации
        final Stack<String> operations = new Stack<>(); // Стэк для расстановки операций по приоритету

        // Карта приоритетов
        Map<String, Integer> priorities = new HashMap<>();
        priorities.put("(", 0);
        priorities.put(")", 0);
        priorities.put("+", 1);
        priorities.put("-", 1);
        priorities.put("*", 2);
        priorities.put("/", 2);
        priorities.put("^", 3);
        priorities.put("cos", 3);
        priorities.put("sin", 3);
        priorities.put("tan", 3);

        boolean checkUnary = true; // флаг проверки на унарную операцию, выставляем его в начале анализа выражения

        while (pointer.find()) {
            String var = pointer.group();

            // Если указатель указывает на число, то ложим его в список
            if (isNumber(var)) {
                RPN.add(var);
                checkUnary = false;
            }
            // Если указатель указывает на открывающую скобку, то ложим его в стек
            else if (var.equals("(")) {
                operations.push(var);
                checkUnary = true; // После открытой скобочки может появится унарный оператор
            }
            // Тригонометрические функции тоже сразу идут в стэк
            else if (isTrigonometric(var)) operations.push(var);
            // Если указатель указывает на закрывающую скобку выталкиваем все операции пока не найдём кто её открыл
            else if (var.equals(")")) {
                while (true) {
                    String operation = operations.pop();
                    if (operation.equals("(")) break;
                    RPN.add(operation);
                }
            }
            // Если указатель указывает на "-", то вначале выражения или после открывающейся скобки это унарный оператор
            // Для его корректной обработки добавляем 0, чтоб превратить его в операцию вычитания
            else if (var.equals("-") && checkUnary) {
                RPN.add("0");
                operations.push(var);
            }
            else {
                while (true) {
                    // Если стэк операций пуст или у текущей операции приоритет больше, то кладём её в стэк
                    if (operations.isEmpty() || priorities.get(var) > priorities.get(operations.peek())) {
                        operations.push(var);
                        break;
                    }
                    // Иначе выталкиваем из стэка последнюю операцию в польскую последовательность
                    RPN.add(operations.pop());
                }
            }
        }

        // После обработки всего выражения выталкиваем в польскую последовательность все оставшиеся в стэке операции
        while (!operations.isEmpty()) {
            String operation = operations.pop();
            if (operation.equals("(")) return null;
            RPN.add(operation);
        }
        return RPN;
    }

    private List<String> computeOneSubexpression(List<String> rpn) {
        Locale.setDefault(Locale.ENGLISH);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        Stack<String> stack = new Stack<>();
        double n1;
        double n2;
        boolean ready = false;
        for (String var : rpn) {
            if (isNumber(var) || ready) {
                stack.push(var);
                continue;
            }
            switch (var) {
                case "+":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(n1+n2));
                    break;
                case "-":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(n1-n2));
                    break;
                case "*":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(n1*n2));
                    break;
                case "/":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(n1/n2));
                    break;
                case "^":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(Math.pow(n1,n2)));
                    break;
                case "sin":
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(Math.sin(Math.toRadians(n1))));
                    break;
                case "cos":
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(Math.cos(Math.toRadians(n1))));
                    break;
                case "tan":
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(Math.tan(Math.toRadians(n1))));
                    break;
            }
            ready = true;
        }
        return stack;
    }

    private List<String> computeRPN(List<String> rpn) {
        Locale.setDefault(Locale.ENGLISH);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        Stack<String> stack = new Stack<>();
        double n1;
        double n2;
        int count = 0;
        for (String var : rpn) {
            if (isNumber(var)) {
                stack.push(var);
                continue;
            }
            switch (var) {
                case "+":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(n1+n2));
                    break;
                case "-":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(n1-n2));
                    break;
                case "*":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(n1*n2));
                    break;
                case "/":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(n1/n2));
                    break;
                case "^":
                    n2 = Double.parseDouble(stack.pop());
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(Math.pow(n1,n2)));
                    break;
                case "sin":
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(Math.sin(Math.toRadians(n1))));
                    break;
                case "cos":
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(Math.cos(Math.toRadians(n1))));
                    break;
                case "tan":
                    n1 = Double.parseDouble(stack.pop());
                    stack.push(decimalFormat.format(Math.tan(Math.toRadians(n1))));
                    break;
            }
            System.out.println(stack);
            count++;
        }
        System.out.println(count);
        return stack;
    }

    private String convertToInfix(List<String> postFix) {
        if (postFix == null) return "null";

        Stack<String> stack = new Stack<>();
        String lastVar = "";
        String expr1;
        String expr2;

        for (String var : postFix) {
            if (isNumber(var)) {
                stack.push(var);
                lastVar = var;
                continue;
            }
            switch (var) {
                case "+":
                case "-":
                    expr2 = stack.pop();
                    expr1 = stack.pop();
                    if (expr2.startsWith("-")) expr2 = "(" + expr2 + ")";
                    String newExpression = expr1 + var + expr2;
                    if (newExpression.startsWith("0-")) newExpression = newExpression.substring(1);
                    stack.push(newExpression);
                    lastVar = var;
                    break;
                case "*":
                case "/":
                    expr2 = stack.pop();
                    expr1 = stack.pop();
                    if (lastVar.matches("[+-]")) expr2 = "(" + expr2 + ")";
                    if (!isNumber(expr1) && !isFunction(expr1)) expr1 = "(" + expr1 + ")";
                    stack.push(expr1 + var + expr2);
                    lastVar = var;
                    break;
                case "^":
                    expr2 = stack.pop();
                    expr1 = stack.pop();
                    if (lastVar.matches("[+-/*]")) expr2 = "(" + expr2 + ")";
                    if (!isNumber(expr1) && !isFunction(expr1)) expr1 = "(" + expr1 + ")";
                    stack.push(expr1 + var + expr2);
                    lastVar = var;
                    break;
                case "sin":
                case "cos":
                case "tan":
                    expr1 = stack.pop();
                    stack.push(var + "(" + expr1 + ")");
                    lastVar = var;
                    break;
            }
        }
        return stack.pop();
    }

    private boolean isFunction(String s) {
        return s.matches("cos\\(-?\\d+\\.?\\d*\\)|sin\\(-?\\d+\\.?\\d*\\)|tan\\(-?\\d+\\.?\\d*\\)");
    }

    private boolean isTrigonometric(String s) {
        return s.matches("cos|sin|tan");
    }

    private boolean isNumber(String s) {
        return s.matches("-?\\d+\\.?\\d*");
    }
}
