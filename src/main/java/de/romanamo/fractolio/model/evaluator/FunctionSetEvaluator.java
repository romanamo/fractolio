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
public class FunctionSetEvaluator<E> implements IterationSetEvaluator<E> {

    private int maxIteration;
    private final Predicate<E> escapeCondition;

    private final Function<E, E> function;


    public FunctionSetEvaluator(int maxIteration, Function<E, E> function, Predicate<E> escapeCondition) {
        this.maxIteration = maxIteration;
        this.function = function;
        this.escapeCondition = escapeCondition;
    }

    @Override
    public int getIteration(E element) {
        //set start values for the evaluating process
        int iteration = 0;
        E num = element;
        //return if maxIteration has been reached or the escapeCondition has been met
        while (!this.escapeCondition.test(num) && iteration < this.maxIteration) {
            //pass through iteration by applying function
            num = this.function.apply(num);
            iteration++;
        }
        return iteration;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    @Override
    public int getMaxIteration() {
        return maxIteration;
    }
}
