package de.romanamo.fractolio.model.color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomMapTest {

    @Test
    public void consistentRandomTest() {
        RandomMap randomMap = new RandomMap(1000L);

        assertEquals(randomMap.map(2,10), randomMap.map(2, 10));
        assertEquals(randomMap.map(3,100), randomMap.map(3, 100));
        assertEquals(randomMap.map(99,200), randomMap.map(99, 200));
    }

    @Test
    public void IllegalArgumentExceptionTest() {
        BiColorMap blackWhiteColorMap = new RandomMap(1000L);

        assertThrows(IllegalArgumentException.class, () ->  blackWhiteColorMap.map(1, 0));
        assertThrows(IllegalArgumentException.class, () ->  blackWhiteColorMap.map(0, 0));
    }
}