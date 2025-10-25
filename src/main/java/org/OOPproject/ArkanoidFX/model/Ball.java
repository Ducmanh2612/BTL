package org.OOPproject.ArkanoidFX.model;

import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_WIDTH;

//TODO: rewrite attachPaddle, releaseFromPaddle, willBrickHit, getCollisionSide methods
//TODO: remove stuckToPaddle or attachedPaddle if not needed anymore
public class Ball extends MovableObject {
    private static final double COOLDOWN_TIME = 0.05; // 50ms cooldown between brick collisions
    private static final double NORMAL_SPEED = 450.0;
    private static final double FAST_SPEED = 600.0;

    private double speed; // Speed in pixels per second
    private boolean active;
    private double collisionCooldown; // Cooldown to prevent multi-brick breaking

    private boolean stuckToPaddle; // Is ball stuck to paddle?
    private Paddle attachedPaddle; // Reference to paddle when stuck

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = NORMAL_SPEED;
        this.active = false;
        this.stuckToPaddle = true;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void attachToPaddle(Paddle paddle) {
        this.attachedPaddle = paddle;
        this.stuckToPaddle = true;
        this.active = false;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    /**
     * Release ball from paddle (start moving)
     * Called when player presses A or D
     */
    public void releaseFromPaddle() {
        if (stuckToPaddle && attachedPaddle != null) {
            stuckToPaddle = false;
            active = true;

            // Launch ball at -45 degree angle (up and to the right)
            double angle = Math.toRadians(-45);
            this.velocityX = Math.cos(angle) * speed;
            this.velocityY = Math.sin(angle) * speed;
            //TODO: launch angle is is a random angle between -45 and -135 degrees
        }
    }

    public boolean isStuckToPaddle() {
        return stuckToPaddle;
    }

    /**
     * Check if ball's trajectory will hit a brick.
     * This is called BEFORE the ball moves.
     *
     * @param brick The brick to check collision with
     * @param deltaTime Time until next position
     * @return true if the path from current position to next position crosses the brick
     */
    public boolean willHitBrick(GameObject brick, double deltaTime) {
        // Current position (center of ball)
        double x0 = x + width / 2.0;
        double y0 = y + height / 2.0;

        // Next position if ball continues on current path
        double x1 = x0 + velocityX * deltaTime;
        double y1 = y0 + velocityY * deltaTime;

        // Brick bounds (expanded by ball radius to simplify calculation)
        double radius = width / 2.0;
        double brickLeft = brick.getX() - radius;
        double brickRight = brick.getX() + brick.getWidth() + radius;
        double brickTop = brick.getY() - radius;
        double brickBottom = brick.getY() + brick.getHeight() + radius;

        // Check if trajectory line crosses any of the 4 brick edges

        // Check top edge (ball moving downward)
        if (y0 <= brickTop && y1 >= brickTop) {
            // Calculate where X would be when Y crosses top edge
            double t = (brickTop - y0) / (y1 - y0); // Parameter along line
            double xAtTop = x0 + t * (x1 - x0);
            if (xAtTop >= brickLeft && xAtTop <= brickRight) {
                return true; // Will hit top edge
            }
        }

        // Check bottom edge (ball moving upward)
        if (y0 >= brickBottom && y1 <= brickBottom) {
            double t = (brickBottom - y0) / (y1 - y0);
            double xAtBottom = x0 + t * (x1 - x0);
            if (xAtBottom >= brickLeft && xAtBottom <= brickRight) {
                return true; // Will hit bottom edge
            }
        }

        // Check left edge (ball moving rightward)
        if (x0 <= brickLeft && x1 >= brickLeft) {
            double t = (brickLeft - x0) / (x1 - x0);
            double yAtLeft = y0 + t * (y1 - y0);
            if (yAtLeft >= brickTop && yAtLeft <= brickBottom) {
                return true; // Will hit left edge
            }
        }

        // Check right edge (ball moving leftward)
        if (x0 >= brickRight && x1 <= brickRight) {
            double t = (brickRight - x0) / (x1 - x0);
            double yAtRight = y0 + t * (y1 - y0);
            if (yAtRight >= brickTop && yAtRight <= brickBottom) {
                return true; // Will hit right edge
            }
        }

        // Also check if ball is already inside brick (safety check)
        double ballCenterX = x + width / 2.0;
        double ballCenterY = y + height / 2.0;
        if (ballCenterX >= brick.getX() && ballCenterX <= brick.getX() + brick.getWidth() &&
                ballCenterY >= brick.getY() && ballCenterY <= brick.getY() + brick.getHeight()) {
            return true; // Ball is inside brick
        }

        return false; // No hit
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
        // Calculate where on paddle the ball hit (-1.0 to 1.0)
        double ballCenterX = x + width / 2.0;
        double paddleCenterX = paddle.getX() + paddle.getWidth() / 2.0;
        double hitPosition = (ballCenterX - paddleCenterX) / (paddle.getWidth() / 2.0);

        // Clamp hit position to prevent extreme angles
        hitPosition = Math.max(-1.0, Math.min(1.0, hitPosition));

        // Calculate bounce angle based on hit position
        // -60° to +60° range (0° is straight up)
        double maxAngle = 60.0; // Maximum angle in degrees
        double bounceAngle = hitPosition * maxAngle;
        double bounceAngleRad = Math.toRadians(bounceAngle - 90); // -90 because 0° should be up

        // Set new velocity based on angle
        velocityX = Math.cos(bounceAngleRad) * speed;
        velocityY = Math.sin(bounceAngleRad) * speed;

        // If paddle is moving, add some of that velocity to the ball
        if (paddle.getVelocityX() != 0) {
            double paddleInfluence = 0.3; // 30% of paddle velocity transferred
            velocityX += paddle.getVelocityX() * paddleInfluence;

            // Re-normalize to maintain speed
            double currentSpeed = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
            if (currentSpeed > 0) {
                velocityX = (velocityX / currentSpeed) * speed;
                velocityY = (velocityY / currentSpeed) * speed;
            }
        }

        // Ensure ball is moving upward
        if (velocityY > 0) {
            velocityY = -Math.abs(velocityY);
        }

        // Set cooldown
        collisionCooldown = COOLDOWN_TIME;
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
     * Push ball away from brick after collision.
     * This prevents ball from getting stuck inside brick.
     */
    public void correctPositionAfterBrickHit(GameObject brick, String side) {
        // Small push distance to ensure ball is outside brick
        double pushDistance = 1.0;

        switch (side) {
            case "top":
                // Ball hit top of brick, push it upward
                y = brick.getY() - height - pushDistance;
                break;
            case "bottom":
                // Ball hit bottom of brick, push it downward
                y = brick.getY() + brick.getHeight() + pushDistance;
                break;
            case "left":
                // Ball hit left of brick, push it leftward
                x = brick.getX() - width - pushDistance;
                break;
            case "right":
                // Ball hit right of brick, push it rightward
                x = brick.getX() + brick.getWidth() + pushDistance;
                break;
        }
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
     * Uses ball's velocity direction to determine hit side
     * More accurate than simple center-to-center calculation
     */
    public String getCollisionSide(GameObject other) {
        // Get ball center
        double ballCenterX = x + width / 2.0;
        double ballCenterY = y + height / 2.0;

        // Get brick bounds
        double brickLeft = other.getX();
        double brickRight = other.getX() + other.getWidth();
        double brickTop = other.getY();
        double brickBottom = other.getY() + other.getHeight();

        // Calculate overlap on each side
        // Check which edge the ball crossed
        double overlapLeft = ballCenterX - brickLeft;
        double overlapRight = brickRight - ballCenterX;
        double overlapTop = ballCenterY - brickTop;
        double overlapBottom = brickBottom - ballCenterY;

        // Find the minimum overlap - that's the side we hit
        double minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                Math.min(overlapTop, overlapBottom));

        // Return the side with minimum overlap
        if (minOverlap == overlapLeft) return "left";
        if (minOverlap == overlapRight) return "right";
        if (minOverlap == overlapTop) return "top";
        return "bottom";
    }

    /**
     * Move ball based on delta time.
     */
    @Override
    public void move(double deltaTime) {
        // NEW: If stuck to paddle, position ball on top of paddle
        if (stuckToPaddle && attachedPaddle != null) {
            // Center ball on paddle, just above it
            x = attachedPaddle.getX() + (attachedPaddle.getWidth() - width) / 2;
            y = attachedPaddle.getY() - height - 2; // 2 pixels above paddle
            return; // Don't move independently
        }

        if (!active) return;

        // Update collision cooldown
        if (collisionCooldown > 0) {
            collisionCooldown -= deltaTime;
        }

        // Simple movement - collision detection happens in GameManagerGUI BEFORE we move
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;

        // Check wall bounces
        checkWallBounces();
    }

    private void checkWallBounces() {
        // Bounce off left and right walls
        if (x <= 0) {
            x = 0;
            velocityX = Math.abs(velocityX);
        } else if (x + width >= GAME_WIDTH) {
            x = GAME_WIDTH - width;
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
        velocityX = 0;
        velocityY = 0;
        active = false;
        stuckToPaddle = true;
        collisionCooldown = 0;
    }
}
