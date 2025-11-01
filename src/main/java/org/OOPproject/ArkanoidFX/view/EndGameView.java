package org.OOPproject.ArkanoidFX.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.OOPproject.ArkanoidFX.model.Bricks.BrickType;

import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_HEIGHT;
import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_WIDTH;

public class EndGameView extends StackPane {
    private static EndGameView instance;
    private Canvas canvas;
    private GraphicsContext gc;
    private AssetManager assetManager;
    private double animationTime = 0;

    private int finalScore;
    private int levelReached;
    private String playerName = "";
    private boolean isEnteringName = true;
    private boolean showCursor = true;
    private double cursorBlinkTime = 0;

    private EndGameView() {
        canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        assetManager = AssetManager.getInstance();
        getChildren().add(canvas);
    }

    public static EndGameView getInstance() {
        if (instance == null) {
            instance = new EndGameView();
        }
        return instance;
    }

    public void setGameStats(int score, int level) {
        this.finalScore = score;
        this.levelReached = level;
        this.playerName = "";
        this.isEnteringName = true;
    }

    public void addCharacterToName(char c) {
        if (isEnteringName && playerName.length() < 15) {
            playerName += c;
        }
    }

    public void removeLastCharacter() {
        if (isEnteringName && playerName.length() > 0) {
            playerName = playerName.substring(0, playerName.length() - 1);
        }
    }

    public void finishEnteringName() {
        if (playerName.trim().isEmpty()) {
            playerName = "Anonymous";
        }
        isEnteringName = false;
    }

    public boolean isEnteringName() {
        return isEnteringName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void render(double deltaTime) {
        animationTime += deltaTime;
        cursorBlinkTime += deltaTime;

        if (cursorBlinkTime > 0.5) {
            showCursor = !showCursor;
            cursorBlinkTime = 0;
        }

        // Draw background
        gc.setFill(Color.rgb(20, 20, 40));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        drawEndGameBackground();

        // Draw game over content
        drawGameOverTitle();
        drawStats();

        if (isEnteringName) {
            drawNameInput();
        } else {
            drawInstructions();
        }

        drawDecorativeElements();
    }

    private void drawEndGameBackground() {
        Image pattern = assetManager.getBackgroundPattern(levelReached);
        if (pattern != null) {
            ImagePattern patternFill = new ImagePattern(pattern, 0, 0,
                    pattern.getWidth(), pattern.getHeight(), false);
            gc.setFill(patternFill);
            gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        }

        // Dark overlay
        gc.setFill(Color.rgb(0, 0, 0, 0.6));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
    }

    private void drawGameOverTitle() {
        // Animated "GAME OVER" with pulse effect
        double pulseScale = 1.0 + Math.sin(animationTime * 3) * 0.05;

        gc.save();
        gc.translate(GAME_WIDTH / 2, GAME_HEIGHT / 2 - 100);
        gc.scale(pulseScale, pulseScale);

        // Shadow/glow effect
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 64));
        gc.setFill(Color.rgb(255, 0, 0, 0.5));
        gc.fillText("GAME OVER", -200, -25);

        // Main text
        gc.setFill(Color.RED);
        gc.fillText("GAME OVER", -195, -20);

