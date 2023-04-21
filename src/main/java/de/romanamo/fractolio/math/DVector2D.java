package de.romanamo.fractolio.math;

public class DVector2D {

    public static final DVector2D UP = new DVector2D(0,1);
    public static final DVector2D DOWN = new DVector2D(0,-1);
    public static final DVector2D LEFT = new DVector2D(-1,0);
    public static final DVector2D RIGHT = new DVector2D(1,0);
    public static final DVector2D ZERO = new DVector2D(0,0);

    private double x;

    private double y;

    public DVector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public DVector2D add(DVector2D operand) {
        return new DVector2D(this.x + operand.x, this.y + operand.y);
    }

    public DVector2D subtract(DVector2D operand) {
        return new DVector2D(this.x - operand.x, this.y - operand.y);
    }

    public DVector2D scale(double scalar) {
        return new DVector2D(this.x * scalar, this.y * scalar);
    }

    public DVector2D rotate(double angle) {
        double rotatedX = this.x * Math.cos(angle) - this.y * Math.sin(angle);
        double rotatedY = this.x * Math.sin(angle) + this.y * Math.cos(angle);

        return new DVector2D(rotatedX, rotatedY);
    }

    public DVector2D divide(DVector2D operand) {
        double real = (x * operand.x + y * operand.y)/(Math.pow(operand.x,2) + Math.pow(operand.y, 2));
        double imag = (x * operand.x - y * operand.y)/(Math.pow(operand.x,2) + Math.pow(operand.y, 2));

        return new DVector2D(real, imag);
    }

    public double distance(DVector2D operand) {
        double real = Math.pow(this.x - operand.x, 2);
        double imag = Math.pow(this.y - operand.y, 2);

        return Math.sqrt(real + imag);
    }

    public DVector2D pow(int power) {
        System.out.print("alert");
        return DVector2D.ZERO;
    }

    public DVector2D multiply(DVector2D operand) {
        double real = operand.x * x - operand.y * y;
        double imag = operand.x * y + operand.y * x;

        return new DVector2D(real, imag);
    }

    public IntVector2D floor() {
        return new IntVector2D((int) Math.floor(this.x), (int) Math.floor(this.y));
    }

    public IntVector2D ceil() {
        return new IntVector2D((int) Math.ceil(this.x), (int) Math.ceil(this.y));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DVector2D that = (DVector2D) o;

        if (Double.compare(that.x, x) != 0) return false;
        return Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DVector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
