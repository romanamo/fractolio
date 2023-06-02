package de.romanamo.fractolio.model.color;

/**
 * <h1>BlackWhiteColorMap</h1>
 * <p>
 * The BlackWhiteColorMap colors black if numerator and denominator are equal, else white.
 * The choice of coloring can be inverted.
 * </p>
 */
public class BlackWhiteColorMap extends FractionalMap {

    private boolean inverted;

    public BlackWhiteColorMap() {
        this.inverted = false;
    }

    public BlackWhiteColorMap(boolean inverted) {
        this.inverted = inverted;
    }

    @Override
    protected int fraction(int numerator, int denominator) {
        int insideSetValue = 0xFFFFFFFF;
        if (numerator == denominator) {
            insideSetValue = 0;
        }
        return inverted ? ~insideSetValue : insideSetValue;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
}
