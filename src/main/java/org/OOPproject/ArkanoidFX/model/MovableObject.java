package org.OOPproject.ArkanoidFX.model;

public abstract class MovableObject extends GameObject {
    protected double velocityX, velocityY; // Velocity in pixels per second

    public MovableObject(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public double getVelocityX() {
        return velocityX;
    }
    public double getVelocityY() {
        return velocityY;
    }
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Move the object based on delta time.
     * @param deltaTime - Time elapsed since last frame in seconds
     */
    public abstract void move(double deltaTime);

    /**
     * Update method that takes delta time.
     * @param deltaTime - Time elapsed since last frame in seconds
     */
    public void update(double deltaTime) {
        move(deltaTime);
    }
}
