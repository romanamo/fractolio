package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.math.DVector2D;
import de.romanamo.fractolio.model.color.ColorMap;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.evaluator.SetEvaluator;
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

    public final DVector2D offset;

    private double zoom = 1.0;
    FunctionSetEvaluator<DVector2D> evaluator;

    public ImageDrawer(FunctionSetEvaluator<DVector2D> evaluator) {
        this(evaluator, DVector2D.ZERO);
    }

    public ImageDrawer(FunctionSetEvaluator<DVector2D> evaluator, DVector2D offset) {
        this.evaluator = evaluator;
        this.offset = offset;
    }

    public int[][] raster(int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double scaledX = ((-FRAME_WIDTH * (1 / zoom)) / 2.0 + x * ((FRAME_WIDTH * (1 / zoom)) / (double) width) + offset.getX());
                double scaledY = ((FRAME_HEIGHT * (1 / zoom)) / 2.0 - y * ((FRAME_HEIGHT * (1 / zoom)) / (double) height) + offset.getY());

                elements.get((int) (Math.random() * (max))).add(new DrawInfo(image, c, this, x, y));
            }
        }
    }

    public BufferedImage draw(int width, int height, ColorMap map) {

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

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Apfloat scaledX = new Apfloat(((-FRAME_WIDTH * (1 / zoom)) / 2.0 + x * ((FRAME_WIDTH * (1 / zoom)) / (double) width) + OFFSET_X));
                Apfloat scaledY = new Apfloat(((FRAME_HEIGHT * (1 / zoom)) / 2.0 - y * ((FRAME_HEIGHT * (1 / zoom)) / (double) height) + OFFSET_Y));
                Apcomplex c = new Apcomplex(scaledX, scaledY);

                elements.get((int) (Math.random() * (max))).add(new DrawInfo(image, c, this, x, y));
            }
        }
        List<Thread> threads = new ArrayList<>();
        for (List<DrawInfo> info : elements) {
            var t = new DrawHelperThread(info);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }

        }
        return image;
    }

    public FunctionSetEvaluator getEvaluator() {
        return evaluator;
    }

    public ColorMap getColorMap() {
        return colorMap;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public double getZoom() {
        return zoom;
    }
}