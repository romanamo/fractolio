package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.model.math.DVector2D;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h1>JuliaEvaluator</h1>
 * <p>
 * Evaluator for JuliaSets
 * </p>
 */
public class JuliaEvaluator extends FunctionSetEvaluator<DVector2D> {

    private double escapeDistance;
    private final Function<DVector2D, DVector2D> function;

    public JuliaEvaluator(int maxIteration, double escapeDistance, Function<DVector2D, DVector2D> function) {
        super(maxIteration);
        this.setEscapeDistance(escapeDistance);
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

    @Override
    public Predicate<DVector2D> getEscapeCondition() {
        return dVector2D -> dVector2D.distance(DVector2D.ZERO) > escapeDistance;
    }

    public double getEscapeDistance() {
        return this.escapeDistance;
    }

    public void setEscapeDistance(double escapeDistance) {
        if (escapeDistance < 0) {
            throw new IllegalArgumentException(
                    String.format("Maximum Iteration: %f has to be greater or equals Zero", escapeDistance));
        }
        this.escapeDistance = escapeDistance;
    }
}
