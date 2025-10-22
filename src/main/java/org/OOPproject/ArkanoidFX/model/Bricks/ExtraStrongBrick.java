package org.OOPproject.ArkanoidFX.model.Bricks;

public class ExtraStrongBrick extends Brick {

    public ExtraStrongBrick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitPoints = 5;
        this.type = "EXTRA_STRONG";
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
