package org.OOPproject.ArkanoidFX.model;

import org.OOPproject.ArkanoidFX.model.Bricks.Brick;

public class Blink extends GameObject {
    private Sprite sprite;
    private Brick attachedBrick; // The brick this blink is attached to

    /**
     * Create a blink effect at brick position.
     * 
     * @param brick The brick to attach this blink effect to
     */
    public Blink(Brick brick) {
        super(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        this.attachedBrick = brick;
        // Blink sprite: 8 frames wide, 3 frames tall, fast animation (0.05s per frame), no loop
        this.sprite = new Sprite(8, 3, 0.015, false);
    }
    
    /**
     * Update animation frame based on elapsed time.
     * Also updates position to match attached brick.
     *
     * @param deltaTime Time since last update in seconds
     */
    public void update(double deltaTime) {
        sprite.update(deltaTime);
        // Keep blink position synced with brick
        if (attachedBrick != null) {
            this.x = attachedBrick.getX();
            this.y = attachedBrick.getY();
        }
    }
    
    /**
     * Check if animation is complete and should be removed.
     * 
     * @return true if animation has played through all frames
     */
    public boolean isFinished() {
        return sprite.isFinished();
    }

    /**
     * Get the brick this blink is attached to.
     *
     * @return The attached brick
     */
    public Brick getAttachedBrick() {
        return attachedBrick;
    }
    
    // Getters for sprite animation (delegate to Sprite)
    public int getFrameX() { return sprite.getFrameX(); }
    public int getFrameY() { return sprite.getFrameY(); }
    public int getMaxFrameX() { return sprite.getMaxFrameX(); }
    public int getMaxFrameY() { return sprite.getMaxFrameY(); }
}
