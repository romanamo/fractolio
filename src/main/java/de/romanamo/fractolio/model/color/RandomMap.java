package de.romanamo.fractolio.model.color;

import java.awt.*;
import java.util.Random;

/**
 * <h1>RandomMap</h1>
 * <p>
 * The RandomMap class provides a deterministic pseudorandom colorization,
 * that can be modified by supplying a multiplier for the seed.
 * </p>
 */
public class RandomMap extends FractionalMap {

    public long seedMultiplier;

    /**
     * Constructor for {@link RandomMap}.
     *
     * @param seedMultiplier multiplier of the random seed
     */
    public RandomMap(long seedMultiplier) {
        this.seedMultiplier = seedMultiplier;
    }

    @Override
    protected int fraction(int numerator, int denominator) {
        if (numerator == denominator) {
            return 0;
        }
        //Fetch "random" seeded values
        Random random = new Random(numerator * 1000L + denominator);

        float hue = random.nextFloat();
        float s = random.nextFloat();
        float b = random.nextFloat();

        return Color.getHSBColor(hue, s, b).getRGB();
    }

    public long getSeedMultiplier() {
        return seedMultiplier;
    }

    public void setSeedMultiplier(long seedMultiplier) {
        this.seedMultiplier = seedMultiplier;
    }
}
