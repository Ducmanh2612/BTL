package org.OOPproject.ArkanoidFX.model.PowerUps;

import org.OOPproject.ArkanoidFX.model.GameObject;
import org.OOPproject.ArkanoidFX.model.Paddle;

public abstract class PowerUp extends GameObject {
    protected final double DEFAULT_FALL_SPEED = 150.0;

    protected PowerUpTypes type;
    protected int duration;
    protected boolean falling;
    protected double fallSpeed; // Pixels per second

    //TODO: abstract this thing to use spirte sheet animation
    protected int frameX;  // Current frame X (0-4)
    protected int frameY;  // Current frame Y (0-3)
    protected int maxFrameX = 5;
    protected int maxFrameY = 4;
    protected double animationSpeed = 0.1; // Seconds per frame
    protected double animationTimer = 0.0;

    public PowerUp(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.falling = true;
        this.fallSpeed = DEFAULT_FALL_SPEED;
        this.frameX = 0;
        this.frameY = 0;
    }

    public PowerUpTypes getType() {
        return type;
    }
    public int getFrameX() { return frameX; }
    public int getFrameY() { return frameY; }
    public int getMaxFrameX() { return maxFrameX; }
    public int getMaxFrameY() { return maxFrameY; }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public int getDuration() {
        return duration;
    }

    public void fall(double deltaTime) {
        if (falling) {
            y += fallSpeed * deltaTime;
        }

        animationTimer += deltaTime;
        if (animationTimer >= animationSpeed) {
            animationTimer = 0.0;
            frameX++;
            if (frameX >= maxFrameX) {
                frameX = 0;
                frameY++;
                if (frameY >= maxFrameY) {
                    frameY = 0;
                }
            }
        }
    }

    public void update(double deltaTime) {
        fall(deltaTime);
    }

    public abstract void applyEffect(Paddle paddle);
    public abstract void removeEffect(Paddle paddle);


}
