package de.romanamo.fractolio.model.evaluator;

/**
 * <h1>IterationSetEvaluator</h1>
 * <p>
 * The IterationSetEvaluator class checks if an element is inside of a set. The process to decide,
 * if the Set contains specified element is done with a specified precision called {@code iteration}.
 * The higher the iteration, the more precise is the result of the {@code inSet()}-function.
 * </p>
 *
 * @param <E> Elements the Set consists of
 */
public abstract class IterationSetEvaluator<E> implements SetEvaluator<E> {

    private int maxIteration;

    /**
     * Constructor of {@link IterationSetEvaluator}.
     *
     * @param maxIteration maximum Iteration
     */
    public IterationSetEvaluator(int maxIteration) {
        this.setMaxIteration(maxIteration);
    }

    /**
     * Specifies the maximum iteration at which the decision process is supposed to be interrupted.
     *
     * @return the maximum possible Iteration
     */
    public int getMaxIteration() {
        return maxIteration;
    }

    /**
     * Specifies the maximum iteration at which the Decision is supposed to be interrupted
     * Sets the maximum possible iteration.
     */
    public void setMaxIteration(int maxIteration) {
        if (maxIteration < 0) {
            throw new IllegalArgumentException(
                    String.format("Maximum Iteration: %d has to be greater or equals Zero", maxIteration));
        }
        this.maxIteration = maxIteration;
    }

    /**
     * Specifies the found Iteration, when the program is able to decide,
     * that the set definitely does not contain the given element.
     *
     * @param element element to check
     * @return last made iteration
     */
    public abstract int getIteration(E element);

    @Override
    public boolean inSet(E element) {
        return this.getMaxIteration() == this.getIteration(element);
    }
}
