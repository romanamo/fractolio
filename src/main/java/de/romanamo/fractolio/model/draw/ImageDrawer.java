package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.math.DVector2D;
import de.romanamo.fractolio.model.color.ColorMap;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.evaluator.SetEvaluator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Class {@link ImageDrawer} managing the creation of Images specified by given
 * {@link SetEvaluator}, {@link ColorMap} and {@link SetEvaluator}.
 */
public class ImageDrawer {

    public final static double FRAME_HEIGHT = 4.0;

    public final static double FRAME_WIDTH = 4.0;

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


    public BufferedImage draw(int width, int height, ColorMap map) {
        ImageFrame frame = new ImageFrame(width, height, 4.0, 4.0, 1.0, new DVector2D(0, 0));

        frame.calculate(evaluator);

        int[][] raster = frame.getRaster();

        BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int entry = raster[j][i];
                buf.setRGB(i,j, map.translate((double) entry / evaluator.getMaxIteration()));
            }
            try {
                File outputfile = new File("saved.png");
                ImageIO.write(buf, "png", outputfile);
            } catch (IOException e) {
                // handle exception
            }
        }
        return null;
    }

    public FunctionSetEvaluator<DVector2D> getEvaluator() {
        return evaluator;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public double getZoom() {
        return zoom;
    }
}