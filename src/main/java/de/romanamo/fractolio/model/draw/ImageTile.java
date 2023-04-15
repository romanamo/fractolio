package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.math.DVector2D;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;

public class ImageTile {

    private final DVector2D top;

    private final DVector2D down;

    private final int tileWidth;

    private final int tileHeight;

    private final double width;

    private final double height;

    private final int[][] raster;

    public ImageTile(int tileWidth, int tileHeight, DVector2D top, DVector2D down) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.raster = new int[tileHeight][tileWidth];
        this.top = top;
        this.down = down;
        this.height = Math.abs(this.top.getY() - this.down.getY());
        this.width = Math.abs(this.top.getX() - this.down.getX());
    }

    public void calculate(FunctionSetEvaluator<DVector2D> evaluator) {
        //calculate every tile by iterating through x and y
        for (int x = 0; x < this.tileWidth; x++) {
            for (int y = 0; y < this.tileHeight; y++) {
                double scaledX = this.top.getX() + x * this.width;
                double scaledY = this.top.getY() + y * this.height;

                this.raster[y][x] = evaluator.getIteration(new DVector2D(scaledX, scaledY));
            }
        }
    }

    public int[][] getRaster() {
        return this.raster;
    }
}
