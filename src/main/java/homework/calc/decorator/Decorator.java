package homework.calc.decorator;

import homework.calc.dataType.Formula;
import homework.calc.dataType.FormulaElement;

public abstract class Decorator extends Formula {

    protected Formula src;

    public Decorator(Formula src) {
        super(src.minSetByUser, src.maxSetByUser);
        this.src = src;
        this.formula.clear();
        this.formula.addAll(src.formula);
        this.replaceElements();
    }

    protected abstract void replaceElements();

    public String getStringFromElement(FormulaElement element) {
        return src.getStringFromElement(element);
    }
}
