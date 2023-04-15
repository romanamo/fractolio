package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.math.DVector2D;

import java.util.function.Function;

public class JuliaEvaluator extends FunctionSetEvaluator<DVector2D> {

    private Function<DVector2D, DVector2D> function;

    public JuliaEvaluator(int maxIteration, Function<DVector2D, DVector2D> function) {
        super(maxIteration, dVector2D -> dVector2D.distance(DVector2D.ZERO) > 2);
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

    public void setFunction(Function<DVector2D, DVector2D> function) {
        this.function = function;
    }
}
