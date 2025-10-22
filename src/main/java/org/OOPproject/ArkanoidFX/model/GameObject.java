package org.OOPproject.ArkanoidFX.model;

public abstract class GameObject {
    protected double x, y; // Use double for smooth movement
    protected int width, height;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getters for collision detection
    public int getX() {
        return (int)x;
    }
    public int getY() {
        return (int)y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    // Check if this object collides with another
    // AABB collision detection
    public boolean collidesWith(GameObject other) {
        return x < other.x + other.width &&
                x + width > other.x &&
                y < other.y + other.height &&
                y + height > other.y;
    }
}