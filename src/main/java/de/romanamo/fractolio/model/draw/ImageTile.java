package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.model.math.DVector2D;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;

public class ImageTile {

    private final DVector2D top;

    private final DVector2D down;

    private final int tileWidth;

    private final int tileHeight;

    private final double width;

    private final double height;

    private int[][] raster;

    public ImageTile(int tileWidth, int tileHeight, DVector2D top, DVector2D down) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.raster = new int[tileHeight][tileWidth];
        this.top = top;
        this.down = down;
        this.height = Math.abs(this.top.getY() - this.down.getY());
        this.width = Math.abs(this.top.getX() - this.down.getX());
    }

    private DVector2D fetchNumber(int x, int y) {
        double widthDistance = width / tileWidth;
        double heightDistance = height / tileHeight;

        double real = top.getX() + x * widthDistance;
        double imag = top.getY() - y * heightDistance;

        return new DVector2D(real, imag);
    }

    public void calculate(FunctionSetEvaluator<DVector2D> evaluator) {
        //calculate every tile by iterating through x and y
        boolean hasConnectedBorders = true;

        //Check if the top and bottom row of the TileGrid is inside the set
        for (int x = 0; x < this.tileWidth; x++) {
            for (int row : new int[]{0, tileHeight - 1}) {
                raster[row][x] = evaluator.getIteration(fetchNumber(x, row));
                if (raster[row][x] != evaluator.getMaxIteration()) {
                    hasConnectedBorders = false;
                }
            }
        }
        //Check if the left and right vertical Border, without the first and last row entries, is inside the Set
        for (int y = 1; y < this.tileHeight - 1; y++) {
            for (int column : new int[]{0, tileWidth - 1}) {
                raster[y][column] = evaluator.getIteration(fetchNumber(column, y));
                if (raster[y][column] != evaluator.getMaxIteration()) {
                    hasConnectedBorders = false;
                }
            }
        }
        //Calculate all the entries, that were left out
        for (int x = 1; x < this.tileWidth - 1; x++) {
            for (int y = 1; y < this.tileHeight - 1; y++) {
                if (hasConnectedBorders) {
                    raster[y][x] = evaluator.getMaxIteration();
                } else {
                    raster[y][x] = evaluator.getIteration(fetchNumber(x,y));
                }
            }
        }
    }

    public int[][] getRaster() {
        return this.raster;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }
}
