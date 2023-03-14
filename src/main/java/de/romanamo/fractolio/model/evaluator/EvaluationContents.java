package de.romanamo.fractolio.model.evaluator;

public class EvaluationContents {

    private final long iterations;

    private final long maxIteration;

    public EvaluationContents(long iterations, long maxIteration) {
        this.iterations = iterations;
        this.maxIteration = maxIteration;
    }

    public long getIterations() {
        return this.iterations;
    }

    public boolean isInSet() {
        return this.iterations == this.maxIteration;
    }

    public long getMaxIteration() {
        return maxIteration;
    }

    public double getRelation() {
        return this.iterations / (double) this.maxIteration;
    }
}
