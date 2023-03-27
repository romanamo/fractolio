package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.model.function.ComplexFunction;
import org.apfloat.Apcomplex;

public interface SetEvaluator {

    public EvaluationContents evaluate(ComplexFunction function, Apcomplex z);
}
