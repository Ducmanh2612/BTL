package org.OOPproject.ArkanoidFX.model.PowerUps;

import org.OOPproject.ArkanoidFX.model.GameObject;
import org.OOPproject.ArkanoidFX.model.Paddle;

public abstract class PowerUp extends GameObject {
    protected final double DEFAULT_FALL_SPEED = 150.0;

    protected String type;
    protected int duration;
    protected boolean falling;
    protected double fallSpeed; // Pixels per second

    public PowerUp(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.falling = true;
        this.fallSpeed = DEFAULT_FALL_SPEED;
    }

    public String getType() {
        return type;
    }

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
    }

    public void update(double deltaTime) {
        fall(deltaTime);
    }

    public abstract void applyEffect(Paddle paddle);
    public abstract void removeEffect(Paddle paddle);


}
