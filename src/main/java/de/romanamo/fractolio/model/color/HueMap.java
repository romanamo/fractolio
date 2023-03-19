package de.romanamo.fractolio.model.color;

import java.awt.*;

public class HueMap extends CycleMap {

    public HueMap(int cycle, float offset) {
        super(cycle, offset);
    }

    @Override
    public int translate(double argument) {
        float hue = (float) argument * cycle + this.offset;
        float saturation = 1.0f; //saturation
        float brightness = 0.6f; //brightness

        return Color.getHSBColor(hue, saturation, brightness).getRGB();
    }
}
