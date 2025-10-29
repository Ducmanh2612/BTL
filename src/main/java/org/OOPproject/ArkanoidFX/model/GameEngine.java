package org.OOPproject.ArkanoidFX.model;

import org.OOPproject.ArkanoidFX.utils.Constants;
import org.OOPproject.ArkanoidFX.model.Bricks.*;
import org.OOPproject.ArkanoidFX.model.PowerUps.*;
import javafx.scene.paint.Color;
import org.OOPproject.ArkanoidFX.utils.InputSignal;
import org.OOPproject.ArkanoidFX.utils.newLevel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.OOPproject.ArkanoidFX.utils.Constants.*;


//TODO: handle the ball passing bricks bugs
public class GameEngine {
    private Paddle paddle;                         // The player's paddle
    private Ball ball;                             // The bouncing ball
    private List<Brick> bricks;                    // List of all bricks
    private List<PowerUp> powerUps;                // List of falling power-ups
    private List<ActivePowerUp> activePowerUps;    // Power-ups currently active
    private List<Blink> blinks;                    // List of active blink effects

    private newLevel currentLevel;                    // Current level definition

    // Particle system for visual effects
    private ParticleSystem particleSystem;

    // Game state variables
    private int score;
    private int lives;
    private int levelNumber;                             // Current level number
    private String gameState;                      // Current state: PLAYING, PAUSED, GAME_OVER

    // Utilities
    private Random random;                         // For random number generation
    private int gameWidth;                         // Width of game area
    private int gameHeight;                        // Height of game area (including UI)

    private static GameEngine instance = null;

    private boolean ballReleased;

    private class ActivePowerUp {
        PowerUp powerUp;           // The power-up object
        double remainingTime;      // How many seconds until it expires

        ActivePowerUp(PowerUp powerUp, double duration) {
            this.powerUp = powerUp;
            this.remainingTime = duration;
        }
    }

    private GameEngine() {
        this.gameWidth = Constants.GAME_WIDTH;
        this.gameHeight = Constants.GAME_HEIGHT;
        this.random = new Random();
        this.particleSystem = new ParticleSystem();

        // Initialize lists to hold game objects
        this.bricks = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        this.activePowerUps = new ArrayList<>();
        this.blinks = new ArrayList<>();

        this.gameState = "PLAYING";
        this.ballReleased = false; // Ball not released yet
    }

    public static GameEngine getInstance() {
        if (instance == null) {
            // Default size, can be modified later
            instance = new GameEngine();
        }
        return instance;
    }

    /**
     * Start a new game - reset everything to initial state.
     */
    public void startGame() {
        this.score = 0;
        this.lives = 3;
        this.levelNumber = 20;
        this.gameState = "PLAYING";
        this.particleSystem.clear();
        this.ballReleased = false; // Ball starts stuck to paddle

        initializeLevel();
    }

