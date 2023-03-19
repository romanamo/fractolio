package de.romanamo.fractolio.model.color;

import java.awt.*;

public class LightMap extends CycleMap {

    private final float hue;

    public LightMap(int cycle, float offset, float hue) {
        super(cycle, offset);
        this.hue = hue;
    }

    @Override
    public int translate(double argument) {
        float hue = this.hue;
        float saturation = 0.5f; //saturation
        float brightness = (float) argument * cycle; //brightness

        return Color.getHSBColor(hue, saturation, brightness).getRGB();
    }
}
