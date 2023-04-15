package de.romanamo.fractolio.model.evaluator;

/**
 * <h1>IterationSetEvaluator</h1>
 * <p>Checks if an Element is inside of a set. The process to decide,
 * if the Set contains specified element is done with a specified precision called {@code iteration}.
 * Normally the higher the iteration, the more precise is the result of the {@code inSet()}-function</p>
 *
 * @param <E> Elements the Set consists of
 */
public interface IterationSetEvaluator<E> extends SetEvaluator<E> {

    /**
     * Specifies the maximum iteration at which the Decision is supposed to be interrupted
     *
     * @return the maximum possible Iteration
     */
    int getMaxIteration();

    /**
     * Specifies the maximum iteration at which the Decision is supposed to be interrupted
     * Sets the maximum possible iteration
     */
    void setMaxIteration(int maxIteration);

    /**
     * Specifies the found Iteration, when the program is able to decide,
     * that the Set definitely does not contain the element
     *
     * @param element element to check
     * @return last made iteration
     */
    int getIteration(E element);

    @Override
    default boolean inSet(E element) {
        return this.getMaxIteration() == this.getIteration(element);
    }
}
