package de.romanamo.fractolio;

import de.romanamo.fractolio.model.color.BlackWhiteMap;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.draw.ImageSize;
import de.romanamo.fractolio.model.evaluator.IterationalSetEvaluator;
import de.romanamo.fractolio.model.evaluator.SetEvaluator;
import de.romanamo.fractolio.model.function.ComplexFunction;
import de.romanamo.fractolio.model.function.EuclideanMetric;
import de.romanamo.fractolio.model.function.QuadraticPolynomialFunction;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

public class Main {

    public static void main(String[] args) {

        SetEvaluator evaluator = new IterationalSetEvaluator(10, new Apfloat(2), new EuclideanMetric());

        ComplexFunction func = new QuadraticPolynomialFunction(new Apcomplex(new Apfloat(-0.70176), new Apfloat(-0.3842)));
        ImageDrawer drawer = new ImageDrawer(func, new BlackWhiteMap(), evaluator, new ImageSize(300,300));
        drawer.draw();
    }
}
