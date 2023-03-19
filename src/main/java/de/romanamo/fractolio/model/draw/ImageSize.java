package de.romanamo.fractolio.model.draw;

import java.util.ArrayList;
import java.util.List;

public class ImageSize {

    private final int width;

    private final int height;

    public ImageSize(int width, int height) {
        if(width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
    }

    public List<int[]> getCoordinates() {
        List<int[]> back = new ArrayList<>(width * height);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++) {
                back.add(new int[] {x,y});
            }
        }
        return back;
    }

    public boolean contains(int[] pixel) {
        if(pixel.length != 2) {
            return false;
        }
        return 1 <= pixel[0] && pixel[0] <= width && 1 <= pixel[1] && pixel[1] <= height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
