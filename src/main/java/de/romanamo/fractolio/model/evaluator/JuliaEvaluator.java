package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.model.math.DVector2D;

import java.util.function.Function;

/**
 * <h1>JuliaEvaluator</h1>
 * <p>
 * Evaluator for JuliaSets
 * </p>
 */
public class JuliaEvaluator extends FunctionSetEvaluator<DVector2D> {

    private final double escapeDistance;
    private final Function<DVector2D, DVector2D> function;

    public JuliaEvaluator(int maxIteration, double escapeDistance, Function<DVector2D, DVector2D> function) {
        super(maxIteration, dVector2D -> dVector2D.distance(DVector2D.ZERO) > escapeDistance);
        this.escapeDistance = escapeDistance;
        this.function = function;
    }

    @Override
    public Function<DVector2D, DVector2D> getFunction(DVector2D element) {
        return this.function;
    }

    @Override
    public DVector2D getInitial(DVector2D element) {
        return element;
    }

    public Function<DVector2D, DVector2D> getFunction() {
        return function;
    }


    public double getEscapeDistance() {
        return this.escapeDistance;
    }
}
