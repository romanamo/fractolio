package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.model.evaluator.EvaluationContents;
import de.romanamo.fractolio.model.function.ComplexFunction;
import org.apfloat.Apcomplex;

public interface SetEvaluator<F extends ComplexFunction> {

    public EvaluationContents evaluate(F function, Apcomplex z);
}
