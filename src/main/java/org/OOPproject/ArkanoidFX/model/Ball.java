package org.OOPproject.ArkanoidFX.model;

public class Ball extends MovableObject {
    private static final double COOLDOWN_TIME = 0.05; // 50ms cooldown between brick collisions
    private static final double NORMAL_SPEED = 450.0;
    private static final double FAST_SPEED = 600.0;

    private double speed; // Speed in pixels per second
    private int gameWidth;
    private int gameHeight;
    //TODO: change gameWidth and gameHeight to use GameSettings
    private boolean active;
    private double collisionCooldown; // Cooldown to prevent multi-brick breaking

    public Ball(int x, int y, int width, int height, int gameWidth, int gameHeight) {
        super(x, y, width, height);
        this.speed = NORMAL_SPEED;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        // Set initial velocity at 45-degree angle
        double angle = Math.toRadians(-45); // -45 degrees (up and right)
        this.velocityX = Math.cos(angle) * speed;
        this.velocityY = Math.sin(angle) * speed;
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Increase ball speed (power-up effect).
     */
    public void increaseSpeed() {
        speed = FAST_SPEED;
        updateVelocityMagnitude();
    }

    /**
     * Return to normal speed.
     */
    public void normalSpeed() {
        speed = NORMAL_SPEED;
        updateVelocityMagnitude();
    }

    /**
     * Update velocity to match current speed while maintaining direction.
     */
    private void updateVelocityMagnitude() {
        double angle = Math.atan2(velocityY, velocityX);
        velocityX = Math.cos(angle) * speed;
        velocityY = Math.sin(angle) * speed;
    }

    /**
     * Bounce off paddle with improved physics.
     * The bounce angle depends on where the ball hits the paddle.
     */
    public void bounceOffPaddle(Paddle paddle) {
        // Calculate where ball hit paddle (0.0 = left edge, 1.0 = right edge)
        double ballCenterX = x + width / 2.0;
        double paddleCenterX = paddle.getX() + paddle.getWidth() / 2.0;
        double hitPosition = (ballCenterX - paddle.getX()) / paddle.getWidth();
        hitPosition = Math.max(0.0, Math.min(1.0, hitPosition)); // Clamp to [0, 1]

        // Convert hit position to angle (-60 to 60 degrees)
        double bounceAngle = (hitPosition - 0.5) * 120.0; // -60 to 60
        bounceAngle = Math.toRadians(bounceAngle);

        // Set velocity based on angle (always upward)
        velocityX = Math.sin(bounceAngle) * speed;
        velocityY = -Math.abs(Math.cos(bounceAngle)) * speed;

        // Ensure ball doesn't get stuck in paddle
        y = paddle.getY() - height;
    }

    /**
     * Bounce off brick based on collision side.
     */
    public void bounceOffBrick(String side) {
        switch (side) {
            case "top":
            case "bottom":
                velocityY = -velocityY;
                break;
            case "left":
            case "right":
                velocityX = -velocityX;
                break;
        }
        // Set cooldown to prevent hitting multiple bricks
        collisionCooldown = COOLDOWN_TIME;
    }

    /**
     * Check if ball can collide with bricks (not in cooldown).
     */
    public boolean canCollideWithBricks() {
        return collisionCooldown <= 0;
    }

    /**
     * Determine which side of an object was hit.
     * Returns: "top", "bottom", "left", or "right"
     */
    public String getCollisionSide(GameObject other) {
        double ballCenterX = x + width / 2.0;
        double ballCenterY = y + height / 2.0;
        double otherCenterX = other.getX() + other.getWidth() / 2.0;
        double otherCenterY = other.getY() + other.getHeight() / 2.0;

        double dx = ballCenterX - otherCenterX;
        double dy = ballCenterY - otherCenterY;

        // Determine collision side based on angle of approach
        double absDx = Math.abs(dx);
        double absDy = Math.abs(dy);

        if (absDx > absDy) {
            return dx > 0 ? "left" : "right";
        } else {
            return dy > 0 ? "top" : "bottom";
        }
    }

    /**
     * Move ball based on delta time.
     */
    @Override
    public void move(double deltaTime) {
        if (!active) return;

        // Update collision cooldown
        if (collisionCooldown > 0) {
            collisionCooldown -= deltaTime;
        }

        // Move by velocity * time
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;

        // Bounce off left and right walls
        if (x <= 0) {
            x = 0;
            velocityX = Math.abs(velocityX);
        } else if (x + width >= gameWidth) {
            x = gameWidth - width;
            velocityX = -Math.abs(velocityX);
        }

        // Bounce off top wall
        if (y <= 0) {
            y = 0;
            velocityY = Math.abs(velocityY);
        }
    }

    /**
     * Reset ball to given position with default velocity.
     */
    public void reset(int newX, int newY) {
        x = newX;
        y = newY;
        speed = NORMAL_SPEED;
        double angle = Math.toRadians(-45);
        velocityX = Math.cos(angle) * speed;
        velocityY = Math.sin(angle) * speed;
        active = true;
    }
}
