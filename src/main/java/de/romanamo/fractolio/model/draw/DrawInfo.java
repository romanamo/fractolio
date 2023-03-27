package de.romanamo.fractolio.model.draw;

import org.apfloat.Apcomplex;

import java.awt.image.BufferedImage;
import java.util.List;

public class DrawInfo {

    private final BufferedImage image;
    private final ImageDrawer drawer;
    private final int x;
    private final int y;

    private Apcomplex number;



    public DrawInfo(BufferedImage image, Apcomplex number, ImageDrawer drawer, int x, int y) {
        this.image = image;
        this.number = number;
        this.drawer = drawer;
        this.x = x;
        this.y = y;}

    public BufferedImage getImage() {
        return image;
    }

    public ImageDrawer getDrawer() {
        return drawer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Apcomplex getNumber() {
        return number;
    }


}
