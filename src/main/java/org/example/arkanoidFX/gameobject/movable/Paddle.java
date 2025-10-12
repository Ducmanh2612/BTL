package org.example.arkanoidFX.gameobject.movable;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.arkanoidFX.gameobject.powerup.PowerUp;

import java.util.ArrayList;
import java.util.List;


public class Paddle extends MovableObject {
    private int speed;
    private PowerUp currentPowerUp;
    private int originalWidth;
    private int gameWidth;

    public Paddle(int x, int y, int width, int height, int gameWidth) {
        super(x, y, width, height);
        this.speed = 20;
        this.originalWidth = width;
        this.gameWidth = gameWidth;

        this.textures = new ArrayList<>();
        this.textures.add(new ImageView(new Image(getClass().getResourceAsStream("/textures/Paddle.png"))));
    }

    public void moveLeft() {
        dx = -speed;
    }

    public void moveRight() {
        dx = speed;
    }

    public void stop() {
        dx = 0;
    }

    public void applyPowerUp(PowerUp powerUp) {
        this.currentPowerUp = powerUp;
        powerUp.applyEffect(this);
    }

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
    public void move() {
        x += dx;
        // Keep paddle within game boundaries
        if (x < 0) x = 0;
        if (x + width > gameWidth) x = gameWidth - width;
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void render() {
        // Render method for terminal (will be used by Renderer)
    }
}
