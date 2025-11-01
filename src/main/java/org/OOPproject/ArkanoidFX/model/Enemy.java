package org.OOPproject.ArkanoidFX.model;

import static org.OOPproject.ArkanoidFX.utils.Constants.*;

public class Enemy extends MovableObject {
    private EnemyType type;
    private MovementType movementType;
    private int hitPoints;
    private int scoreValue;
    private double currentPhi;
    private double timeInCurrentCircle;

    private Sprite sprite;

    public Enemy(int x, int y, int size) {
        super(x, y, size, size);
        this.type = randEnemyType();
        this.movementType = randMovementType();
        hitPoints = 1;
        scoreValue = 100;
        currentPhi = 0;
        timeInCurrentCircle = ENEMY_MOVEMENT_CYCLE;
        velocityX = movementType.vx;
        velocityY = movementType.vy;
        sprite = new Sprite(8, 3, 0.1, true);
    }

    public Enemy(int x, int y, int size, EnemyType type) {
        super(x, y, size, size);
        this.type = type;
        movementType = randMovementType();
        hitPoints = 1;
        scoreValue = 100;
        currentPhi = 0;
        timeInCurrentCircle = ENEMY_MOVEMENT_CYCLE;
        velocityX = movementType.vx;
        velocityY = movementType.vy;
        sprite = new Sprite(8, 3, 0.1, true);
    }

    public Enemy(int x, int y, int size, EnemyType type, MovementType movementType) {
        super(x, y, size, size);
        this.type = type;
        this.movementType = movementType;
        hitPoints = 1;
        scoreValue = 100;
        currentPhi = 0;
        timeInCurrentCircle = ENEMY_MOVEMENT_CYCLE;
        velocityX = movementType.vx;
        velocityY = movementType.vy;
        sprite = new Sprite(8, 3, 0.1, true);
    }

    public static EnemyType randEnemyType() {
        int r = (int)(Math.random() * 3);
        switch (r%3) {
            case 0:
                return EnemyType.UP_SENSITIVE;
            case 1:
                return EnemyType.DOWN_SENSITIVE;
        }
        return EnemyType.REFLECTOR;
    }

    public static MovementType randMovementType() {
        int r = (int)(Math.random() * 4);
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

    public void setScoreValue(int score) {
        scoreValue = score;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public double getTimeInCurrentCircle() {
        return timeInCurrentCircle;
    }

    public int getFrameX() { return sprite.getFrameX(); }
    public int getFrameY() { return sprite.getFrameY(); }

    public void setTimeInCurrentCircle(double timeInCurrentCircle) {
        this.timeInCurrentCircle = timeInCurrentCircle;
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
            //SoundManager.getInstance().playSound("bounce.wav");
        }
    }

    //TODO rewrite willHitBrick for enemy
    public boolean willHitBrick(GameObject brick, double deltaTime) {
        // Current position (center of enemy)
        double x0 = x ;
        double y0 = y ;

        // Next position if ball continues on current path
        double x1 = x0 + velocityX * deltaTime;
        double y1 = y0 + velocityY * deltaTime;

        Enemy fakeEnemy = new Enemy((int)x1, (int)y1, ENEMY_SIZE, type, movementType);

        return fakeEnemy.collidesWith(brick); // No hit
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
        sprite.update(deltaTime);

        checkWallBounces();
    }

    private void setVelocity(double deltaTime) {
        if (movementType == MovementType.WAVE) {
            setVelocityX(velocityX * Math.sin(currentPhi + 0.08 * deltaTime));
            currentPhi += 0.08 * deltaTime;
            setVelocityY(movementType.vx);
        }
    }

    public void takeHit() {
        hitPoints --;
    }

    public boolean isDestroyed() {
        return hitPoints == 0;
    }
}
