package org.OOPproject.ArkanoidFX.model.PowerUps;

import org.OOPproject.ArkanoidFX.model.GameObject;
import org.OOPproject.ArkanoidFX.model.Paddle;
import org.OOPproject.ArkanoidFX.model.Sprite;

public abstract class PowerUp extends GameObject {
    protected final double DEFAULT_FALL_SPEED = 150.0;

    protected PowerUpTypes type;
    protected int duration;
    protected boolean falling;
    protected double fallSpeed; // Pixels per second

    protected Sprite sprite;

    public PowerUp(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.falling = true;
        this.fallSpeed = DEFAULT_FALL_SPEED;
        // PowerUp sprite: 5 frames wide, 4 frames tall, 0.1s per frame, loops
        this.sprite = new Sprite(5, 4, 0.1, true);
    }

    public PowerUpTypes getType() {
        return type;
    }

    // Getters for sprite animation (delegate to Sprite)
    public int getFrameX() { return sprite.getFrameX(); }
    public int getFrameY() { return sprite.getFrameY(); }

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
        sprite.update(deltaTime);
    }

    public void update(double deltaTime) {
        fall(deltaTime);
    }

    public abstract void applyEffect(Paddle paddle);
    public abstract void removeEffect(Paddle paddle);


}
