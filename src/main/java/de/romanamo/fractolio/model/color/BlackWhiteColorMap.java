package de.romanamo.fractolio.model.color;


public class BlackWhiteColorMap extends FractionalMap{

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
        if(numerator == denominator) {
            insideSetValue = 0;
        }
        return inverted ? ~insideSetValue : insideSetValue;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
}
