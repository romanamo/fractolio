package de.romanamo.fractolio.model.function;

import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

/**
 * Class describing {@link Metric} for {@link Apcomplex Complex Numbers} or e.g. a 2-dimensional Vector.
 * Mathematical speaking a Metric {@code d} must have the following properties.
 * <ol>
 *     <li>
 *         The Distance from a point to itself is zero
 *         {@code d(x,x) = 0}
 *     </li>
 *     <li>
 *         Positivity: The distance between two distinct points is always positive.
 *         {@code x != y -> d(x,y) > 0}
 *     </li>
 *     <li>
 *         Symmetry: The distance from x to y is always the same as the distance from y to x.
 *         {@code d(x,y) == d(y,x)}
 *     </li>
 *     <li>
 *         Triangle Inequality: holds
 *         {@code d(x,z) <= d(x,y) + d(y,z)}
 *     </li>
 * </ol>
 * For any further reading <a href="https://en.wikipedia.org/wiki/Metric_space">See on Wikipedia...</a>
 *
 * @author romanamo
 * @version 1.0.0
 */
public interface Metric {

    /**
     * Definition of the Distance from point {@code a} to point {@code b}.
     *
     * @param a first point
     * @param b second point
     * @return the distance between both of the points
     */
    public Apfloat distance(Apcomplex a, Apcomplex b);

    /**
     * Distance from point {@code a} to the zero Element / Null vector.
     *
     * @param a first point
     * @return the distance between the zero element and the given point
     */
    default Apfloat distance(Apcomplex a) {
        return this.distance(a, Apcomplex.ZERO);
    }
}
