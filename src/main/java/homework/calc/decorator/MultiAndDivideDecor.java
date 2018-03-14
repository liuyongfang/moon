package homework.calc.decorator;

import homework.calc.dataType.Formula;
import homework.calc.dataType.FormulaElement;

public class MultiAndDivideDecor extends Decorator {
    public MultiAndDivideDecor(Formula src) {
        super(src);
    }

    @Override
    protected void replaceElements() {
        int multiIndex = (int) genNumber(1, opndCount - 1, 0);
        int divideIndex = (int) genNumber(1, opndCount - 1, 0);
        int count = 0;
        for (int i = 0; i < formula.size(); i++) {
            if (formula.get(i).isOptr) {
                count++;
                if (count == multiIndex) {
                    formula.set(i, new FormulaElement(multiply));
                } else if (count == divideIndex) {
                    formula.set(i, new FormulaElement(divide));
                }
            }
        }
    }
}
