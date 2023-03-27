package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.model.color.ColorMap;
import de.romanamo.fractolio.model.evaluator.EvaluationContents;
import de.romanamo.fractolio.model.evaluator.SetEvaluator;
import de.romanamo.fractolio.model.function.ComplexFunction;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class {@link ImageDrawer} managing the creation of Images specified by given
 * {@link ComplexFunction}, {@link ColorMap} and {@link SetEvaluator}.
 */
public class ImageDrawer {

    public final static int FRAME_HEIGHT = 4;

    public final static int FRAME_WIDTH = 4;

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

                ////Create the parameter to pass into the evaluator
                //Apfloat scaledX = new Apfloat(-FRAME_WIDTH/2.0 + x * (FRAME_WIDTH / (double) width));
                //Apfloat scaledY = new Apfloat(FRAME_HEIGHT/2.0 - y * (FRAME_HEIGHT / (double) height));
                //Apcomplex c = new Apcomplex(scaledX, scaledY);
                //
                //EvaluationContents contents = this.evaluator.evaluate(this.function, c);
                //
                ////Fetching the matching color
                //int color = this.colorMap.translate(contents.getRelation());
                //image.setRGB(x,y, color);
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

        try {
            ImageIO.write(image, "png", new File("src//main//resources//test.png"));
        }catch (IOException e) {
            e.printStackTrace();
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
