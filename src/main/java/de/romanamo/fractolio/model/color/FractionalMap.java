package de.romanamo.fractolio.model.color;

/**
 * <p>
 * The class FractionalMap wraps the BiColorMap functionality by providing extra requirements,
 * for the parameters <code>a,b</code>.
 * </p>
 * <ol>
 *     <li>a must be greater then b</li>
 *     <li>b has to be greater then 0</li>
 * </ol>
 */
public abstract class FractionalMap implements BiColorMap {
    @Override
    public int map(int a, int b) {
        if (a > b) {
            throw new IllegalArgumentException(
                    String.format("Numerator: %d cannot be bigger then denominator: %d", a, b));
        }
        if (b <= 0) {
            throw new IllegalArgumentException(
                    String.format("Denominator: %d cannot be smaller then 0", b));
        }
        return this.fraction(a, b);
    }

    /**
     * Maps 2 <code>int</code> values to an <code>int</code> RGB-value.
     * Wrapped by FractionalMap class.
     *
     * @param numerator first <code>int</code> value
     * @param denominator second <code>int</code> value
     * @return the mapped <code>int</code> RGB-value
     */
    protected abstract int fraction(int numerator, int denominator);
}
