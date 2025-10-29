package org.OOPproject.ArkanoidFX.model.Bricks;

import org.OOPproject.ArkanoidFX.model.GameObject;

public abstract class Brick extends GameObject {
    protected int hitPoints;
    protected BrickType type; //TODO: change brick type to enum // Solved
    protected int scoreValue;

    public Brick(int x, int y, int width, int height, BrickType type) {
        super(x, y, width, height);
        this.type = type;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public BrickType getType() {
        return type;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void takeHit() {
        hitPoints--;
    }

    public boolean isDestroyed() {
        return hitPoints <= 0;
    }
}