        gc.restore();
    }

    private void drawStats() {
        // Stats box background
        gc.setFill(Color.rgb(0, 0, 0, 0.7));
        gc.fillRoundRect(GAME_WIDTH / 2 - 150, GAME_HEIGHT / 2 - 40, 300, 100, 15, 15);

        // Border
        gc.setStroke(Color.CYAN);
        gc.setLineWidth(3);
        gc.strokeRoundRect(GAME_WIDTH / 2 - 150, GAME_HEIGHT / 2 - 40, 300, 100, 15, 15);

        // Final Score
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gc.setFill(Color.YELLOW);
        gc.fillText("FINAL SCORE", GAME_WIDTH / 2 - 75, GAME_HEIGHT / 2 - 5);

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        gc.setFill(Color.WHITE);
        gc.fillText(String.valueOf(finalScore), GAME_WIDTH / 2 - 30, GAME_HEIGHT / 2 + 30);

        // Level Reached
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        gc.setFill(Color.LIGHTGRAY);
        gc.fillText("Level: " + levelReached, GAME_WIDTH / 2 - 35, GAME_HEIGHT / 2 + 55);
    }

    private void drawNameInput() {
        // Name input box
        gc.setFill(Color.rgb(0, 0, 0, 0.8));
        gc.fillRoundRect(GAME_WIDTH / 2 - 180, GAME_HEIGHT / 2 + 80, 360, 80, 15, 15);

        // Prompt
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gc.setFill(Color.YELLOW);
        gc.fillText("Enter Your Name:", GAME_WIDTH / 2 - 90, GAME_HEIGHT / 2 + 105);

        // Name input field
        gc.setFill(Color.rgb(30, 30, 50));
        gc.fillRoundRect(GAME_WIDTH / 2 - 150, GAME_HEIGHT / 2 + 115, 300, 35, 8, 8);

        // Display name with cursor
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gc.setFill(Color.WHITE);
        String displayName = playerName.isEmpty() ? "" : playerName;
        gc.fillText(displayName, GAME_WIDTH / 2 - 140, GAME_HEIGHT / 2 + 140);

        if (showCursor) {
            Text tempText = new Text(displayName);
            tempText.setFont(gc.getFont());
            double textWidth = tempText.getLayoutBounds().getWidth();

            double cursorX = GAME_WIDTH / 2 - 140 + textWidth + 2; // +2 for small spacing
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            gc.strokeLine(cursorX, GAME_HEIGHT / 2 + 120, cursorX, GAME_HEIGHT / 2 + 150);
        }

        // Instructions
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        gc.setFill(Color.LIGHTGRAY);
        gc.fillText("Press ENTER to confirm", GAME_WIDTH / 2 - 95, GAME_HEIGHT / 2 + 185);
    }

    private void drawInstructions() {
        // Blinking instruction
        double blinkSpeed = 2.5;
        double opacity = (Math.sin(animationTime * blinkSpeed) + 1) / 2;

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gc.setFill(Color.rgb(255, 255, 255, opacity));
        gc.fillText("Press SPACE to Play Again", GAME_WIDTH / 2 - 155, GAME_HEIGHT / 2 + 120);

        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        gc.setFill(Color.rgb(200, 200, 200, opacity * 0.8));
        gc.fillText("Press ESC to Return to Menu", GAME_WIDTH / 2 - 130, GAME_HEIGHT / 2 + 150);
    }

    private void drawDecorativeElements() {
        // Draw some decorative bricks at the top
        drawFloatingBricks();
//        Image brickImg = assetManager.getBrickImage(BrickType.CYAN);
//        if (brickImg != null) {
//            int brickWidth = 38;
//            int brickHeight = 20;
//            int spacing = 10;
//            int totalBricks = 8;
//            int startX = (GAME_WIDTH - (totalBricks * brickWidth + (totalBricks - 1) * spacing)) / 2;
//            int y = 50;
//
//            BrickType[] colors = {BrickType.RUBY, BrickType.YLLW, BrickType.BLUE, BrickType.MGNT, BrickType.ORNG, BrickType.CYAN, BrickType.CYAN, BrickType.LIME};
//            for (int i = 0; i < totalBricks; i++) {
//                Image colorBrick = assetManager.getBrickImage(colors[i]);
//                if (colorBrick != null) {
//                    int x = startX + i * (brickWidth + spacing);
//                    gc.drawImage(colorBrick, x, y, brickWidth, brickHeight);
//                }
//            }
//        }
//
//        // Draw decorative ball
//        Image ballImg = assetManager.getBallImg();
//        if (ballImg != null) {
//            double ballX = GAME_WIDTH / 2 - 6 + Math.sin(animationTime * 2) * 50;
//            double ballY = GAME_HEIGHT / 2 + 240;
//            gc.drawImage(ballImg, ballX, ballY, 12, 12);
//        }
//
//        // Draw decorative paddle
//        Image paddleImg = assetManager.getPaddleStdImg();
//        if (paddleImg != null) {
//            int paddleX = GAME_WIDTH / 2 - 40;
//            int paddleY = GAME_HEIGHT / 2 + 270;
//            gc.drawImage(paddleImg, paddleX, paddleY, 80, 22);
//        }

        // Bottom decoration
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        gc.setFill(Color.DARKGRAY);
        gc.fillText("Thanks for playing!", GAME_WIDTH / 2 - 70, GAME_HEIGHT - 30);
    }

    private void drawFloatingBricks() {
        BrickType[] colors = {BrickType.RUBY, BrickType.YLLW, BrickType.BLUE, BrickType.MGNT, BrickType.ORNG, BrickType.CYAN};
        int numBricks = 6;

        for (int i = 0; i < numBricks; i++) {
            Image brickImg = assetManager.getBrickImage(colors[i]);
            if (brickImg != null) {
                // Calculate floating position
                double angle = animationTime * 0.5 + i * Math.PI * 2 / numBricks;
                double radius = 150;
                double x = GAME_WIDTH / 2 + Math.cos(angle) * radius - 19;
                double y = 50 + Math.sin(angle) * 30;
                double rotation = angle * 20;

                gc.save();
                gc.translate(x + 19, y + 10);
                gc.rotate(rotation);
                gc.setGlobalAlpha(0.6);
                gc.drawImage(brickImg, -19, -10, 38, 20);
                gc.restore();
            }
        }
    }

    public void resetAnimation() {
        animationTime = 0;
        cursorBlinkTime = 0;
        showCursor = true;
    }
}
