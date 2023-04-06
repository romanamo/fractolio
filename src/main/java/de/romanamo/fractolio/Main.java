package de.romanamo.fractolio;

import de.romanamo.fractolio.model.color.HueMap;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.draw.ImageScaler;
import de.romanamo.fractolio.model.draw.ImageSize;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.function.ComplexFunction;
import de.romanamo.fractolio.model.function.EuclideanMetric;
import de.romanamo.fractolio.model.function.QuadraticPolynomialFunction;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        FunctionSetEvaluator evaluator = new FunctionSetEvaluator(15, new Apfloat(2), new EuclideanMetric());

        ComplexFunction func = new QuadraticPolynomialFunction(new Apcomplex(new Apfloat(-0.70176), new Apfloat(-0.3842)));
        ImageDrawer drawer = new ImageDrawer(func, new HueMap(1, 0), evaluator, new ImageSize(100, 100));
        BufferedImage original = drawer.draw();
        BufferedImage resized = ImageScaler.scale(original, 300, 300);

        try {
            ImageIO.write(resized, "png", new File("src//main//resources//test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
