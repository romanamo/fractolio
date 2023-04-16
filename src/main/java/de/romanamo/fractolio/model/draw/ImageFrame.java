package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.math.DVector2D;
import de.romanamo.fractolio.math.IntVector2D;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ImageFrame {

    private int width;
    private int height;
    private double planeWidth;
    private double planeHeight;
    private double zoom;
    private DVector2D planeOffset;

    private int tileWidth = 32;

    private int tileHeight = 32;

    private ImageTile[][] tileRaster;

    public ImageFrame(int width, int height, double planeWidth, double planeHeight, double zoom, DVector2D planeOffset) {
        this.width = width;
        this.height = height;
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
        this.zoom = zoom;
        this.planeOffset = planeOffset;
        this.tileRaster = this.getTileRaster();
    }

    public DVector2D transformImageToPlane(IntVector2D v) {
        double scaledX = ((-planeWidth * (1 / zoom)) / 2.0 + v.getX() * ((planeWidth * (1 / zoom)) / (double) width) + planeOffset.getX());
        double scaledY = ((planeHeight * (1 / zoom)) / 2.0 - v.getY() * ((planeHeight * (1 / zoom)) / (double) height) + planeOffset.getY());

        return new DVector2D(scaledX, scaledY);
    }

    public ImageTile[][] getTileRaster() {
        //calculate size of the tile-raster
        int tileRasterHeight = (int) (double) (height) / tileHeight;
        int tileRasterWidth = (int) (double) (width) / tileWidth;

        //create the raster and fill it with tiles
        ImageTile[][] tileRaster = new ImageTile[tileRasterHeight][tileRasterWidth];
        for (int x = 0; x < tileRasterHeight; x++) {
            for (int y = 0; y < tileRasterWidth; y++) {
                //Detect if the tiles are "edge cases"
                boolean onHeightEdge = (y + 1) * tileHeight > height;
                boolean onWidthEdge = (x + 1) * tileWidth > width;

                //Set the Size of the Tile considering the special case that the tiles do not fit perfectly in
                int singleHeight = onHeightEdge ? tileHeight - (height - y * tileHeight) : tileHeight;
                int singleWidth = onWidthEdge ? tileWidth - (width - x * tileWidth) : tileWidth;

                DVector2D top = transformImageToPlane(new IntVector2D(x * tileWidth, y * tileHeight));
                DVector2D bottom = transformImageToPlane(new IntVector2D(
                        onWidthEdge ? x * tileWidth + singleWidth : (x + 1) * tileWidth,
                        onHeightEdge ? y * tileHeight + singleHeight : (y + 1) * tileHeight));
                tileRaster[y][x] = new ImageTile(singleHeight, singleWidth, top, bottom);
            }
        }
        return tileRaster;
    }

    public void calculate(FunctionSetEvaluator<DVector2D> evaluator) {

        Random rand = new Random();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        List<CalculationHelper> helperList = new ArrayList<>();

        for (int i = 0; i < availableProcessors; i++) {
            helperList.add(new CalculationHelper(evaluator));
        }
        for (int y = 0; y < tileRaster.length; y++) {
            for (int x = 0; x < tileRaster[y].length; x++) {
                helperList.get(rand.nextInt(availableProcessors)).tileList.add(tileRaster[y][x]);
            }
        }
        for (CalculationHelper helper : helperList) {
            helper.start();
        }
        for (CalculationHelper helper : helperList) {
            try {
                helper.join();
            }catch (InterruptedException e) {
                System.out.print("no");
            }
        }
    }

    public int[][] getRaster() {
        int[][] raster = new int[height][width];

        for (int y = 0; y < tileRaster.length; y++) {
            for (int x = 0; x < tileRaster[y].length; x++) {
                ImageTile tile = tileRaster[y][x];
                for (int h = 0; h < tile.getTileHeight(); h++) {
                    for (int w = 0; w < tile.getTileWidth(); w++) {
                        int acc = y * tile.getTileWidth() + w;
                        raster[y * tile.getTileHeight() + h][x * tile.getTileWidth() + w] = tile.getRaster()[h][w];
                    }
                }
            }
        }
        return raster;
    }

    @Override
    public String toString() {
        return "ImageFrame{" +
                "width=" + width +
                ", height=" + height +
                ", planeWidth=" + planeWidth +
                ", planeHeight=" + planeHeight +
                ", zoom=" + zoom +
                ", planeOffset=" + planeOffset +
                ", tileWidth=" + tileWidth +
                ", tileHeight=" + tileHeight +
                ", tileRaster=" + Arrays.toString(tileRaster) +
                '}';
    }
}
