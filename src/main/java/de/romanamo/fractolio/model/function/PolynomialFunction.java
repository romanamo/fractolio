package de.romanamo.fractolio.model.function;

import de.romanamo.fractolio.model.function.ComplexFunction;
import org.apfloat.Apcomplex;

public abstract class PolynomialFunction implements ComplexFunction {

    protected Apcomplex c;


    public PolynomialFunction(Apcomplex c) {
        this.c = c;
    }
}
