package org.OOPproject.ArkanoidFX.model.Bricks;

//TODO: this may be deleted
public class ExtraStrongBrick extends Brick {
    public ExtraStrongBrick(int x, int y, int width, int height) {
        super(x, y, width, height, BrickType.NONE);
        this.hitPoints = 6;
        this.scoreValue = 150;
    }
}
