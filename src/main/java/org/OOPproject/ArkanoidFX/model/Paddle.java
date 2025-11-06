package org.OOPproject.ArkanoidFX.model;

import org.OOPproject.ArkanoidFX.model.PowerUps.PowerUp;
import org.OOPproject.ArkanoidFX.utils.Constants;

import java.util.List;

import static org.OOPproject.ArkanoidFX.utils.Constants.*;

public class Paddle extends MovableObject {
    private static final double DEFAULT_SPEED = 600.0;

    private double speed; // Speed in pixels per second
    private int boundingBoxWidth = Constants.GAME_WIDTH - (Constants.BORDER_OFFSET * 2); // Account for both borders
    private boolean isGun;
    private double coolDown;


    private Sprite sprite;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = DEFAULT_SPEED; // 500 pixels per second
        velocityX = 0;
        isGun = false;
        sprite = new Sprite(8, 8, 0.1, true);
        // 8x8 frames, 0.1s per frame, loops
    }

    public void moveLeft() { velocityX = -speed; }

    public double getSpeed() { return speed;}
    public int getFrameX() { return sprite.getFrameX(); }
    public int getFrameY() { return sprite.getFrameY(); }

    public void stop() { velocityX = 0; }

    public void moveRight() {
        velocityX = speed;
    }

    public void applyPowerUp(PowerUp powerUp) {
        powerUp.applyEffect(this);
    }

    public boolean isExpanded() { return this.width > Constants.PADDLE_DEFAULT_WIDTH; }

    public void expandPaddle() {
        this.width = Constants.PADDLE_EXPANDED_WIDTH;
    }

    public void restorePaddleSize() {
        this.width = Constants.PADDLE_DEFAULT_WIDTH;
    }

    public boolean isGun() {return isGun;}
    public void setGun(boolean isGun) {
        this.isGun = isGun;
    }

    public double getCoolDown() {return this.coolDown;};
    public void setCoolDown(double coolDown) { this.coolDown = coolDown;}

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void shoot(List<Bullet> bullets, double deltaTime) {
        if (coolDown <= 0) {
            Bullet b1 = new Bullet((int) x + 20, (int) y, BULLET_WIDTH, BULLET_HEIGHT , 0.0, BULLET_VELOCITY);
            Bullet b2 = new Bullet((int) (x + width - 20), (int) y, BULLET_WIDTH, BULLET_HEIGHT, 0.0, BULLET_VELOCITY);
            bullets.add(b1);
            bullets.add(b2);
            coolDown = GUN_COOLDOWN;
            SoundManager.getInstance().playSound("laserShoot.wav");
        }
        else coolDown -= deltaTime;
    }

    @Override
    public void move(double deltaTime) {
        x += velocityX * deltaTime;
        // Keep paddle within game boundaries (accounting for border)
        if (x < Constants.BORDER_OFFSET) x = Constants.BORDER_OFFSET;
        if (x + width > Constants.BORDER_OFFSET + boundingBoxWidth) x = Constants.BORDER_OFFSET + boundingBoxWidth - width;
        sprite.update(deltaTime);
    }
}
