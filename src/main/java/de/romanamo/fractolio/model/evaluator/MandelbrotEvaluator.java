package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.model.math.DVector2D;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h1>MandelbrotEvaluator</h1>
 *
 * <p>
 * The Mandelbrot set is the set of values of <code>c</code> in the complex plane
 * for which the orbit of the critical point z = 0 under iteration of the quadratic map
 * <code>z_{n+1} = z_{n}^{2} + c</code> remains bounded.
 * </p>
 */
public class MandelbrotEvaluator extends FunctionSetEvaluator<DVector2D> {

    /**
     * Constructor of {@link MandelbrotEvaluator}.
     *
     * @param maxIteration maximum Iteration
     */
    public MandelbrotEvaluator(int maxIteration) {
        super(maxIteration);
    }

    @Override
    public Function<DVector2D, DVector2D> getFunction(DVector2D element) {
        return dVector2D -> dVector2D.multiply(dVector2D).add(element);
    }

    @Override
    public DVector2D getInitial(DVector2D element) {
        return DVector2D.ZERO;
    }

    @Override
    public Predicate<DVector2D> getEscapeCondition() {
        return dVector2D -> dVector2D.distance(DVector2D.ZERO) > 2;
    }
}
