package org.OOPproject.ArkanoidFX.model;

import org.OOPproject.ArkanoidFX.model.PowerUps.PowerUp;
import org.OOPproject.ArkanoidFX.utils.Constants;

public class Paddle extends MovableObject {
    private static final double DEFAULT_SPEED = 500.0;

    private double speed; // Speed in pixels per second
    private PowerUp currentPowerUp;
    private int originalWidth;
    private int boundingBoxWidth = Constants.GAME_WIDTH;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = DEFAULT_SPEED; // 500 pixels per second
        this.originalWidth = width;
        velocityX = 0;
    }

    public void moveLeft() { velocityX = -speed; }


    public void stop() { velocityX = 0; }

    public void moveRight() {
        velocityX = speed;
    }

    public void applyPowerUp(PowerUp powerUp) {
        this.currentPowerUp = powerUp;
        powerUp.applyEffect(this);
    }

    public boolean isExpanded() { return this.width > originalWidth; }

    public void expandPaddle() {
        this.width = originalWidth * 2;
    }

    public void restorePaddleSize() {
        this.width = originalWidth;
    }

    public int getOriginalWidth() {
        return originalWidth;
    }

    @Override
    public void move(double deltaTime) {
        x += velocityX * deltaTime;
        // Keep paddle within game boundaries
        if (x < 0) x = 0;
        if (x + width > boundingBoxWidth) x = boundingBoxWidth - width;
    }
}
