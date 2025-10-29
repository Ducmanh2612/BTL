package org.OOPproject.ArkanoidFX.model.Bricks;

/**
 * Normal brick will be rendered without using assets.
 * the default color for normal brick is blue
 */
public class NormalBrick extends Brick {
    public NormalBrick(int x, int y, int width, int height) {
        super(x, y, width, height, BrickType.CYAN);
        this.hitPoints = 1;
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