    /**
     * Initialize a level - create paddle, ball, and bricks.
     */
    private void initializeLevel() {
        // Create paddle in the center bottom of play area
        int paddleX = (gameWidth - PADDLE_WIDTH) / 2;
        int paddleY = gameHeight - 50;  // 50 pixels from bottom
        paddle = new Paddle(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Create ball just above the paddle
        int ballX = gameWidth / 2 - BALL_SIZE / 2;
        int ballY = gameHeight - 100;  // 100 pixels from bottom
        ball = new Ball(ballX, ballY, BALL_SIZE, BALL_SIZE);
        ball.attachToPaddle(paddle);
        this.ballReleased = false;
        //TODO: ballReleased is set to false too much times, find a way to set it only once at the start of the game

        // Clear old objects
        bricks.clear();
        powerUps.clear();
        activePowerUps.clear();
        blinks.clear();
        particleSystem.clear();

        // Create bricks using Level system
        currentLevel = new newLevel(levelNumber);
        bricks = currentLevel.createBricks(gameWidth, 50);
    }
    //TODO: create a new level generator and delete this method to it

    /**
     * Update game state - called every frame with delta time.
     *
     * @param deltaTime - Time elapsed since last frame in seconds
     */
    public void updateGame(double deltaTime) {
        // Only update if game is being played
        if (!gameState.equals("PLAYING")) {
            return;
        }

        // If ball is stuck and paddle is moving, release the ball
        if (!ballReleased && ball.isStuckToPaddle()) {
            // Check if paddle is moving
            if (paddle.getVelocityX() != 0) {
                ball.releaseFromPaddle();
                ballReleased = true;
            }
        }
        paddle.update(deltaTime);
        checkCollisions(deltaTime);
        ball.update(deltaTime);
        for (PowerUp powerUp : powerUps) {
            powerUp.update(deltaTime);
        }
        updateActivePowerUps(deltaTime);
        updateBlinks(deltaTime); //TODO sửa lại để blink hoạt động
        particleSystem.update(deltaTime);

        // Check if ball fell off bottom of screen
        if (ball.getY() >= gameHeight) {
            loseLife();
        }

        // Check if all destroyable bricks are gone (level complete)
        if (isLevelComplete()) {
            levelComplete();
        }
    }

    /**
     * Check if level is complete (all destroyable bricks gone).
     * Unbreakable bricks don't count.
     */
    private boolean isLevelComplete() {
        for (Brick brick : bricks) {
            if (!(brick instanceof UnbreakableBrick)) {
                return false; // Still have destroyable bricks
            }
        }
        return true;
    }

    /**
     * Update active power-ups - decrease their remaining time.
     * When time runs out, remove the effect.
     */
    private void updateActivePowerUps(double deltaTime) {
        Iterator<ActivePowerUp> iterator = activePowerUps.iterator();
        while (iterator.hasNext()) {
            ActivePowerUp active = iterator.next();
            active.remainingTime -= deltaTime;

            if (active.remainingTime <= 0) {
                active.powerUp.removeEffect(paddle);
                iterator.remove();
            }
        }
    }

    /**
     * Update blink effects - animate them and remove when finished or brick destroyed.
     */
    private void updateBlinks(double deltaTime) {
        Iterator<Blink> iterator = blinks.iterator();
        while (iterator.hasNext()) {
            Blink blink = iterator.next();
            blink.update(deltaTime);

            // Remove blink if animation finished or attached brick was destroyed
            if (blink.isFinished() || !bricks.contains(blink.getAttachedBrick())) {
                iterator.remove();
            }
        }
    }

    /**
     * Check for collisions between game objects.
     * <p>
     * COLLISION TYPES:
     * 1. Ball vs Paddle - bounce the ball
     * 2. Ball vs Brick - destroy brick, bounce ball, create particles
     * 3. Paddle vs Power-up - activate power-up effect
     */
    public void checkCollisions(double deltaTime) {
        // 1. Ball-Paddle collision (simple bounds check is fine for paddle)
        if (ball.collidesWith(paddle)) {
            ball.bounceOffPaddle(paddle);
            if(ball.velocityX < 520) ball.specialMode = true;
            return ;
        }

        //Đảm bảo sẽ không có va chạm trong nhiều nhịp game
        if (ball.y + ball.height > paddle.y - paddle.height && ball.specialMode) {
            ball.velocityX = (500 + 20) * (ball.velocityX) / Math.abs(ball.velocityX);
        }
        else {
            ball.specialMode = false;
            ball.normalSpeed();
        }

        // 2. Ball-Brick collision using trajectory prediction
        // Check if ball's path WILL hit any brick
        boolean hitBrick = false; // Only hit one brick per frame

        if(ball.velocityY < 0) {
            int m = bricks.size();
            for (int i = m - 1; i >= 0; i--) {
                if(ball.willHitBrick(bricks.get(i), deltaTime)) {
                    brickAndBallProcess(bricks.get(i));
                    hitBrick = true;
                    break;
                }
            }
            for (Brick brick : bricks) {
                /** key */
                if (ball.willHitBrick(brick, deltaTime)) {
                    brickAndBallProcess(brick);
                    hitBrick = true;
                    break; // Only hit one brick
                }
            }
        } else {
            for (Brick brick : bricks) {
                /** key */
                if (ball.willHitBrick(brick, deltaTime)) {
                    brickAndBallProcess(brick);
                    hitBrick = true;
                    break; // Only hit one brick
                }
            }
        }


        // 3. Paddle-PowerUp collision
        Iterator<PowerUp> powerUpIterator = powerUps.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();

            // Remove power-ups that fell off screen
            if (powerUp.getY() >= gameHeight) {
                powerUpIterator.remove();
                continue;
            }

            // Check if paddle caught the power-up
            if (paddle.collidesWith(powerUp)) {
                activatePowerUp(powerUp);
                powerUpIterator.remove();
            }
        }

    }

