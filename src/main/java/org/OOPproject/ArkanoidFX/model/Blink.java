package org.OOPproject.ArkanoidFX.model;

public class Blink extends GameObject {
    private int frameX;  // Current frame X (0-7)
    private int frameY;  // Current frame Y (0-2)
    private int maxFrameX = 8;  // 8 frames wide
    private int maxFrameY = 3;  // 3 frames tall
    private boolean finished;  // Has animation completed?
    
    private double animationSpeed = 0.05; // Seconds per frame (fast animation)
    private double animationTimer = 0.0;  // Accumulated time
    
    /**
     * Create a blink effect at brick position.
     * 
     * @param x X position (same as brick)
     * @param y Y position (same as brick)
     * @param width Width (same as brick)
     * @param height Height (same as brick)
     */
    public Blink(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.frameX = 0;
        this.frameY = 0;
        this.finished = false;
    }
    
    /**
     * Update animation frame based on elapsed time.
     * 
     * @param deltaTime Time since last update in seconds
     */
    public void update(double deltaTime) {
        animationTimer += deltaTime;
        
        // Advance frame when timer exceeds speed threshold
        if (animationTimer >= animationSpeed) {
            animationTimer = 0.0;
            frameX++;
            
            // Move to next row when reaching end of current row
            if (frameX >= maxFrameX) {
                frameX = 0;
                frameY++;
                
                // Animation complete when all rows finished
                if (frameY >= maxFrameY) {
                    finished = true;
                }
            }
        }
    }
    
    /**
     * Check if animation is complete and should be removed.
     * 
     * @return true if animation has played through all frames
     */
    public boolean isFinished() {
        return finished;
    }
    
    // Getters for animation frame coordinates
    public int getFrameX() { return frameX; }
    public int getFrameY() { return frameY; }
    public int getMaxFrameX() { return maxFrameX; }
    public int getMaxFrameY() { return maxFrameY; }
}
