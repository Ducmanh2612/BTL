package org.OOPproject.ArkanoidFX.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import org.OOPproject.ArkanoidFX.model.*;
import org.OOPproject.ArkanoidFX.model.Bricks.*;
import org.OOPproject.ArkanoidFX.model.PowerUps.*;

import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_HEIGHT;
import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_WIDTH;

public class GameView extends StackPane {
    private static GameEngine gameEngineRef;
    private static GameView instance;

    private Canvas canvas;
    private GraphicsContext gc;
    private AssetManager assetManager;

    private GameView(GameEngine gameEngine) {
        gameEngineRef = gameEngine;
        canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        assetManager = AssetManager.getInstance();
        getChildren().add(canvas);
    }

    public static GameView getInstance(GameEngine gameEngine) {
        if (instance == null) {
            instance = new GameView(gameEngine);
        }
        return instance;
    }

    public void render() {
        gc.setFill(Color.rgb(20, 20, 40));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        drawLevelBackground();

        String state = gameEngineRef.getGameState();
        renderGame();
        if (state.equals("PAUSED")) {
            renderPauseOverlay();
        }
    }

    private void drawLevelBackground() {
        int level = gameEngineRef.getLevelNumber();
        Image pattern = assetManager.getBackgroundPattern(level);
        if (pattern != null) {
            ImagePattern patternFill = new ImagePattern(pattern, 0, 0,
                pattern.getWidth(), pattern.getHeight(), false);
            gc.setFill(patternFill);
            gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        }
    }

    private void renderGameOver() {
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        gc.fillText("GAME OVER", GAME_WIDTH / 2 - 130, GAME_HEIGHT / 2 - 50);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        gc.fillText("Final Score: " + gameEngineRef.getScore(), GAME_WIDTH / 2 - 80, GAME_HEIGHT / 2 + 20);
        gc.fillText("Level Reached: " + gameEngineRef.getLevelNumber(), GAME_WIDTH / 2 - 90, GAME_HEIGHT / 2 + 60);
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        gc.fillText("Press SPACE to Play Again", GAME_WIDTH / 2 - 120, GAME_HEIGHT / 2 + 120);
    }

    private void renderGame() {
        for (Brick brick : gameEngineRef.getBricks()) {
            renderBrickWithImage(brick);
        }

        // Render blink effects on top of bricks
        for (Blink blink : gameEngineRef.getBlinks()) {
            renderBlink(blink);
        }

        for (PowerUp powerUp : gameEngineRef.getPowerUps()) {
            renderAnimatedPowerUp(powerUp);
        }

        renderPaddle(gameEngineRef.getPaddle());
        renderBall(gameEngineRef.getBall());
        renderParticles();

        Ball ball = gameEngineRef.getBall();
        if (ball.isStuckToPaddle()) {
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            gc.fillText("Move LEFT or RIGHT to release ball", GAME_WIDTH / 2 - 180, GAME_HEIGHT / 2 + 100);
        }
    }

