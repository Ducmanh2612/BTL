package org.OOPproject.ArkanoidFX.model.Bricks;

import org.OOPproject.ArkanoidFX.utils.newConstants;

public class ExtraStrongBrick extends Brick {
    public ExtraStrongBrick(int x, int y, int width, int height) {
        super(x, y, width, height, newConstants.BlockType.CYAN);
        this.hitPoints = 5;
        this.type = newConstants.BlockType.NONE;
        this.scoreValue = 150;
    }

    @Override
    public void takeHit() {
        hitPoints--;
    }

    @Override
    public boolean isDestroyed() {
        return hitPoints <= 0;
    }
}
