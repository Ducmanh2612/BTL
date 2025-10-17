package org.example.arkanoidFX.gameobject.movable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the ball that bounces around the game area.
 * Demonstrates OOP principle: Polymorphism (overriding methods)
 */
public class Ball extends MovableObject {
    private int speed;
    private final int originalSpeed;
    private int gameWidth;
    private int gameHeight;
    private boolean active;

    public Ball(int x, int y, int width, int height, int gameWidth, int gameHeight, int speed) {
        super(x, y, width, height);
        this.speed = speed;
        this.originalSpeed = speed;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.dx = speed;
        this.dy = -speed;
        this.active = true;
        this.textures = new java.util.ArrayList<>();
        this.textures.add(new ImageView(new Image(getClass().getResourceAsStream("/textures/Ball.png"))));
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void increaseSpeed() {
        speed += 10;
        updateVelocity();
    }

    public void normalSpeed() {
        speed = originalSpeed;
        updateVelocity();
    }

    private void updateVelocity() {
        int dirX = dx > 0 ? 1 : -1;
        int dirY = dy > 0 ? 1 : -1;
        dx = dirX * speed;
        dy = dirY * speed;
    }

    public void bounceOffPaddle(Paddle paddle) {
        // Bounce vertically
        dy = -Math.abs(dy);

        // Add horizontal component based on where ball hits paddle
        int paddleCenter = paddle.getX() + paddle.getWidth() / 2;
        int ballCenter = x + width / 2;
        int offset = ballCenter - paddleCenter;

        // Adjust horizontal direction based on offset
        if (offset < -paddle.getWidth() / 4) {
            dx = -speed;
        } else if (offset > paddle.getWidth() / 4) {
            dx = speed;
        }
    }

    public void bounceOffBrick() {
        dy = -dy;
    }

    public void bounceOffWall() {
        dx = -dx;
    }

    @Override
    public void move() {
        if (!active) return;

        x += dx;
        y += dy;

        // Bounce off left and right walls
        if (x <= 0 || x + width >= gameWidth) {
            dx = -dx;
            x = x <= 0 ? 0 : gameWidth - width;
        }

        // Bounce off top wall
        if (y <= 0) {
            dy = -dy;
            y = 0;
        }
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void render() {
        // Render method for terminal
    }

    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = speed;
        this.dy = -speed;
        this.active = true;
    }
}