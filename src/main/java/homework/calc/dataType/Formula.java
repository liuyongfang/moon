package homework.calc.dataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Formula {

    public final static int opndCount = 5;
    public final static String plus = "+";
    public final static String minus = "-";
    public final static String multiply = "*";
    public final static String divide = "/";
    public final static String exp = "^";
    public final static String left_bracket = "{";
    public final static String right_bracket = "}";
    public final static String negative = "-";
    public final static String ends = "#";

    public Stack<String> optrs = new Stack<>();
    public Stack<Double> opnds = new Stack<>();

    public List<FormulaElement> formula = new ArrayList<>();
    public int minSetByUser;
    public int maxSetByUser;
    public String userAnswer;
    public String correctAnswer;

    public Formula(int min, int max) {
        this.minSetByUser = min;
        this.maxSetByUser = max;
        min = min > -1 ? min : 0;
        max = max > min ? max : (min + 10);
        FormulaElement element;
        for (int i = 0; i < opndCount - 1; i++) {
            element = new FormulaElement(genNumber(min, max));
            formula.add(element);
            if (genNumber(1, 2) == 1) {
                element = new FormulaElement(plus);
            } else {
                element = new FormulaElement(minus);
            }
            formula.add(element);
        }
        element = new FormulaElement(genNumber(min, max));
        formula.add(element);
    }

    public String getFormulaString() {
        StringBuilder sb = new StringBuilder();
        for (FormulaElement element : formula) {
            sb.append(" ");
            sb.append(getStringFromElement(element));
        }
        return sb.toString();
    }

    public double getFormulaValue() {

        return 0;
    }

    public static double genNumber(int min, int max, int decimalCount) {
        decimalCount = decimalCount > -1 ? decimalCount : 0;
        int span = max - min;
        double scale = Math.pow(10, decimalCount);
        double result = Math.round(Math.random() * span * scale) / scale + min;
        if (decimalCount == 0) {
            return result;
        }
        String s = String.valueOf(result);
        if (s.length() - s.indexOf(".") - 1 != decimalCount) {
            result = genNumber(min, max, decimalCount);
        }
        return result;
    }

    public static int genNumber(int min, int max) {
        int span = max - min;
        return (int) (Math.round(Math.random() * span) + min);
    }

    public double evaluate() {
        optrs.push(ends);
        formula.add(formula.size(), new FormulaElement(ends));
        FormulaElement newElement = formula.get(0);
        int eleCount = 1;
        String stackTop = optrs.peek();
        for (; !ends.equals(newElement.optr) || !ends.equals(stackTop); stackTop = optrs.peek()) {
            if (!newElement.isOptr) {
                opnds.push(newElement.opnd);
                newElement = formula.get(eleCount++);
            } else {
                stackTop = optrs.peek();
                switch (precede(stackTop, newElement.optr)) {
                    case '<':
                        optrs.push(newElement.optr);
                        newElement = formula.get(eleCount++);
                        break;
                    case '=':
                        optrs.pop();
                        newElement = formula.get(eleCount++);
                        break;
                    case '>':
                        String o = optrs.pop();
                        double b = opnds.pop();
                        double a = opnds.pop();
                        opnds.push(operate(a, o, b));
                        break;

                }
            }
        }
        return opnds.pop();
    }

    public static double operate(double a, String optr, double b) {
        double i, j, result = 0;
        i = a;
        j = b;

        switch (optr) {
            case plus:
                result = i + j;
                break;
            case minus:
                result = i - j;
                break;
            case multiply:
                result = i * j;
                break;
            case divide:
                result = i / j;
                break;
        }
        return result;
    }

    public static char precede(String a, String b) {
        int i = 0, j = 0;
        char[][] pre = {
                {'>', '>', '<', '<', '<', '>', '>'},
                {'>', '>', '<', '<', '<', '>', '>'},
                {'>', '>', '>', '>', '<', '>', '>'},
                {'>', '>', '>', '>', '<', '>', '>'},
                {'<', '<', '<', '<', '<', '=', '0'},
                {'>', '>', '>', '>', '0', '>', '>'},
                {'<', '<', '<', '<', '<', '0', '='}};
        switch (a) {
            case plus:
                i = 0;
                break;
            case minus:
                i = 1;
                break;
            case multiply:
                i = 2;
                break;
            case divide:
                i = 3;
                break;
            case left_bracket:
                i = 4;
                break;
            case right_bracket:
                i = 5;
                break;
            case ends:
                i = 6;
                break;
        }
        switch (b) {
            case plus:
                j = 0;
                break;
            case minus:
                j = 1;
                break;
            case multiply:
                j = 2;
                break;
            case divide:
                j = 3;
                break;
            case left_bracket:
                j = 4;
                break;
            case right_bracket:
                j = 5;
                break;
            case ends:
                j = 6;
                break;
        }
        return pre[i][j];
    }

    public String getStringFromElement(FormulaElement element) {
        if (element.isOptr) {
            return element.optr;
        } else {
            if (element.isInt) {
                return String.valueOf((int) element.opnd);
            } else {
                return String.valueOf(element.opnd);
            }
        }

    }

}
