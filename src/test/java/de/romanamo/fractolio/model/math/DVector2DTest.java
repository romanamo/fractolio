package de.romanamo.fractolio.model.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DVector2DTest {

    @Test
    public void addTestZeroVector() {
        DVector2D a = new DVector2D(0, 0);
        DVector2D b = new DVector2D(0,0);

        assertEquals(a.add(b), new DVector2D(0, 0));
    }

    @Test
    public void BasicPythagorasDistanceTest() {
        DVector2D a = DVector2D.ZERO;
        DVector2D b = new DVector2D(3,4);

        assertEquals(5 , a.distance(b), 0.0000001);
    }

    @Test
    public void ZeroDistanceTest() {
        assertEquals(0, DVector2D.ZERO.distance(DVector2D.ZERO), 0.0000001);
    }

    @Test
    public void BasicMultiplication() {
        DVector2D a = new DVector2D(1, 2);
        DVector2D b = new DVector2D(3,4);

        assertEquals(new DVector2D(-5, 10), a.multiply(b));
    }

}