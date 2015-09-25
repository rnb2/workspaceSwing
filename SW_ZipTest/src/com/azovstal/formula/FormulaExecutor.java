package com.azovstal.formula;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.LinkedList;
 
public class FormulaExecutor {
 
    public static void main(String[] args) {
        Formula f = new Formula("formula.txt");
        int res = f.parseFormula();
        System.out.println("результат: " + res);
    }
}
 
class Formula {
    InputStreamReader isr;
 
    Formula(String fileName) {
        try {
            isr = new InputStreamReader(new FileInputStream(new File(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public int parseFormula() {
        LineNumberReader lnr = new LineNumberReader(isr);
        String formula = null;
        try {
            formula = lnr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("‘ормула = " + formula);
        return eval(formula);
    }
 
    boolean isDelim(char c) { // тру если пробел
        return c == ' ';
    }
 
    boolean isOperator(char c) { // возвращ€ем тру если один из символов
                                    // ниже
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }
 
    // расклад математических операций по приоритету выполнени€
    int priority(char op) {
        switch (op) { // при + или - возврат 1, при * / % 2 иначе -1
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
        case '%':
            return 2;
        default:
            return -1;
        }
    }
 
    void processOperator(LinkedList<Integer> st, char op) {
        // выдЄргиваем из упор€доченного листа последний элемент
        int r = st.removeLast();
        int l = st.removeLast();
        // выполн€ем действие между l и r в зависимости от оператора в кейсе и
        // результат валим в st
        switch (op) {
        case '+':
            st.add(l + r);
            break;
        case '-':
            st.add(l - r);
            break;
        case '*':
            st.add(l * r);
            break;
        case '/':
            st.add(l / r);
            break;
        case '%':
            st.add(l % r);
            break;
        }
    }
 
    public int eval(String s) {
        // сюда наваливают цифры
        LinkedList<Integer> st = new LinkedList<Integer>();
        // сюда опрераторы и st и op в пор€дке поступлени€
        LinkedList<Character> op = new LinkedList<Character>();
        // парсим строку с выражением и вычисл€ем
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isDelim(c))
                continue;
            if (c == '(')
                op.add('(');
            else if (c == ')') {
                while (op.getLast() != '(')
                    processOperator(st, op.removeLast());
                op.removeLast();
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                    processOperator(st, op.removeLast());
                op.add(c);
            } else {
                String operand = "";
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    operand += s.charAt(i++);
                --i;
                st.add(Integer.parseInt(operand));
            }
        }
        while (!op.isEmpty())
            processOperator(st, op.removeLast());
        // возврат результата
        return st.get(0);
    }
 
}