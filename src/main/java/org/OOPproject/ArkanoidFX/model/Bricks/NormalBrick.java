package org.OOPproject.ArkanoidFX.model.Bricks;

import org.OOPproject.ArkanoidFX.utils.newConstants;

/**
 * Normal brick will be rendered without using assets.
 * the default color for normal brick is blue
 */
public class NormalBrick extends Brick {
    public NormalBrick(int x, int y, int width, int height) {
        super(x, y, width, height, newConstants.BlockType.CYAN);
        this.hitPoints = 1;
        this.type = newConstants.BlockType.NONE;
        this.scoreValue = 10;
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

