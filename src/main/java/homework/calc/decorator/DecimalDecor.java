package homework.calc.decorator;

import homework.calc.dataType.Formula;
import homework.calc.dataType.FormulaElement;

public class DecimalDecor extends Decorator {
    public DecimalDecor(Formula src) {
        super(src);
    }

    @Override
    protected void replaceElements() {
        int index1 = genNumber(1, opndCount);
        int index2 = genNumber(1, opndCount);
        int count = 0;
        for (int i = 0; i < formula.size(); i++) {
            if (!formula.get(i).isOptr) {
                count++;
                if (count == index1) {
                    formula.set(i, new FormulaElement(
                            genNumber(minSetByUser, maxSetByUser,
                                    genNumber(1, 2))
                    ));
                } else if (count == index2) {
                    formula.set(i, new FormulaElement(
                            genNumber(minSetByUser, maxSetByUser,
                                    genNumber(1, 2))
                    ));
                }
            }
        }

    }
}
