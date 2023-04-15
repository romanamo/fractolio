package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.math.DVector2D;

import java.util.function.Function;

public class MandelbrotEvaluator extends FunctionSetEvaluator<DVector2D> {

    public MandelbrotEvaluator(int maxIteration) {
        super(maxIteration, dVector2D -> dVector2D.distance(DVector2D.ZERO) > 2);
    }

    @Override
    public Function<DVector2D, DVector2D> getFunction(DVector2D element) {
        return dVector2D -> dVector2D.pow(2).add(element);
    }

    @Override
    public DVector2D getInitial(DVector2D element) {
        return DVector2D.ZERO;
    }
}
