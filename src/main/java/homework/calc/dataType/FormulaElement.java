package homework.calc.dataType;

public class FormulaElement {

    public boolean isOptr;
    public String optr;
    public double opnd;
    public boolean isInt;
    public boolean isNegtive;
    public boolean isFraction;
    public int numerator;
    public int denominator;

    public FormulaElement(String optr) {
        isOptr = true;
        this.optr = optr;
    }

    public FormulaElement(double opnd) {
        init(opnd);
    }

    public FormulaElement(int opnd) {
        isInt = true;
        init((double) opnd);
    }

    public FormulaElement(int numerator, int denominator) {
        isFraction = true;
        this.numerator = numerator;
        this.denominator = denominator;
        init(numerator / (double) denominator);
    }

    private void init(double opnd) {
        isOptr = false;
        this.opnd = opnd;
        if (opnd < 0) {
            isNegtive = true;
        }
    }

}
