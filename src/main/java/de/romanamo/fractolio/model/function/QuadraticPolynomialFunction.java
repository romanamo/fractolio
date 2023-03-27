package de.romanamo.fractolio.model.function;

import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;

public class QuadraticPolynomialFunction extends PolynomialFunction {
    public QuadraticPolynomialFunction(Apcomplex c) {
        super(c);
    }

    @Override
    public Apcomplex apply(Apcomplex apcomplex) {
        return ApcomplexMath.pow(apcomplex, 2).add(this.c);
    }
}
