package org.OOPproject.ArkanoidFX.model;

import javafx.scene.input.KeyCode;
import org.OOPproject.ArkanoidFX.utils.Configs;
import org.OOPproject.ArkanoidFX.model.Bricks.*;
import org.OOPproject.ArkanoidFX.model.PowerUps.*;
import javafx.scene.paint.Color;
import org.OOPproject.ArkanoidFX.utils.InputSignal;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


//TODO: handle the ball passing bricks bugs
public class GameEngine {
    // Game constants - size of objects in pixels
    private static final int PADDLE_WIDTH = 100;   // Paddle width in pixels
    private static final int PADDLE_HEIGHT = 15;   // Paddle height in pixels
    private static final int BALL_SIZE = 15;       // Ball diameter in pixels
    private static final int UI_HEIGHT = 50;       // Height of UI panel at top

    // Game objects - the actual things in the game
    private Paddle paddle;                         // The player's paddle
    private Ball ball;                             // The bouncing ball
    private List<Brick> bricks;                    // List of all bricks
    private List<PowerUp> powerUps;                // List of falling power-ups
    private List<ActivePowerUp> activePowerUps;    // Power-ups currently active

    // Level system
    private Level currentLevel;                    // Current level definition

    // Particle system for visual effects
    private ParticleSystem particleSystem;

    // Game state variables
    private int score;                             // Player's score
    private int lives;                             // Remaining lives
    private int level;                             // Current level number
    private String gameState;                      // Current state: PLAYING, PAUSED, GAME_OVER

    // Utilities
    private Random random;                         // For random number generation
    private int gameWidth;                         // Width of game area
    private int gameHeight;                        // Height of game area (including UI)
    private int playAreaHeight;                    // Height of actual play area

    private static GameEngine instance = null;

    private class ActivePowerUp {
        PowerUp powerUp;           // The power-up object
        double remainingTime;      // How many seconds until it expires

        ActivePowerUp(PowerUp powerUp, double duration) {
            this.powerUp = powerUp;
            this.remainingTime = duration;
        }
    }

    private GameEngine() {
        this.gameWidth = Configs.GAME_WIDTH;
        this.gameHeight = Configs.GAME_HEIGHT;
        this.playAreaHeight = gameHeight - UI_HEIGHT;
        this.random = new Random();
        this.particleSystem = new ParticleSystem();

        // Initialize lists to hold game objects
        this.bricks = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        this.activePowerUps = new ArrayList<>();

        // Start in menu state
        this.gameState = "PLAYING";
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
        this.level = 1;
        this.gameState = "PLAYING";
        this.particleSystem.clear();
        initializeLevel();
    }

    /**
     * Initialize a level - create paddle, ball, and bricks.
     */
    private void initializeLevel() {
        // Create paddle in the center bottom of play area
        int paddleX = (gameWidth - PADDLE_WIDTH) / 2;
        int paddleY = gameHeight - 50;  // 50 pixels from bottom
        paddle = new Paddle(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT, gameWidth);

        // Create ball just above the paddle
        int ballX = gameWidth / 2 - BALL_SIZE / 2;
        int ballY = gameHeight - 100;  // 100 pixels from bottom
        ball = new Ball(ballX, ballY, BALL_SIZE, BALL_SIZE, gameWidth, playAreaHeight + UI_HEIGHT);

        // Clear old objects
        bricks.clear();
        powerUps.clear();
        activePowerUps.clear();
        particleSystem.clear();

        // Create bricks using Level system
        currentLevel = new Level(level);
        bricks = currentLevel.createBricks(gameWidth, UI_HEIGHT + 10); // Start below UI
    }
    //TODO: create a new level generator and delete this method to it

