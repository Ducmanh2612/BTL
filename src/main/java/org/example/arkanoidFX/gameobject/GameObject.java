package org.example.arkanoidFX.gameobject;

import javafx.scene.image.ImageView;

import java.util.List;

/**
 * Base class for all game objects.
 * Demonstrates OOP principle: Abstraction
 * Uses composition instead of inheritance to avoid JavaFX Shape extension restrictions
 */
public abstract class GameObject {
    protected int x, y, width, height;
    protected List<ImageView> textures;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        // textures are initialized in subclasses
    }

    // Getters for collision detection
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    // Get the current texture for rendering
    public ImageView getTexture() {
        if (textures != null && !textures.isEmpty()) {
            return textures.get(0);
        }
        return null;
    }

    // Check if this object collides with another
    public boolean collidesWith(GameObject other) {
        return x < other.x + other.width &&
                x + width > other.x &&
                y < other.y + other.height &&
                y + height > other.y;
    }

    public abstract void update();
    public abstract void render();
}
