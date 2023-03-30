package de.romanamo.fractolio.model.draw;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageScaler {

    public static BufferedImage scale(BufferedImage original, int width, int height) {

        BufferedImage resized = new BufferedImage(width, height, original.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(original, 0, 0, width, height, 0, 0, original.getWidth(),
                original.getHeight(), null);

        return resized;
    }
}
