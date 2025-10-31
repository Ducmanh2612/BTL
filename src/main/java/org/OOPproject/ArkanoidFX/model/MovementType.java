package org.OOPproject.ArkanoidFX.model;

public enum MovementType {
    FREE_FALL(0, -20),
    DRIFT(10, -20),
    WAVE(20, 40),
    ZIGZAG(10, -20);

    public final int vx;
    public final int vy;

    MovementType(int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
    }
}
