package de.romanamo.fractolio;

import de.romanamo.fractolio.math.DVector2D;
import de.romanamo.fractolio.model.color.BlackWhiteMap;
import de.romanamo.fractolio.model.color.CycleMap;
import de.romanamo.fractolio.model.color.HueMap;
import de.romanamo.fractolio.model.color.LightMap;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.evaluator.MandelbrotEvaluator;

public class Main {
    public static void main(String[] args) {
        FunctionSetEvaluator<DVector2D> mandelbrot = new MandelbrotEvaluator(30);
        ImageDrawer drawer = new ImageDrawer(mandelbrot);
        drawer.draw(1024, 1024, new BlackWhiteMap() {
        });
    }
}
