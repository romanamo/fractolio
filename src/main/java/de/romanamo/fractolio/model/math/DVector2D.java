package de.romanamo.fractolio.model.math;

/**
 * <h1>DVector2D</h1>
 * <p>
 * The {@link DVector2D} class implements a 2-dimensional Vector using real valued components,
 * implementing methods commonly used for operations on complex numbers.
 * </p>
 * <p>
 * Every operation with a vector as result, returns a newly created {@link DVector2D}-object.
 * </p>
 */
public class DVector2D {

    /**
     * up Vector - (0, 1)
     */
    public static final DVector2D UP = new DVector2D(0, 1);

    /**
     * down vector - (0, -1)
     */
    public static final DVector2D DOWN = new DVector2D(0, -1);

    /**
     * left vector - (-1, 0)
     */
    public static final DVector2D LEFT = new DVector2D(-1, 0);

    /**
     * right vector - (1,0)
     */
    public static final DVector2D RIGHT = new DVector2D(1, 0);

    /**
     * zero Vector - (0, 0)
     */
    public static final DVector2D ZERO = new DVector2D(0, 0);

    private final double x;

    private final double y;

    /**
     * Constructor for {@link DVector2D}
     *
     * @param x first component
     * @param y second component
     */
    public DVector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds two vectors by creating the sum of each component.
     *
     * @param operand vector to add
     * @return sum of given vectors
     */
    public DVector2D add(DVector2D operand) {
        return new DVector2D(this.x + operand.x, this.y + operand.y);
    }

    /**
     * Subtracts two vectors by subtracting each component.
     *
     * @param operand vector to subtract
     * @return subtraction of given vectors
     */
    public DVector2D subtract(DVector2D operand) {
        return new DVector2D(this.x - operand.x, this.y - operand.y);
    }

    /**
     * Scales a vector by multiplying each component with the scalar.
     *
     * @param scalar scalar
     * @return scaled vector
     */
    public DVector2D scale(double scalar) {
        return new DVector2D(this.x * scalar, this.y * scalar);
    }

    /**
     * Rotates a vector by given angle in radians.
     *
     * @param angle angle in radians
     * @return rotated vector
     */
    public DVector2D rotate(double angle) {
        /*
        Applies the rotation matrix for R^2.
        |  cos(a)   -sin(a)  |
        |  sin(a)    cos(a)  |
         */
        double rotatedX = this.x * Math.cos(angle) - this.y * Math.sin(angle);
        double rotatedY = this.x * Math.sin(angle) + this.y * Math.cos(angle);

        return new DVector2D(rotatedX, rotatedY);
    }

    /**
     * Conjugates a vector in terms of conjugation for complex numbers,
     * by flipping the sign of the complex value.
     *
     * @return conjugated vector
     */
    public DVector2D conjugate() {
        return new DVector2D(this.x, -this.y);
    }

    /**
     * Calculates the distance of two vectors.
     *
     * @param operand other vector
     * @return distance between both vectors.
     */
    public double distance(DVector2D operand) {
        double real = Math.pow(this.x - operand.x, 2);
        double imag = Math.pow(this.y - operand.y, 2);

        return Math.sqrt(real + imag);
    }

    /**
     * Creates the Exponentiation of a vector in terms of complex exponentiation.
     *
     * @param power power
     * @return exponentiation of given vector
     */
    public DVector2D pow(int power) {
        //TODO implement instead of throwing exception
        throw new UnsupportedOperationException();
    }

    /**
     * Multiplies two vectors together.
     *
     * @param operand vector to multiply
     * @return multiplied vector
     */
    public DVector2D multiply(DVector2D operand) {
        double real = operand.x * x - operand.y * y;
        double imag = operand.x * y + operand.y * x;

        return new DVector2D(real, imag);
    }

    /**
     * Creates a {@link IntVector2D} by creating the floor of each component.
     *
     * @return floored vector
     */
    public IntVector2D floor() {
        return new IntVector2D((int) Math.floor(this.x), (int) Math.floor(this.y));
    }

    /**
     * Creates a {@link IntVector2D} by creating the ceil of each component.
     *
     * @return ceiled vector
     */
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
