package de.romanamo.fractolio.model.color;


import java.awt.*;

/**
 * <h1>HueColorMap</h1>
 * <p>
 * The HueColorMap class implements the usual color mapping,
 * known for coloring the Mandelbrotset.
 * </p>
 * <p>
 * All points inside the Mandelbrot set are colored black,
 * the Complement points are colored by their speed of escape.
 * </p>
 */
public class HueColorMap extends FractionalMap {

    private float cycles;

    private float offset;

    private float brightness;

    private float saturation;

    public HueColorMap(float cycles, float offset, float brightness, float saturation) {
        this.cycles = cycles;
        this.offset = offset;
        this.brightness = brightness;
        this.saturation = saturation;
    }

    public HueColorMap() {
        this.cycles = 1f;
        this.offset = 0;
        this.brightness = 0.5f;
        this.saturation = 1f;
    }

    @Override
    protected int fraction(int numerator, int denominator) {
        //color points inside the Set black
        if (numerator == denominator) {
            return 0;
        }
        //color remaining points in relation to their escaping speed
        float hue = (float) numerator / denominator;

        return Color.getHSBColor(hue * cycles + offset, saturation, brightness).getRGB();
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }
}
