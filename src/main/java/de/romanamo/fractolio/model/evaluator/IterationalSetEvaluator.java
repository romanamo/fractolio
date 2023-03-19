package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.model.function.Metric;
import de.romanamo.fractolio.model.function.ComplexFunction;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

public class IterationalSetEvaluator implements SetEvaluator {

    private final long maxIteration;

    private final Apfloat escapeRadii;

    private final Metric metric;

    public IterationalSetEvaluator(long maxIteration, Apfloat escapeRadii, Metric metric) {
        this.maxIteration = maxIteration;
        this.escapeRadii = escapeRadii;
        this.metric = metric;
    }

    @Override
    public EvaluationContents evaluate(ComplexFunction function, Apcomplex z) {
        long iteration = 0;
        Apcomplex num = z;

        //Even more abstraction is possible here by changing the criteria why a number is contained in the Set
        while (metric.distance(num).compareTo(this.escapeRadii) < 0 && iteration < this.maxIteration) {
            num = function.apply(num);
            iteration++;
        }
        return new EvaluationContents(iteration, this.maxIteration);

    }
}
