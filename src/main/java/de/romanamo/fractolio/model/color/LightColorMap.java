package de.romanamo.fractolio.model.color;

import java.awt.*;

public class LightColorMap extends FractionalMap {

    private float hue;

    private float saturation;

    public LightColorMap(float hue) {
        this.hue = hue;
        this.saturation = 0.5f;
    }

    public LightColorMap(float hue, float saturation) {
        this.hue = hue;
        this.saturation = saturation;
    }


    @Override
    protected int fraction(int numerator, int denominator) {
        if(numerator == denominator) {
            return 0;
        }
        float brightness = (float) numerator / denominator;
        return Color.getHSBColor(this.hue, this.saturation, brightness).getRGB();
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public void setHue(float hue) {
        this.hue = hue;
    }
}
