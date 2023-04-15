package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.math.DVector2D;

import java.util.function.Function;
import java.util.function.Predicate;

public class EvaluatorFactory {

    public FunctionSetEvaluator<DVector2D> getEvaluator() {
        Predicate<DVector2D> escapeCondition = dVector2D -> dVector2D.distance(DVector2D.ZERO) > 2;
        Function<DVector2D, DVector2D> function = dVector2D -> dVector2D.add(DVector2D.UP);
        return new FunctionSetEvaluator<>(10, function, escapeCondition);

    }
}
