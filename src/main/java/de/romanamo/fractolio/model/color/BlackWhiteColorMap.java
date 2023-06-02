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

    /**
     * Constructor for {@link BlackWhiteColorMap}.
     */
    public BlackWhiteColorMap() {
        this.inverted = false;
    }

    /**
     * Constructor for {@link BlackWhiteColorMap}.
     *
     * @param inverted inverted color choice option
     */
    public BlackWhiteColorMap(boolean inverted) {
        this.inverted = inverted;
    }

    @Override
    protected int fraction(int numerator, int denominator) {
        //Apply normal black white coloring
        int insideSetValue = 0xFFFFFFFF;
        if (numerator == denominator) {
            insideSetValue = 0;
        }
        //Check inverted option
        return inverted ? ~insideSetValue : insideSetValue;
    }

    public boolean getInverted() {
        return this.inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
}
