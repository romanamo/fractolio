package de.romanamo.fractolio.model.evaluator;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h1>FunctionSetEvaluator</h1>
 *
 * {@link IterationSetEvaluator} that evaluates a given element,
 * by applying a function to the element multiple times
 *
 * @param <E> Element of the Function
 */
public abstract class FunctionSetEvaluator<E> implements IterationSetEvaluator<E> {

    private int maxIteration;
    private final Predicate<E> escapeCondition;

    public FunctionSetEvaluator(int maxIteration, Predicate<E> escapeCondition) {
        this.maxIteration = maxIteration;
        this.escapeCondition = escapeCondition;
    }

    @Override
    public int getIteration(E element) {
        //set start values for the evaluating process
        int iteration = 0;
        E num = this.getInitial(element);
        //return if maxIteration has been reached or the escapeCondition has been met
        while (!this.escapeCondition.test(num) && iteration < this.maxIteration) {
            //pass through iteration by applying function
            num = this.getFunction(element).apply(num);
            iteration++;
        }
        return iteration;
    }
    public abstract Function<E, E> getFunction(E element);
    public abstract E getInitial(E element);

    @Override
    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    @Override
    public int getMaxIteration() {
        return maxIteration;
    }
}