    /**
     * Update game state - called every frame with delta time.
     *
     * @param deltaTime - Time elapsed since last frame in seconds
     *
     * WHAT HAPPENS EACH FRAME:
     * 1. Update paddle position
     * 2. Update ball position
     * 3. Update power-ups (make them fall)
     * 4. Update particle effects
     * 5. Check for collisions
     * 6. Check win/lose conditions
     */
    public void updateGame(double deltaTime) {
        // Only update if game is being played
        if (!gameState.equals("PLAYING")) {
            return;
        }

        // Update game objects with delta time
        paddle.update(deltaTime);
        ball.update(deltaTime);

        // Update falling power-ups
        for (PowerUp powerUp : powerUps) {
            powerUp.update(deltaTime);
        }

        // Update active power-up durations
        updateActivePowerUps(deltaTime);

        // Update particle system
        particleSystem.update(deltaTime);

        // Check for collisions
        checkCollisions();

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

            // If expired, remove effect
            if (active.remainingTime <= 0) {
                active.powerUp.removeEffect(paddle);
                iterator.remove();
            }
        }
    }

    /**
     * Check for collisions between game objects.
     *
     * COLLISION TYPES:
     * 1. Ball vs Paddle - bounce the ball
     * 2. Ball vs Brick - destroy brick, bounce ball, create particles
     * 3. Paddle vs Power-up - activate power-up effect
     */
    public void checkCollisions() {
        // 1. Ball-Paddle collision
        if (ball.collidesWith(paddle)) {
            ball.bounceOffPaddle(paddle);
        }

        // 2. Ball-Brick collision (with cooldown to prevent multi-brick breaking)
        if (ball.canCollideWithBricks()) {
            Iterator<Brick> brickIterator = bricks.iterator();
            while (brickIterator.hasNext()) {
                Brick brick = brickIterator.next();

                if (ball.collidesWith(brick)) {
                    // Determine collision side for accurate bouncing
                    String side = ball.getCollisionSide(brick);
                    ball.bounceOffBrick(side);

                    // Get brick color for particles
                    Color particleColor = getBrickColor(brick);

                    // Create particle effect at hit location
                    particleSystem.createBurstEffect(
                            brick.getX() + brick.getWidth() / 2.0,
                            brick.getY() + brick.getHeight() / 2.0,
                            particleColor,
                            15 // Number of particles
                    );

                    // Take hit on brick
                    brick.takeHit();

                    // If brick is destroyed
                    if (brick.isDestroyed()) {
                        score += brick.getScoreValue();

                        // 15% chance to spawn power-up (not for unbreakable bricks)
                        if (!(brick instanceof UnbreakableBrick) && random.nextInt(100) < 15) {
                            spawnPowerUp(brick.getX(), brick.getY());
                        }

                        brickIterator.remove();
                    }

                    // IMPORTANT: Only collide with ONE brick per frame
                    break;
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

    /**
     * Get color for particles based on brick type.
     */
    private Color getBrickColor(Brick brick) {
        if (brick instanceof UnbreakableBrick) {
            return Color.GRAY;
        } else if (brick instanceof ExtraStrongBrick) {
            return Color.PURPLE;
        } else if (brick instanceof StrongBrick) {
            int hp = brick.getHitPoints();
            if (hp == 3) return Color.DARKRED;
            if (hp == 2) return Color.RED;
            return Color.ORANGERED;
        } else {
            return Color.DODGERBLUE;
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

    /**
     * Activate a power-up effect.
     */
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
    }

    /**
     * Level complete - advance to next level.
     */
    private void levelComplete() {
        level++;
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
                case STOP_LEFT -> paddle.stopLeft();
                case STOP_RIGHT -> paddle.stopRight();
                case PAUSE_RESUME -> gameState = "PAUSE";
            }
        } else if (gameState.equals("PAUSED")) {
            if(inputSignal.equals(InputSignal.PAUSE_RESUME)) gameState = "PLAYING";
        }
    }

    // ========== GETTER METHODS ==========
    // These allow GameView to access game objects for rendering

    public List<Brick> getBricks() { return bricks; }
    public List<PowerUp> getPowerUps() { return powerUps; }
    public Paddle getPaddle() { return paddle; }
    public Ball getBall() { return ball; }
    public ParticleSystem getParticleSystem() { return particleSystem; }
    public String getGameState() { return gameState; }
    public void setGameState(String state) { this.gameState = state; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getLevel() { return level; }
    public int getUIHeight() { return UI_HEIGHT; }
}
