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
import org.OOPproject.ArkanoidFX.utils.Constants;
import org.OOPproject.ArkanoidFX.utils.GameState;

import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_HEIGHT;
import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_WIDTH;

public class GameView extends StackPane {
    private static GameEngine gameEngineRef;
    private static GameView instance;

    private Canvas canvas;
    private GraphicsContext gc;
    private AssetManager assetManager;

    // Door animation state
    private boolean isDoorOpen = false;
    private double doorOpenProgress = 0.0; // 0.0 = closed, 1.0 = fully open

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

        GameState state = gameEngineRef.getGameState();
        renderGame();

        // Update door animation based on enemies
        updateDoorAnimation();

        // Draw border frame with animated door
        drawBorderFrameWithDoor();

        if (state.equals(GameState.PAUSED)) {
            renderPauseOverlay();
        }
    }

    private void updateDoorAnimation() {
        boolean shouldOpenDoor = gameEngineRef.isSpawningEnemies();

        if (shouldOpenDoor && !isDoorOpen) {
            isDoorOpen = true;
            System.out.println("Door opening...");
        } else if (!shouldOpenDoor && isDoorOpen) {
            isDoorOpen = false;
            System.out.println("Door closing...");
        }

        // Animate door opening/closing
        if (isDoorOpen && doorOpenProgress < 1.0) {
            doorOpenProgress += 0.08; // Fast open speed
            if (doorOpenProgress > 1.0) doorOpenProgress = 1.0;
        } else if (!isDoorOpen && doorOpenProgress > 0.0) {
            doorOpenProgress -= 0.05; // Slower close speed
            if (doorOpenProgress < 0.0) doorOpenProgress = 0.0;
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

    private void drawBorderFrameWithDoor() {
        int tileSize = 20;
        int sideHeight = GAME_HEIGHT / 6; //6 frame

        Image sidePattern = assetManager.getBorderSideVertical();
        if (sidePattern != null) {
            for (int i = 0; i < 6; i++) {
                gc.drawImage(sidePattern, 0, i * sideHeight + 20, tileSize, sideHeight + 20);
            }
            for (int i = 0; i < 6; i++) {
                gc.drawImage(sidePattern, GAME_WIDTH - tileSize, i * sideHeight + 20, tileSize, sideHeight + 20);
            }
        }

        int centerX = GAME_WIDTH / 2;
        int doorGap = 100;
        int doorWidth = 80;

        int leftDoorX = centerX - doorWidth - doorGap / 2;
        int rightDoorX = centerX + doorGap / 2;

        int rightCornerStart = GAME_WIDTH - tileSize;

        Image topFrame = assetManager.getBorderTopFrameImg();
        if (topFrame != null) {
            int firstSectionStart = tileSize;
            int firstSectionEnd = leftDoorX;
            int firstSpacing = firstSectionEnd - firstSectionStart;
            gc.drawImage(topFrame, firstSectionStart, 0, firstSpacing , tileSize);

            int middleSectionStart = leftDoorX + doorWidth;
            int middleSectionEnd = rightDoorX;
            int middleSpacing = (middleSectionEnd - middleSectionStart);
            gc.drawImage(topFrame, middleSectionStart, 0, middleSpacing , tileSize);

            int lastSectionStart = rightDoorX + doorWidth;
            int lastSectionEnd = rightCornerStart;
            int lastSpacing = (lastSectionEnd - lastSectionStart);
            gc.drawImage(topFrame, lastSectionStart, 0, lastSpacing, tileSize);
        }

        Image doorImg = assetManager.getBorderTopDoorImg();
        if (doorImg != null) {
            int doorSlide = (int)(doorOpenProgress * 40); // Doors slide apart 40px when open
            gc.drawImage(doorImg, leftDoorX - doorSlide, 0, doorWidth, tileSize);

            gc.drawImage(doorImg, rightDoorX + doorSlide, 0, doorWidth, tileSize);
        }

        Image topLeft = assetManager.getBorderTopLeftImg();
        if (topLeft != null) {
            gc.drawImage(topLeft, 0, 0, tileSize, tileSize);
        }

        Image topRight = assetManager.getBorderTopRightImg();
        if (topRight != null) {
            gc.drawImage(topRight, GAME_WIDTH - tileSize, 0, tileSize, tileSize);
        }

        drawTopBarUI();
        drawBottomBarUI();
    }

    private void drawTopBarUI() {
        gc.setFill(Color.CYAN);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gc.fillText("LV " + gameEngineRef.getLevelNumber(), 20, 30);

        // Lives (hearts) below level
        int lives = gameEngineRef.getLives();
        Image heartImg = assetManager.getHeartImg();
        int heartSize = 16;
        int spacing = 3;
        int startX = 12;
        int startY = 38;

        if (heartImg != null) {
            for (int i = 0; i < lives; i++) {
                int x = startX + (heartSize + spacing) * i;
                gc.drawImage(heartImg, x, startY, heartSize, heartSize);
            }
            for (int i = lives; i < 3; i++) {
                int x = startX + (heartSize + spacing) * i;
                gc.setGlobalAlpha(0.3);
                gc.drawImage(heartImg, x, startY, heartSize, heartSize);
                gc.setGlobalAlpha(1.0);
            }
        }
    }

    private void drawBottomBarUI() {
        gc.setFill(Color.YELLOW);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gc.fillText("SCORE: " + gameEngineRef.getScore(), 70, GAME_HEIGHT - 25);

        // Hint in bottom-right
        gc.setFill(Color.LIGHTGRAY);
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gc.fillText("P: Pause", GAME_WIDTH - 120, GAME_HEIGHT - 25);
    }

    private void renderGame() {
        for (Brick brick : gameEngineRef.getBricks()) {
            renderBrickShadow(brick);
        }


        for (Brick brick : gameEngineRef.getBricks()) {
            renderBrickWithImage(brick);
        }

        for (Blink blink : gameEngineRef.getBlinks()) {
            renderBlink(blink);
        }

        for (PowerUp powerUp : gameEngineRef.getPowerUps()) {
            renderAnimatedPowerUp(powerUp);
        }

        for (Enemy enemy : gameEngineRef.getEnemies()) {
            renderEnemy(enemy);
        }

        for (Destroy destroy : gameEngineRef.getDestroys()) {
            renderExplosion(destroy);
        }

        renderPaddle(gameEngineRef.getPaddle());

        for (Ball ball : gameEngineRef.getBalls()) {
            renderBall(ball);
        }

        for (Bullet bullet : gameEngineRef.getBullets()) {
            renderBullet(bullet);
        }

        renderParticles();


        // Show instruction if any ball is stuck to paddle
        if (!gameEngineRef.isBallReleased()) {
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

        Image shadowImg = assetManager.getPowerupShadowImg();
        if (shadowImg != null) {
            gc.drawImage(shadowImg, px, py + 2, Constants.POWER_UP_WIDTH, Constants.POWER_UP_HEIGHT);
        }

        PowerUpTypes powerUpType = powerUp.getType();
        Image spriteMap = assetManager.getPowerUpSpriteMap(powerUpType);

        if (spriteMap != null) {
            int frameWidth = Constants.POWER_UP_WIDTH;
            int frameHeight = Constants.POWER_UP_HEIGHT;
            int frameX = powerUp.getFrameX();
            int frameY = powerUp.getFrameY();
            int sourceX = frameX * frameWidth;
            int sourceY = frameY * frameHeight;
            gc.drawImage(spriteMap, sourceX, sourceY, frameWidth, frameHeight, px, py, Constants.POWER_UP_WIDTH, Constants.POWER_UP_HEIGHT);
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

        Image brickImg = null;
        if (brick instanceof ColoredBrick) {
            ColoredBrick coloredBrick = (ColoredBrick) brick;
            brickImg = assetManager.getBrickImage(coloredBrick.getType());
        } else if (brick instanceof UnbreakableBrick) {
            brickImg = assetManager.getBrickImage(BrickType.GOLD);
        } else if (brick instanceof StrongBrick || brick instanceof ExtraStrongBrick) {
            brickImg = assetManager.getBrickImage(BrickType.GRAY);
        }

        if (brickImg != null) {
            gc.drawImage(brickImg, bx, by, bw, bh);
        } else {
            renderBrick(brick);
        }
    }

    private void renderBrickShadow(Brick brick) {

        int bx = brick.getX();
        int by = brick.getY();
        int bw = brick.getWidth();
        int bh = brick.getHeight();

        Image shadowImg = assetManager.getBlockShadowImg();
        if (shadowImg != null) {
            gc.drawImage(shadowImg, bx + 10, by + 10, bw, bh);
        }
    }

    private void renderBrick(Brick brick) {
        if (brick instanceof ColoredBrick) {
            ColoredBrick coloredBrick = (ColoredBrick) brick;
            switch (brick.getType()) {
                case BrickType.RUBY: gc.setFill(Color.rgb(255, 50, 50)); break;
                case BrickType.YLLW: gc.setFill(Color.rgb(255, 230, 0)); break;
                case BrickType.BLUE: gc.setFill(Color.rgb(50, 100, 255)); break;
                case BrickType.MGNT: gc.setFill(Color.rgb(255, 50, 255)); break;
                case BrickType.LIME: gc.setFill(Color.rgb(100, 255, 50)); break;
                case BrickType.WHIT: gc.setFill(Color.rgb(240, 240, 240)); break;
                case BrickType.ORNG: gc.setFill(Color.rgb(255, 150, 50)); break;
                case BrickType.CYAN: gc.setFill(Color.rgb(50, 230, 255)); break;
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
        boolean isGun = paddle.isGun();

        Image shadowImg = isExpanded ? assetManager.getPaddleWideShadowImg() : assetManager.getPaddleStdShadowImg();
        if (shadowImg != null) {
            gc.drawImage(shadowImg, px + 10, py + 10, pw, ph);
        }

        Image paddleImg = assetManager.getPaddleStdSpriteMapImg();;
        if(isGun) {
            paddleImg = assetManager.getPaddleGunSpriteMapImg();
        }
        else if (isExpanded) {
            paddleImg = assetManager.getPaddleWideSpriteMapImg();
        }
        if (paddleImg != null) {
            int frameWidth = Constants.PADDLE_DEFAULT_WIDTH;
            int frameHeight = Constants.PADDLE_HEIGHT;
            if(paddle.isGun()){
                frameWidth = Constants.PADDLE_DEFAULT_WIDTH;
                frameHeight = Constants.PADDLE_HEIGHT;
            }
            else if (paddle.isExpanded()) {
                frameWidth = Constants.PADDLE_EXPANDED_WIDTH;
                frameHeight = Constants.PADDLE_HEIGHT;
            }
            int frameX = paddle.getFrameX();
            int frameY = paddle.getFrameY();
            int sourceX = frameX * frameWidth;
            int sourceY = frameY * frameHeight;
            gc.drawImage(paddleImg, sourceX, sourceY, frameWidth, frameHeight, px, py, pw, ph);
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
            gc.drawImage(shadowImg, bx + 5, by + 5, bw, bh);
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

    private void renderBullet(Bullet bullet) {
        int bx = bullet.getX();
        int by = bullet.getY();
        int bw = bullet.getWidth();
        int bh = bullet.getHeight();

        Image bulletImg = assetManager.getBulletImg();
        if (bulletImg != null) {
            gc.drawImage(bulletImg, bx, by, bw, bh);
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
            int frameWidth = Constants.BRICK_WIDTH;
            int frameHeight = Constants.BRICK_HEIGHT;
            int frameX = blink.getFrameX();
            int frameY = blink.getFrameY();
            int sourceX = frameX * frameWidth;
            int sourceY = frameY * frameHeight;

            gc.drawImage(blinkMapImg, sourceX, sourceY, frameWidth, frameHeight,
                    blinkX, blinkY, blinkWidth, blinkHeight);
        }
    }

    private void renderEnemy(Enemy enemy) {
        int ex = enemy.getX();
        int ey = enemy.getY();
        int ew = enemy.getWidth();
        int eh = enemy.getHeight();

        Image enemyImg = assetManager.getEnemyMapImg();
        if (enemyImg != null) {
            int frameWidth = Constants.ENEMY_SIZE;
            int frameHeight = Constants.ENEMY_SIZE;
            int frameX = enemy.getFrameX();
            int frameY = enemy.getFrameY();
            int sourceX = frameX * frameWidth;
            int sourceY = frameY * frameHeight;
            gc.drawImage(enemyImg, sourceX, sourceY, frameWidth, frameHeight, ex, ey, ew, eh);
        }
    }

    private void renderExplosion(Destroy destroy) {
        int ex = destroy.getX();
        int ey = destroy.getY();
        int ew = destroy.getWidth();
        int eh = destroy.getHeight();

        Image destroyImg = assetManager.getExplosionMapImg();
        if (destroyImg != null) {
            int frameWidth = Constants.ENEMY_SIZE;
            int frameHeight = Constants.ENEMY_SIZE;
            int frameX = destroy.getFrameX();
            int frameY = destroy.getFrameY();
            int sourceX = frameX * frameWidth;
            int sourceY = frameY * frameHeight;
            gc.drawImage(destroyImg, sourceX, sourceY, frameWidth, frameHeight, ex, ey, ew, eh);
        }
    }
}
