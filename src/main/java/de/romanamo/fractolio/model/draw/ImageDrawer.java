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

    public int[][] getRaster(int width, int height) {
        int tileHeight = 32;
        int tileWidth = 32;

        //calculate size of the tile-raster
        int tileRasterHeight = (int) (double) (height) / tileHeight + 1;
        int tileRasterWidth = (int) (double) (width) / tileWidth + 1;

        //create the raster and fill it with tiles
        ImageTile[][] tileRaster = new ImageTile[tileRasterHeight][tileRasterWidth];
        for (int x = 0; x < tileWidth; x++) {
            for (int y = 0; y < tileHeight; y++) {
                //Detect if the tiles are "edge cases"
                boolean onHeightEdge = (y + 1) * tileHeight > height;
                boolean onWidthEdge = (x + 1) * tileRasterWidth > width;

                //Set the Size of the Tile considering the special case that the tiles do not fit perfectly in
                int singleHeight = onHeightEdge ? tileHeight - (height - y * tileHeight) : tileHeight;
                int singleWidth = onWidthEdge ? tileWidth - (width - x * tileWidth) : tileWidth;

                /*
                DVector2D top = new DVector2D(x * tileWidth, y * tileHeight);
                DVector2D bottom = new DVector2D(
                        onWidthEdge ? x * tileWidth + singleWidth : (x+1) * tileWidth,
                        onHeightEdge ? y * tileHeight + singleHeight : (y+1) * tileHeight);
                */
                tileRaster[y][x] = new ImageTile(singleHeight, singleWidth, top, bottom);
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double scaledX = ((-FRAME_WIDTH * (1 / zoom)) / 2.0 + x * ((FRAME_WIDTH * (1 / zoom)) / (double) width) + offset.getX());
                double scaledY = ((FRAME_HEIGHT * (1 / zoom)) / 2.0 - y * ((FRAME_HEIGHT * (1 / zoom)) / (double) height) + offset.getY());
            }
        }
    }

    public BufferedImage draw(int width, int height, ColorMap map) {
        return null;
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