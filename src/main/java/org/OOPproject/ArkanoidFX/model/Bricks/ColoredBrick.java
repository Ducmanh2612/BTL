package org.OOPproject.ArkanoidFX.model.Bricks;


public class ColoredBrick extends Brick {
    private String color; // Color name: RED, YELLOW, BLUE, etc.
    /**
     * Create a colored brick.
     *
     * @param x X position
     * @param y Y position
     * @param width Width in pixels
     * @param height Height in pixels
     * @param color Color name (RED, YELLOW, BLUE, MAGENTA, LIME, WHITE, ORANGE, CYAN)
     * @param scoreValue Points awarded when destroyed
     */
    public ColoredBrick(int x, int y, int width, int height, String color, int scoreValue) {
        super(x, y, width, height);
        this.color = color;
        this.hitPoints = 1;
        this.scoreValue = scoreValue;
        this.type = color + "_BRICK";
    }
    //TODO: the color field can be changed to enum for better type safety and more efficient comparisons
    

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
