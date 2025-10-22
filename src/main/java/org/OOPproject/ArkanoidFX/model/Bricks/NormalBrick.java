package org.OOPproject.ArkanoidFX.model.Bricks;

public class NormalBrick extends Brick {
    /*
     * TODO: this brick type has several colors
     *  white -> 50 points
     *  orange -> 60 points
     *  cyan -> 70 points
     *  lime -> 80 points
     *  red -> 90 points
     */
    public NormalBrick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitPoints = 1;
        this.type = "Normal";
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

