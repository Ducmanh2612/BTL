package org.OOPproject.ArkanoidFX.model.Bricks;

import org.OOPproject.ArkanoidFX.utils.newConstants;

public class StrongBrick extends Brick {
    public StrongBrick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitPoints = 3;
        this.type = newConstants.BlockType.NONE;
        this.scoreValue = 30;
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
