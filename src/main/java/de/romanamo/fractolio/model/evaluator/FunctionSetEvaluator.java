package de.romanamo.fractolio.model.evaluator;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h1>FunctionSetEvaluator</h1>
 * <p>
 * The FunctionSetEvaluator class evaluates a given element,
 * by applying a function to the element multiple times.
 * The given element is part of the set,
 * if the supplied escaping condition has not been met.
 * </p>
 *
 * @param <E> Element of the Function
 */
public abstract class FunctionSetEvaluator<E> extends IterationSetEvaluator<E> {

    /**
     * Constructor of {@link FunctionSetEvaluator}.
     *
     * @param maxIteration    maximum Iteration
     */
    public FunctionSetEvaluator(int maxIteration) {
        super(maxIteration);
    }

    @Override
    public int getIteration(E element) {
        //set start values for the evaluating process
        int iteration = 0;
        E num = this.getInitial(element);
        Function<E, E> function = this.getFunction(element);

        //return if maxIteration has been reached or the escapeCondition has been met
        while (!this.getEscapeCondition().test(num) && iteration < this.getMaxIteration()) {
            //pass through iteration by applying function
            num = function.apply(num);
            iteration++;
        }
        return iteration;
    }

    /**
     * Returns the function used for the evaluation process, in dependence from the passed element.
     *
     * @param element the passed element
     * @return the evaluating function
     */
    public abstract Function<E, E> getFunction(E element);

    /**
     * Returns the initial value of the evaluating process.
     * Can depend on the passed element.
     *
     * @param element the passed element
     * @return the initial value
     */
    public abstract E getInitial(E element);


    public abstract Predicate<E> getEscapeCondition();
}
