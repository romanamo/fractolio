package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.model.color.HueMap;
import de.romanamo.fractolio.model.evaluator.EvaluationContents;
import de.romanamo.fractolio.model.evaluator.IterationalSetEvaluator;
import de.romanamo.fractolio.model.function.EuclideanMetric;
import de.romanamo.fractolio.model.function.QuadraticPolynomialFunction;
import de.romanamo.fractolio.model.function.ComplexFunction;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDrawer {

    private final static int FRAME_HEIGHT = 4;

    private final static int FRAME_WIDTH = 4;

    ComplexFunction function;

    public ImageDrawer(ComplexFunction function) {
        this.function = function;
    }

    public BufferedImage draw(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++) {
                IterationalSetEvaluator evaluator = new IterationalSetEvaluator(40, new Apfloat(2), new EuclideanMetric());

                Apfloat scaledX = new Apfloat(-FRAME_WIDTH/2.0 + x * (FRAME_WIDTH / (double) width));
                Apfloat scaledY = new Apfloat(FRAME_HEIGHT/2.0 - y * (FRAME_HEIGHT / (double) height));

                Apcomplex c = new Apcomplex(scaledX, scaledY);
                Apcomplex start = new Apcomplex(new Apfloat(-0.70176), new Apfloat(-0.3842));

                EvaluationContents cont = evaluator.evaluate(new QuadraticPolynomialFunction(start), c);
                int color = new HueMap().translate(cont.getRelation());
                image.setRGB(x,y, color);
            }
            if(x % 20 == 0) {
                System.out.println(100 * (x * height)/(double)(width*height) + "%");
            }

        }
        try {
            ImageIO.write(image, "png", new File("src//main//resources//test.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
