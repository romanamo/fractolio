package de.romanamo.fractolio.model.evaluator;

/**
 * <h1>SetEvaluator</h1>
 * <p>
 * Evaluates if an Element of given Type is inside the Set.
 * </p>
 *
 * @param <E> Elements the Set consists of
 * @version 1.0.0
 */
public interface SetEvaluator<E> {

    /**
     * Checks if a given Input is part of a Set
     *
     * @param element element to check
     * @return {@code true} if the Element is contained inside the Set, else {@code false}
     */
    boolean inSet(E element);
}
