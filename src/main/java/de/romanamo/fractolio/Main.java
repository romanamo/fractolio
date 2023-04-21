package de.romanamo.fractolio;

import de.romanamo.fractolio.math.DVector2D;
import de.romanamo.fractolio.model.color.LightMap;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.evaluator.JuliaEvaluator;
import de.romanamo.fractolio.model.evaluator.MandelbrotEvaluator;

public class Main {
    public static void main(String[] args) {
        FunctionSetEvaluator<DVector2D> mandelbrot = new MandelbrotEvaluator(40);
        FunctionSetEvaluator<DVector2D> julia = new JuliaEvaluator(100, (z) -> z.multiply(z).add(new DVector2D(-0.8, 0.156)));
        ImageDrawer drawer = new ImageDrawer(julia);
        drawer.draw(1024, 1024, new LightMap(8,0,2.4f));
    }
}
