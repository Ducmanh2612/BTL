package org.OOPproject.ArkanoidFX.model;

import javafx.scene.paint.Color;

/**
 * Particle - Represents a small particle for visual effects.
 * Used for brick destruction animations.
 */
public class Particle {
    private double x, y;
    private double velocityX, velocityY;
    private Color color;
    private double life; // 0.0 to 1.0, decreases over time
    private double size;
    private double gravity;

    public Particle(double x, double y, double velocityX, double velocityY, Color color, double size) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.color = color;
        this.size = size;
        this.life = 1.0;
        this.gravity = 200.0; // Pixels per second squared
    }

    /**
     * Update particle position and life.
     */
    public void update(double deltaTime) {
        // Apply velocity
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;

        // Apply gravity
        velocityY += gravity * deltaTime;

        // Decrease life
        life -= deltaTime * 2.0; // Particles last 0.5 seconds
    }

    public boolean isDead() {
        return life <= 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public double getLife() {
        return life;
    }

    public double getSize() {
        return size * life; // Size shrinks as particle dies
    }
}

