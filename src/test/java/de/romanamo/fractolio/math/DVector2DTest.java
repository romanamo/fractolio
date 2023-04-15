package de.romanamo.fractolio.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DVector2DTest {

    @Test
    public void addTestZeroVector() {
        DVector2D a = new DVector2D(0, 0);
        DVector2D b = new DVector2D(0,0);

        assertTrue(a.add(b).equals(new DVector2D(0,0)));
    }

}