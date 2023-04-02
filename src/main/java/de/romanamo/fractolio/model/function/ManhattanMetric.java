package de.romanamo.fractolio.model.function;

import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class ManhattanMetric implements Metric{
    @Override
    public Apfloat distance(Apcomplex a, Apcomplex b) {
        return ApfloatMath.abs(a.real().subtract(b.real())).add(ApfloatMath.abs(a.imag().subtract(b.imag())));
    }
}
