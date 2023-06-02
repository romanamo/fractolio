package de.romanamo.fractolio.model.evaluator;

import de.romanamo.fractolio.model.math.DVector2D;

public class PolynomialJuliaEvaluator extends JuliaEvaluator {
    public PolynomialJuliaEvaluator(int maxIteration, double escapeDistance, int power, DVector2D c) {
        super(maxIteration, escapeDistance, (d) -> d.pow(power).add(c));
    }
}