    private void renderParticles() {
        for (Particle particle : gameEngineRef.getParticleSystem().getParticles()) {
            double life = particle.getLife();
            Color color = particle.getColor();
            Color fadedColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), life);
            gc.setFill(fadedColor);
            double size = particle.getSize();
            gc.fillOval(particle.getX() - size / 2, particle.getY() - size / 2, size, size);
        }
    }

    private void renderAnimatedPowerUp(PowerUp powerUp) {
        int px = powerUp.getX();
        int py = powerUp.getY();

        Image shadowImg = assetManager.getBonusBlockShadowImg();
        if (shadowImg != null) {
            gc.drawImage(shadowImg, px, py + 2, 38, 18);
        }

        PowerUpTypes powerUpType = powerUp.getType();
        Image spriteMap = assetManager.getPowerUpSpriteMap(powerUpType);

        if (spriteMap != null) {
            int frameWidth = 38;
            int frameHeight = 18;
            int frameX = powerUp.getFrameX();
            int frameY = powerUp.getFrameY();
            int sourceX = frameX * frameWidth;
            int sourceY = frameY * frameHeight;
            gc.drawImage(spriteMap, sourceX, sourceY, frameWidth, frameHeight, px, py, 38, 18);
        } else {
            if (powerUp instanceof ExpandPaddlePowerUp) {
                gc.setFill(Color.GOLD);
            } else if (powerUp instanceof FastBallPowerUp) {
                gc.setFill(Color.CYAN);
            } else {
                gc.setFill(Color.MAGENTA);
            }
            gc.fillOval(px, py, 20, 20);
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            String letter = powerUp instanceof ExpandPaddlePowerUp ? "E" : "F";
            gc.fillText(letter, px + 6, py + 15);
        }
    }

    private void renderPauseOverlay() {
        gc.setFill(Color.rgb(0, 0, 0, 0.5));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        gc.fillText("PAUSED", GAME_WIDTH / 2 - 90, GAME_HEIGHT / 2);
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        gc.fillText("Press P to Resume", GAME_WIDTH / 2 - 80, GAME_HEIGHT / 2 + 50);
    }

    private void renderBrickWithImage(Brick brick) {
        int bx = brick.getX();
        int by = brick.getY();
        int bw = brick.getWidth();
        int bh = brick.getHeight();

        Image shadowImg = assetManager.getBlockShadowImg();
        if (shadowImg != null) {
            gc.drawImage(shadowImg, bx + 2, by + 2, bw, bh);
        }

        Image brickImg = null;
        if (brick instanceof ColoredBrick) {
            ColoredBrick coloredBrick = (ColoredBrick) brick;
            brickImg = assetManager.getBrickImage(coloredBrick.getColor());
        } else if (brick instanceof UnbreakableBrick) {
            brickImg = assetManager.getBrickImage("GOLD");
        } else if (brick instanceof StrongBrick || brick instanceof ExtraStrongBrick) {
            brickImg = assetManager.getBrickImage("GRAY");
        }

        if (brickImg != null) {
            gc.drawImage(brickImg, bx, by, bw, bh);
        } else {
            renderBrick(brick);
        }
    }

    private void renderBrick(Brick brick) {
        if (brick instanceof ColoredBrick) {
            ColoredBrick coloredBrick = (ColoredBrick) brick;
            String colorName = coloredBrick.getColor();
            switch (colorName) {
                case "RED": gc.setFill(Color.rgb(255, 50, 50)); break;
                case "YELLOW": gc.setFill(Color.rgb(255, 230, 0)); break;
                case "BLUE": gc.setFill(Color.rgb(50, 100, 255)); break;
                case "MAGENTA": gc.setFill(Color.rgb(255, 50, 255)); break;
                case "LIME": gc.setFill(Color.rgb(100, 255, 50)); break;
                case "WHITE": gc.setFill(Color.rgb(240, 240, 240)); break;
                case "ORANGE": gc.setFill(Color.rgb(255, 150, 50)); break;
                case "CYAN": gc.setFill(Color.rgb(50, 230, 255)); break;
                default: gc.setFill(Color.LIGHTGRAY);
            }
        } else if (brick instanceof UnbreakableBrick) {
            gc.setFill(Color.DARKGOLDENROD);
        } else if (brick instanceof ExtraStrongBrick) {
            int hitPoints = brick.getHitPoints();
            if (hitPoints == 5) gc.setFill(Color.PURPLE);
            else if (hitPoints == 4) gc.setFill(Color.MEDIUMPURPLE);
            else if (hitPoints == 3) gc.setFill(Color.PLUM);
            else if (hitPoints == 2) gc.setFill(Color.VIOLET);
            else gc.setFill(Color.LAVENDER);
        } else if (brick instanceof StrongBrick) {
            int hitPoints = brick.getHitPoints();
            if (hitPoints == 3) gc.setFill(Color.rgb(130, 130, 130));
            else if (hitPoints == 2) gc.setFill(Color.rgb(170, 170, 170));
            else gc.setFill(Color.rgb(200, 200, 200));
        } else {
            gc.setFill(Color.DODGERBLUE);
        }

        gc.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
    }

    private void renderPaddle(Paddle paddle) {
        int px = paddle.getX();
        int py = paddle.getY();
        int pw = paddle.getWidth();
        int ph = paddle.getHeight();
        boolean isExpanded = paddle.isExpanded();

        Image shadowImg = isExpanded ? assetManager.getPaddleWideShadowImg() : assetManager.getPaddleStdShadowImg();
        if (shadowImg != null) {
            gc.drawImage(shadowImg, px + 2, py + 2, pw, ph);
        }

        Image paddleImg = isExpanded ? assetManager.getPaddleWideSpriteMapImg() : assetManager.getPaddleStdSpriteMapImg();
        if (paddleImg != null) {
            int frameWidth = 80;
            int frameHeight = 22;
            if(paddle.isExpanded()){
                frameHeight = 22;
                frameWidth = 121;
            }
            //TODO: fix frame size for wide paddle
            int frameX = paddle.getFrameX();
            int frameY = paddle.getFrameY();
            int sourceX = frameX * frameWidth;
            int sourceY = frameY * frameHeight;
            gc.drawImage(paddleImg, sourceX, sourceY, frameWidth, frameHeight, px, py, frameWidth, frameHeight);
        } else {
            gc.setFill(isExpanded ? Color.GOLD : Color.LIMEGREEN);
            gc.fillRoundRect(px, py, pw, ph, 10, 10);
            gc.setStroke(isExpanded ? Color.ORANGE : Color.DARKGREEN);
            gc.setLineWidth(2);
            gc.strokeRoundRect(px, py, pw, ph, 10, 10);
        }
    }

    private void renderBall(Ball ball) {
        int bx = ball.getX();
        int by = ball.getY();
        int bw = ball.getWidth();
        int bh = ball.getHeight();

        Image shadowImg = assetManager.getBallShadowImg();
        if (shadowImg != null) {
            gc.drawImage(shadowImg, bx + 1, by + 1, bw, bh);
        }

        Image ballImg = assetManager.getBallImg();
        if (ballImg != null) {
            gc.drawImage(ballImg, bx, by, bw, bh);
        } else {
            gc.setFill(Color.YELLOW);
            gc.fillOval(bx, by, bw, bh);
            gc.setFill(Color.WHITE);
            gc.fillOval(bx + 2, by + 2, 3, 3);
        }
    }

    private void renderBlink(Blink blink) {
        int blinkX = blink.getX();
        int blinkY = blink.getY();
        int blinkWidth = blink.getWidth();
        int blinkHeight = blink.getHeight();

        Image blinkMapImg = assetManager.getBlinkMapImg();

        if (blinkMapImg != null) {
            // Blink sprite sheet: 8 frames wide (38px each), 3 frames tall (20px each)
            int frameWidth = 38;
            int frameHeight = 20;
            int frameX = blink.getFrameX();
            int frameY = blink.getFrameY();
            int sourceX = frameX * frameWidth;
            int sourceY = frameY * frameHeight;

            gc.drawImage(blinkMapImg, sourceX, sourceY, frameWidth, frameHeight,
                        blinkX, blinkY, blinkWidth, blinkHeight);
        }
    }
}
