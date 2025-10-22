package org.OOPproject.ArkanoidFX.model.Bricks;

public class UnbreakableBrick extends Brick {
    //TODO: this brick type has gold color and it is applied blinking effect when being hit
    public UnbreakableBrick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitPoints = Integer.MAX_VALUE; // Cannot be destroyed
        this.type = "UNBREAKABLE";
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
