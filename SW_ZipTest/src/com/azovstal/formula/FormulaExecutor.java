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
        System.out.println("���������: " + res);
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
        System.out.println("������� = " + formula);
        return eval(formula);
    }
 
    boolean isDelim(char c) { // ��� ���� ������
        return c == ' ';
    }
 
    boolean isOperator(char c) { // ���������� ��� ���� ���� �� ��������
                                    // ����
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }
 
    // ������� �������������� �������� �� ���������� ����������
    int priority(char op) {
        switch (op) { // ��� + ��� - ������� 1, ��� * / % 2 ����� -1
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
        // ���������� �� �������������� ����� ��������� �������
        int r = st.removeLast();
        int l = st.removeLast();
        // ��������� �������� ����� l � r � ����������� �� ��������� � ����� �
        // ��������� ����� � st
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
        // ���� ���������� �����
        LinkedList<Integer> st = new LinkedList<Integer>();
        // ���� ���������� � st � op � ������� �����������
        LinkedList<Character> op = new LinkedList<Character>();
        // ������ ������ � ���������� � ���������
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
        // ������� ����������
        return st.get(0);
    }
 
}