package org.OOPproject.ArkanoidFX.model;

public enum MovementType {
    FREE_FALL(0, 70),
    DRIFT(100, 20),
    WAVE(100, 20),
    ZIGZAG(10, 40);

    public final int vx;
    public final int vy;

    MovementType(int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
    }
}
