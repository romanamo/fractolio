package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.log.Log;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.evaluator.SetEvaluator;
import de.romanamo.fractolio.model.math.DVector2D;

import java.util.logging.Logger;

/**
 * Class {@link ImageDrawer} managing the creation of Images specified by given
 * {@link SetEvaluator}.
 */
public class ImageDrawer {

    private static Logger logger = Logger.getLogger(Log.NAME);

    public final static double FRAME_HEIGHT = 4.0;

    public final static double FRAME_WIDTH = 4.0;

    public DVector2D offset;

    private double zoom = 1.0;

    FunctionSetEvaluator<DVector2D> evaluator;

    public ImageDrawer(FunctionSetEvaluator<DVector2D> evaluator) {
        this(evaluator, DVector2D.ZERO);
    }

    public ImageDrawer(FunctionSetEvaluator<DVector2D> evaluator, DVector2D offset) {
        this.evaluator = evaluator;
        this.offset = offset;
    }


    public int[][] draw(int width, int height) {

        //logger.info(String.format("Image-Creation with width: %d, height: %d", width, height));

        ImageFrame frame = new ImageFrame(width, height, FRAME_WIDTH, FRAME_HEIGHT, this.zoom, this.offset);

        frame.calculate(evaluator);

        return frame.getRaster();
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

    public DVector2D getOffset() {
        return offset;
    }

    public void setOffset(DVector2D offset) {
        this.offset = offset;
    }
}