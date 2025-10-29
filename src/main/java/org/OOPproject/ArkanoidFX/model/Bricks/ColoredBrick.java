package org.OOPproject.ArkanoidFX.model.Bricks;

public class ColoredBrick extends Brick {
    /**
     * Create a colored brick.
     *
     * @param x X position
     * @param y Y position
     * @param width Width in pixels
     * @param height Height in pixels
     * @param type Color name (RED, YELLOW, BLUE, MAGENTA, LIME, WHITE, ORANGE, CYAN)
     * @param scoreValue Points awarded when destroyed
     */
    public ColoredBrick(int x, int y, int width, int height, BrickType type, int scoreValue, int hitPoints) {
        super(x, y, width, height, type);
        this.hitPoints = hitPoints;
        this.scoreValue = scoreValue;
    }
}
