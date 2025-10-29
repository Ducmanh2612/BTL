package org.OOPproject.ArkanoidFX.model.Bricks;

import org.OOPproject.ArkanoidFX.utils.newConstants;

public class ColoredBrick extends Brick {
    // Color name: RED, YELLOW, BLUE, etc.
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
    public ColoredBrick(int x, int y, int width, int height, newConstants.BlockType type, int scoreValue, int hitPoints) {
        super(x, y, width, height, type);
        // Sua lai hit points theo mau chuan
        this.hitPoints = hitPoints;
        this.scoreValue = scoreValue;
        // this.type = color + "_BRICK";
        // Sua lai type theo enum

    }
    //TODO: the color field can be changed to enum for better type safety and more efficient comparisons


    @Override
    public void takeHit() {
        hitPoints--;
    }

    @Override
    public boolean isDestroyed() {
        return hitPoints <= 0;
    }

}
