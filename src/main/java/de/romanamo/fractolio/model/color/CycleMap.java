package de.romanamo.fractolio.model.color;

public abstract class CycleMap implements ColorMap {

    protected int cycle;

    protected float offset;

    public static int DEFAULT_CYCLE = 1;

    public static int DEFAULT_OFFSET = 0;

    public CycleMap() {
        this.cycle = DEFAULT_CYCLE;
        this.offset = 0;
    }

    public CycleMap(int cycle, float offset) {
        this.cycle = cycle;
        this.offset = offset;
    }
}
