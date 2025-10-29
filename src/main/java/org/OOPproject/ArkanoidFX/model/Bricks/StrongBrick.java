package org.OOPproject.ArkanoidFX.model.Bricks;

public class StrongBrick extends Brick {
    public StrongBrick(int x, int y, int width, int height) {
        super(x, y, width, height, BrickType.NONE);
        this.hitPoints = 3;
        this.scoreValue = 150;
    }
}
