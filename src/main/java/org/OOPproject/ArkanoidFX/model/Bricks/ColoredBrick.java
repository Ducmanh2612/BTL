package org.OOPproject.ArkanoidFX.model.Bricks;


public class ColoredBrick extends Brick {
    private String color; // Color name: RED, YELLOW, BLUE, etc.

    public ColoredBrick(int x, int y, int width, int height, String color, int scoreValue) {
        super(x, y, width, height);
        this.color = color;
        this.hitPoints = 1;
        this.scoreValue = scoreValue;
        this.type = color + "_BRICK";
    }
    

    public String getColor() {
        return color;
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
