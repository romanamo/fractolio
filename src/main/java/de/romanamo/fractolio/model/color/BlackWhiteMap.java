package de.romanamo.fractolio.model.color;

import java.awt.*;

public class BlackWhiteMap implements ColorMap{
    @Override
    public int translate(double argument) {
        float hue = 0.0f;
        float saturation = 0.0f; //saturation
        float brightness = Math.abs(argument - 1) < 1e-10 ? 0.0f : 1.0f;

        return Color.getHSBColor(hue, saturation, brightness).getRGB();
    }
}
