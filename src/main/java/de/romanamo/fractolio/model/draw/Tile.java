package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

public class Tile {

    private final int width;

    private final int height;

    private final Apcomplex origin;

    private final Apcomplex end;

    public Tile(int width, int height, Apcomplex origin, Apcomplex end) {
        this.width = width;
        this.height = height;
        this.origin = origin;
        this.end = end;
    }

    private Apcomplex fetchNumber(int x, int y) {
        double widthDistance = origin.real().subtract(end.real()).doubleValue();
        double heightDistance = origin.imag().subtract(end.imag()).doubleValue();

        Apfloat real = origin.real().add(new Apfloat(x * widthDistance));
        Apfloat imag = origin.imag().add(new Apfloat(y * heightDistance));

        return new Apcomplex(real, imag);
    }

    public int[][] calculate(FunctionSetEvaluator evaluator) {
        int[][] tileGrid = new int[height][width];
        boolean hasConnectedBorders = true;

        //Check if the top and bottom row of the TileGrid is inside the set
        for (int x = 0; x < this.width; x++) {
            for (int row : new int[]{0, height - 1}) {
                tileGrid[row][x] = evaluator.evaluate(null, this.fetchNumber(x, row));
                if (tileGrid[row][x] == evaluator.getMaxIteration()) {
                    hasConnectedBorders = false;
                }
            }
        }
        //Check if the left and right vertical Border, without the first and last row entries, is inside the Set
        for (int y = 1; y < this.height - 2; y++) {
            for (int column : new int[]{0, width - 1}) {
                tileGrid[y][column] = evaluator.evaluate(null, this.fetchNumber(column, y));
                if (tileGrid[y][column] == evaluator.getMaxIteration()) {
                    hasConnectedBorders = false;
                }
            }
        }
        //Calculate all the entries, that were left out
        for (int x = 1; x < this.width - 2; x++) {
            for (int y = 1; y < this.width - 2; y++) {
                if (hasConnectedBorders) {
                    tileGrid[y][x] = evaluator.getMaxIteration();
                } else {
                    evaluator.evaluate(null, this.fetchNumber(x,y));
                }
            }
        }
    }
}
