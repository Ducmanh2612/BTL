package org.OOPproject.ArkanoidFX.model.Bricks;

import org.OOPproject.ArkanoidFX.utils.newConstants;

public class UnbreakableBrick extends Brick {
    public UnbreakableBrick(int x, int y, int width, int height) {
        super(x, y, width, height, newConstants.BlockType.CYAN);
        this.hitPoints = Integer.MAX_VALUE; // Cannot be destroyed
        this.type = newConstants.BlockType.NONE;
        this.scoreValue = 0; // No points for hitting unbreakable bricks
    }

    @Override
    public void takeHit() {
        // Do nothing - this brick cannot be destroyed
    }

    @Override
    public boolean isDestroyed() {
        return false; // Never destroyed
        //TODO: refactor so super class have default implementation of isDestroyed() that uses hitPoints
    }
}
