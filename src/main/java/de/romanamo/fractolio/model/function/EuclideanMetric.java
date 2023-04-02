package de.romanamo.fractolio.model.function;

import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

/**
 * Class describing the 2-dimensional Euclidean Metric.
 * Formally the euclidean metric is defined for two points p and q
 * with p = (p_1, p_2) and q = (q_1, q_2).
 * <p>
 * {@code d(p,q) = sqrt((q_1 - p_1)^2 + (q_2 - p_2)^2)}
 *
 * @author romanamo
 * @version 1.0.0
 * @see Metric
 */
public class EuclideanMetric implements Metric {
    @Override
    public Apfloat distance(Apcomplex a, Apcomplex b) {
        //Calculate distance between a and b using the Pythagorean theorem
        Apfloat real = ApfloatMath.pow(a.real().subtract(b.real()), 2);
        Apfloat imag = ApfloatMath.pow(a.imag().subtract(b.imag()), 2);

        return ApfloatMath.sqrt(real.add(imag));
    }

    @Override
    public Apfloat distance(Apcomplex a) {
        //Rounding is heavily needed here else we would get a problem
        //TODO implement an option to define the maximum precision instead of being hardcoded
        return ApcomplexMath.abs(a.precision(32));
    }
}
