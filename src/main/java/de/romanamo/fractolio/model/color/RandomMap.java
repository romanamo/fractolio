package de.romanamo.fractolio.model.color;

import java.awt.*;
import java.util.Random;

public class RandomMap extends FractionalMap{
    @Override
    protected int fraction(int numerator, int denominator) {
        if (numerator == denominator) {
            return 0;
        }
        Random r = new Random(numerator * 1000L);

        float hue = r.nextFloat();
        float s = r.nextFloat();
        float b = r.nextFloat();

        return Color.getHSBColor(hue, s, b).getRGB();
    }
}
