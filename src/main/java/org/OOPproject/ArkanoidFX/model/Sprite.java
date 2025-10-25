package org.OOPproject.ArkanoidFX.model;

/**
 * Sprite class handles sprite sheet animation.
 * Supports both looping and one-shot animations.
 */
public class Sprite {
    private int frameX;          // Current frame X coordinate (0 to maxFrameX-1)
    private int frameY;          // Current frame Y coordinate (0 to maxFrameY-1)
    private int maxFrameX;       // Total frames horizontally
    private int maxFrameY;       // Total frames vertically
    private double animationSpeed;  // Seconds per frame
    private double animationTimer;  // Accumulated time
    private boolean loop;           // Should animation loop?
    private boolean finished;       // Has animation completed? (for non-looping)

    /**
     * Create a sprite with specified animation parameters.
     *
     * @param maxFrameX Total frames horizontally (e.g., 8 frames wide)
     * @param maxFrameY Total frames vertically (e.g., 3 frames tall)
     * @param animationSpeed Seconds per frame (lower = faster)
     * @param loop Should animation loop back to start?
     */
    public Sprite(int maxFrameX, int maxFrameY, double animationSpeed, boolean loop) {
        this.maxFrameX = maxFrameX;
        this.maxFrameY = maxFrameY;
        this.animationSpeed = animationSpeed;
        this.loop = loop;
        this.frameX = 0;
        this.frameY = 0;
        this.animationTimer = 0.0;
        this.finished = false;
    }

    /**
     * Update animation frame based on elapsed time.
     *
     * @param deltaTime Time since last update in seconds
     */
    public void update(double deltaTime) {
        if (finished && !loop) {
            return; // Don't update if animation finished and not looping
        }

        animationTimer += deltaTime;

        // Advance frame when timer exceeds speed threshold
        if (animationTimer >= animationSpeed) {
            animationTimer = 0.0;
            frameX++;

            // Move to next row when reaching end of current row
            if (frameX >= maxFrameX) {
                frameX = 0;
                frameY++;

                // Check if we've gone through all frames
                if (frameY >= maxFrameY) {
                    if (loop) {
                        frameY = 0; // Loop back to start
                    } else {
                        frameY = maxFrameY - 1; // Stay on last frame
                        frameX = maxFrameX - 1;
                        finished = true;
                    }
                }
            }
        }
    }

    /**
     * Reset animation to first frame.
     */
    public void reset() {
        frameX = 0;
        frameY = 0;
        animationTimer = 0.0;
        finished = false;
    }

    // Getters
    public int getFrameX() { return frameX; }
    public int getFrameY() { return frameY; }
    public int getMaxFrameX() { return maxFrameX; }
    public int getMaxFrameY() { return maxFrameY; }
    public boolean isFinished() { return finished; }
    public boolean isLoop() { return loop; }
}
