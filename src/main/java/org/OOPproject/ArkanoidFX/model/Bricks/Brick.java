package org.OOPproject.ArkanoidFX.model.Bricks;

import org.OOPproject.ArkanoidFX.model.GameObject;
import org.OOPproject.ArkanoidFX.utils.newConstants;

public abstract class Brick extends GameObject {
    protected int hitPoints;
    protected newConstants.BlockType type; //TODO: change brick type to enum // Solved
    protected int scoreValue;

    public Brick(int x, int y, int width, int height, newConstants.BlockType type) {
        super(x, y, width, height);
        this.type = type;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public newConstants.BlockType getType() {
        return type;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public abstract void takeHit();
    public abstract boolean isDestroyed();
}
