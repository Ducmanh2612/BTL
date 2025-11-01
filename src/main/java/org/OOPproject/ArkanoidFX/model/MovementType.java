package org.OOPproject.ArkanoidFX.model;

public enum MovementType {
    FREE_FALL(0, 40),
    DRIFT(10, 40),
    WAVE(20, 80),
    ZIGZAG(10, 40);

    public final int vx;
    public final int vy;

    MovementType(int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
    }
}
