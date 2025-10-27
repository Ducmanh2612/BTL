package org.OOPproject.ArkanoidFX.model;

import org.OOPproject.ArkanoidFX.model.PowerUps.PowerUp;
import org.OOPproject.ArkanoidFX.utils.Constants;

public class Paddle extends MovableObject {
    private static final double DEFAULT_SPEED = 500.0;

    private double speed; // Speed in pixels per second
    private int originalWidth;
    private int boundingBoxWidth = Constants.GAME_WIDTH;

    private Sprite sprite;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = DEFAULT_SPEED; // 500 pixels per second
        this.originalWidth = width;
        velocityX = 0;
        sprite = new Sprite(8, 8, 0.1, true);
        // 8x8 frames, 0.1s per frame, loops
    }

    public void moveLeft() { velocityX = -speed; }

    public int getFrameX() { return sprite.getFrameX(); }
    public int getFrameY() { return sprite.getFrameY(); }

    public void stop() { velocityX = 0; }

    public void moveRight() {
        velocityX = speed;
    }

    public void applyPowerUp(PowerUp powerUp) {
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
        sprite.update(deltaTime);
    }
}
