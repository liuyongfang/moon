package homework.calc.decorator;

import homework.calc.dataType.Formula;
import homework.calc.dataType.FormulaElement;

public class BracketDecor extends Decorator {

    public BracketDecor(Formula src) {
        super(src);
    }

    @Override
    protected void replaceElements() {
        int index1 = genNumber(1, opndCount - 1);
        int count = 0;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < formula.size(); i++) {
            if (!formula.get(i).isOptr) {
                count++;
                if (count == index1) {
                    count1 = i;
                } else if (count == index1 + 1) {
                    count2 = i;
                }
            }
        }
        formula.add(count1, new FormulaElement(left_bracket));
        formula.add(count2 + 2, new FormulaElement(right_bracket));
    }

}
