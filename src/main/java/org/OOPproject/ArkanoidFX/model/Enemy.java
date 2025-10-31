package org.OOPproject.ArkanoidFX.model;

import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_WIDTH;

public abstract class Enemy extends MovableObject {
    private EnemyType type;
    private MovementType movementType;
    private int hitPoints;
    private int scoreValue;
    private double currentPhi;

    public Enemy(int x, int y, int width, int height, EnemyType type) {
        super(x, y, width, height);
        this.type = type;
        //may be need to rewrite in constant
        hitPoints = 1;
        scoreValue = 100;
        currentPhi = 0;
        movementType = randMovementType();
    }

    public MovementType randMovementType() {
        int r = (int)Math.random() * 3;
        switch (r%3) {
            case 0:
                return MovementType.FREE_FALL;
            case 1:
                return MovementType.DRIFT;
        }
        return MovementType.WAVE;
    }

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    //TODO add sfx for enemy
    private void checkWallBounces() {
        // Bounce off left and right walls
        if (x <= 0) {
            x = 0;
            velocityX = Math.abs(velocityX);
            //SoundManager.getInstance().playSound("bounce.wav");
        } else {
            if (x + width >= GAME_WIDTH) {
                x = GAME_WIDTH - width;
                velocityX = -Math.abs(velocityX);
                //SoundManager.getInstance().playSound("bounce.wav");
            }
        }

        // Bounce off top wall
        if (y <= 0) {
            y = 0;
            velocityY = Math.abs(velocityY);
            SoundManager.getInstance().playSound("bounce.wav");
        }
    }

    //TODO rewrite willHitBrick for enemy
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

    //TODO rewrite bounceOffBrick for enemy
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
    }

    //TODO rewrite conrrectPositionAfterBrickHit
    public void correctPositionAfterBrickHit(GameObject brick, String side) {
        // Small push distance to ensure ball is outside brick
        switch (side) {
            case "top":
                // Ball hit top of brick, push it upward
                y = brick.getY() - height;
                break;
            case "bottom":
                // Ball hit bottom of brick, push it downward
                y = brick.getY() + brick.getHeight();
                break;
            case "left":
                // Ball hit left of brick, push it leftward
                x = brick.getX() - width;
                break;
            case "right":
                // Ball hit right of brick, push it rightward
                x = brick.getX() + brick.getWidth();
                break;
        }
    }

    //TODO rewrite getCollisionSide
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

    public void move(double deltaTime) {
        setVelocity(deltaTime);
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;
    }

    private void setVelocity(double deltaTime) {
        if (movementType == MovementType.WAVE) {
            setVelocityX(movementType.vy * Math.sin(currentPhi + 0.08 * deltaTime));
            setVelocityY(movementType.vx);
        }
        else {
            setVelocityX(movementType.vx);
            setVelocityY(movementType.vy);
        }
    }
}
