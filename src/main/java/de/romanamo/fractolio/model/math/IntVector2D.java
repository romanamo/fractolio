package de.romanamo.fractolio.model.math;

public class IntVector2D {

    public static final IntVector2D UP = new IntVector2D(0,1);
    public static final IntVector2D DOWN = new IntVector2D(0,-1);
    public static final IntVector2D LEFT = new IntVector2D(-1,0);
    public static final IntVector2D RIGHT = new IntVector2D(1,0);
    public static final IntVector2D ZERO = new IntVector2D(0,0);

    public int x;

    public int y;

    public IntVector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVector2D add(IntVector2D adder) {
        return new IntVector2D(this.x + adder.x, this.y + adder.y);
    }

    public IntVector2D scale(int scalar) {
        return new IntVector2D(this.x * scalar, this.y * scalar);
    }

    public IntVector2D rotate(double angle) {
        int rotatedX = (int) Math.round(this.x * Math.cos(angle) - this.y * Math.sin(angle));
        int rotatedY = (int) Math.round(this.x * Math.sin(angle) + this.y * Math.cos(angle));

        return new IntVector2D(rotatedX, rotatedY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
