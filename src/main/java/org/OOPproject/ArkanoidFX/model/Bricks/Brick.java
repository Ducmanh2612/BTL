package org.OOPproject.ArkanoidFX.model.Bricks;

import org.OOPproject.ArkanoidFX.model.GameObject;

public abstract class Brick extends GameObject {
    protected int hitPoints;
    protected String type; //TODO: change brick type to enum
    protected int scoreValue;

    public Brick(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public String getType() {
        return type;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public abstract void takeHit();
    public abstract boolean isDestroyed();
}