    public void brickAndBallProcess(Brick brick) {
        String side = ball.getCollisionSide(brick);
        ball.correctPositionAfterBrickHit(brick, side); /** key */
        ball.bounceOffBrick(side);

        Color particleColor = getBrickColor(brick);
        particleSystem.createBurstEffect(
                brick.getX() + brick.getWidth() / 2.0,
                brick.getY() + brick.getHeight() / 2.0,
                particleColor,
                15
        );

        brick.takeHit();

        // Create blink effect for strong bricks when hit
        if (brick instanceof StrongBrick || brick instanceof ExtraStrongBrick || brick instanceof UnbreakableBrick) {
            // Only create new blink if this brick doesn't already have one
            boolean alreadyHasBlink = false;
            for (Blink existingBlink : blinks) {
                if (existingBlink.getAttachedBrick() == brick) {
                    alreadyHasBlink = true;
                    break;
                }
            }
            if (!alreadyHasBlink) {
                blinks.add(new Blink(brick));
            }
        }

        // Remove if destroyed
        if (brick.isDestroyed()) {
            score += brick.getScoreValue();

            // Spawn power-up chance
            if (!(brick instanceof UnbreakableBrick) && random.nextInt(100) < 15) {
                spawnPowerUp(brick.getX(), brick.getY());
            }

            bricks.remove(brick);
        }

    }

    /**
     * Get color for particles based on brick type.
     */
    /** can sua lai */
    private Color getBrickColor(Brick brick) {
        switch (brick) {
            case UnbreakableBrick unbreakableBrick -> {
                return Color.GOLD;
            }
            case ExtraStrongBrick extraStrongBrick -> {
                return Color.PURPLE;
            }
            case StrongBrick strongBrick -> {
                int hp = brick.getHitPoints();
                if (hp == 3) return Color.DARKRED;
                if (hp == 2) return Color.RED;
                return Color.ORANGERED;
            }
            case null, default -> {
                return Color.DODGERBLUE;
            }
        }
    }

    /**
     * Spawn a power-up at the given position.
     * Randomly chooses between different power-up types.
     */
    private void spawnPowerUp(int x, int y) {
        PowerUp powerUp;

        // 50/50 chance between two power-up types
        if (random.nextBoolean()) {
            powerUp = new ExpandPaddlePowerUp(x, y, 20, 20);
        } else {
            FastBallPowerUp fastBall = new FastBallPowerUp(x, y, 20, 20);
            fastBall.setBall(ball);
            powerUp = fastBall;
        }

        powerUps.add(powerUp);
    }

    private void activatePowerUp(PowerUp powerUp) {
        powerUp.applyEffect(paddle);
        // Power-up duration is in frames, convert to seconds (assuming 60 FPS)
        double durationInSeconds = powerUp.getDuration() / 60.0;
        activePowerUps.add(new ActivePowerUp(powerUp, durationInSeconds));
    }

    /**
     * Player loses a life.
     * Reset ball and paddle if lives remain, otherwise game over.
     */
    private void loseLife() {
        lives--;

        if (lives <= 0) {
            gameOver();
        } else {
            // Reset ball and paddle positions
            int paddleX = (gameWidth - paddle.getWidth()) / 2;
            paddle.setX(paddleX);
            paddle.stop();

            int ballX = gameWidth / 2 - ball.getWidth() / 2;
            int ballY = gameHeight - 100;
            ball.reset(ballX, ballY);
        }

        ball.attachToPaddle(paddle);
        ballReleased = false;
    }

    /**
     * Level complete - advance to next level.
     */
    private void levelComplete() {
        levelNumber++;
        initializeLevel();
    }

    /**
     * Game over - set state to GAME_OVER.
     */
    public void gameOver() {
        gameState = "GAME_OVER";
    }

    public void handleInput(InputSignal inputSignal) {
        //TODO: remove the two if statements and put them into the innner switch
        if (gameState.equals("PLAYING")) {
            switch (inputSignal) {
                case MOVE_LEFT -> paddle.moveLeft();
                case MOVE_RIGHT -> paddle.moveRight();
                case STOP -> paddle.stop();
                case PAUSE_RESUME -> gameState = "PAUSE";
            }
        } else if (gameState.equals("PAUSED")) {
            if (inputSignal.equals(InputSignal.PAUSE_RESUME)) gameState = "PLAYING";
        }
    }

    // ========== GETTER METHODS ==========
    // These allow GameView to access game objects for rendering

    public List<Brick> getBricks() {
        return bricks;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public List<Blink> getBlinks() {
        return blinks;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Ball getBall() {
        return ball;
    }

    public ParticleSystem getParticleSystem() {
        return particleSystem;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String state) {
        this.gameState = state;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getUIHeight() {
        return UI_HEIGHT;
    }
}
