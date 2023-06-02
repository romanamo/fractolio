package de.romanamo.fractolio.model.color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackWhiteColorMapTest {

    private final int WHITE = 0xFFFFFFFF;

    private final int BLACK = 0x0;

    @Test
    public void DefaultColorTest() {
        BiColorMap blackWhiteColorMap = new BlackWhiteColorMap();

        assertEquals(BLACK, blackWhiteColorMap.map(7,7));
        assertEquals(WHITE, blackWhiteColorMap.map(3,7));
        assertEquals(WHITE, blackWhiteColorMap.map(1,7));
    }

    @Test
    public void InvertedColorTest() {
        BiColorMap blackWhiteColorMap = new BlackWhiteColorMap(true);

        assertEquals(WHITE, blackWhiteColorMap.map(7,7));
        assertEquals(BLACK, blackWhiteColorMap.map(3,7));
        assertEquals(BLACK, blackWhiteColorMap.map(1,7));
    }

    @Test
    public void IllegalArgumentExceptionTest() {
        BiColorMap blackWhiteColorMap = new BlackWhiteColorMap();

        assertThrows(IllegalArgumentException.class, () ->  blackWhiteColorMap.map(1, 0));
        assertThrows(IllegalArgumentException.class, () ->  blackWhiteColorMap.map(0, 0));
    }
}