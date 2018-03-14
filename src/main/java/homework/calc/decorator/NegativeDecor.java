package homework.calc.decorator;

import homework.calc.dataType.Formula;
import homework.calc.dataType.FormulaElement;

public class NegativeDecor extends Decorator {

    public NegativeDecor(Formula src) {
        super(src);
    }

    @Override
    protected void replaceElements() {
        int index1 = (int) genNumber(1, opndCount, 0);
        int index2 = (int) genNumber(1, opndCount, 0);
        int count = 0;
        for (int i = 0; i < formula.size(); i++) {
            if (!formula.get(i).isOptr) {
                count++;
                if (count == index1) {
                    formula.set(i, new FormulaElement(
                            genNumber(minSetByUser, 0)
                    ));
                } else if (count == index2) {
                    formula.set(i, new FormulaElement(
                            genNumber(minSetByUser, 0)
                    ));
                }
            }
        }
    }

    @Override
    public String getStringFromElement(FormulaElement element) {
        if (element.isNegtive) {
            return "(" + src.getStringFromElement(element) + ")";
        } else {
            return src.getStringFromElement(element);
        }
    }
}
