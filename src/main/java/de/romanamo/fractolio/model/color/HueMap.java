package de.romanamo.fractolio.model.color;

import java.awt.*;

public class HueMap implements ColorMap{

    @Override
    public int translate(double argument) {
        float hue = (float) argument;
        float saturation = 1.0f; //saturation
        float brightness = 0.6f; //brightness

        return Color.getHSBColor(hue, saturation, brightness).getRGB();
    }
}
