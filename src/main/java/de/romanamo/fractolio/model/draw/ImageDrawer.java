package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.model.color.ColorMap;
import de.romanamo.fractolio.model.evaluator.SetEvaluator;
import de.romanamo.fractolio.model.function.ComplexFunction;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@link ImageDrawer} managing the creation of Images specified by given
 * {@link ComplexFunction}, {@link ColorMap} and {@link SetEvaluator}.
 */
public class ImageDrawer {

    public final static double FRAME_HEIGHT = 3.5;

    public final static double FRAME_WIDTH = 3.5;

    ComplexFunction function;

    ColorMap colorMap;

    SetEvaluator evaluator;

    ImageSize size;

    public ImageDrawer(ComplexFunction function, ColorMap colorMap, SetEvaluator evaluator, ImageSize size) {
        this.function = function;
        this.colorMap = colorMap;
        this.evaluator = evaluator;
        this.size = size;

    }

    public BufferedImage draw() {
        int width = size.getWidth();
        int height = size.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //Set color for every pixel by iterating through width and height of the image

        List<List<DrawInfo>> elements = new ArrayList<>();

        int max = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < max; i++) {
            elements.add(new ArrayList<>());
        }

        for (int x = 0; x < width; x++){

            for (int y = 0; y < height; y++) {

                Apfloat scaledX = new Apfloat(-FRAME_WIDTH/2.0 + x * (FRAME_WIDTH / (double) width));
                Apfloat scaledY = new Apfloat(FRAME_HEIGHT/2.0 - y * (FRAME_HEIGHT / (double) height));
                Apcomplex c = new Apcomplex(scaledX, scaledY);

                elements.get((int) (Math.random() * (max))).add(new DrawInfo(image, c, this, x,y));
            }
        }
        List<Thread> threads = new ArrayList<>();
        for (List<DrawInfo> info: elements) {
            var t = new DrawHelperThread(info);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            }
            catch (InterruptedException e) {
                throw new RuntimeException();
            }

        }
        return image;
    }

    public SetEvaluator getEvaluator() {
        return evaluator;
    }

    public ComplexFunction getFunction() {
        return function;
    }

    public ColorMap getColorMap() {
        return colorMap;
    }

    public ImageSize getSize() {
        return size;
    }
}
