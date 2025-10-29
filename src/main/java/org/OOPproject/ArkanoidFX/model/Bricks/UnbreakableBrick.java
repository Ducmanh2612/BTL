package org.OOPproject.ArkanoidFX.model.Bricks;

public class UnbreakableBrick extends Brick {
    public UnbreakableBrick(int x, int y, int width, int height) {
        super(x, y, width, height, BrickType.GOLD);
        this.hitPoints = Integer.MAX_VALUE; // Cannot be destroyed
        this.scoreValue = 0;
    }

    @Override
    public void takeHit() {
        // Do nothing - this brick cannot be destroyed
    }

    @Override
    public boolean isDestroyed() {
        return false; // Never destroyed
    }
}
