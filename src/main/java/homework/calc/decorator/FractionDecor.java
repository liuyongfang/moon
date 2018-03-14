package homework.calc.decorator;

import homework.calc.dataType.Formula;
import homework.calc.dataType.FormulaElement;

public class FractionDecor extends Decorator {
    public FractionDecor(Formula src) {
        super(src);
    }

    @Override
    protected void replaceElements() {
        int index1 = genNumber(1, opndCount);
        int count = 0;
        for (int i = 0; i < formula.size(); i++) {
            if (!formula.get(i).isOptr) {
                count++;
                if (count == index1) {
                    formula.set(i, new FormulaElement(
                            genNumber(minSetByUser, maxSetByUser),
                            genNumber(minSetByUser, maxSetByUser)
                    ));
                }
            }
        }
    }

    @Override
    public String getStringFromElement(FormulaElement element) {
        if (element.isFraction) {
            return "[" + element.numerator + "/" + element.denominator + "]";
        } else {
            return src.getStringFromElement(element);
        }
    }
}
