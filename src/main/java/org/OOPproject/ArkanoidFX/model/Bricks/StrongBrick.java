package org.OOPproject.ArkanoidFX.model.Bricks;

public class StrongBrick extends Brick {
    public StrongBrick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitPoints = 3;
        this.type = "Strong";
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
