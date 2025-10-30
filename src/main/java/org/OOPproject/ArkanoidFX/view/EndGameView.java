package org.OOPproject.ArkanoidFX.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
    }

    public void render(double deltaTime) {
        animationTime += deltaTime;

        // Draw background
        gc.setFill(Color.rgb(20, 20, 40));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        drawEndGameBackground();

        // Draw game over content
        drawGameOverTitle();
        drawStats();
        drawInstructions();
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
        gc.translate(GAME_WIDTH / 2, GAME_HEIGHT / 2 - 80);
        gc.scale(pulseScale, pulseScale);

        // Shadow/glow effect
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 64));
        gc.setFill(Color.rgb(255, 0, 0, 0.5));
        gc.fillText("GAME OVER", -155, 5);

        // Main text
        gc.setFill(Color.RED);
        gc.fillText("GAME OVER", -158, 0);

        gc.restore();
    }

    private void drawStats() {
        // Stats box background
        gc.setFill(Color.rgb(0, 0, 0, 0.7));
        gc.fillRoundRect(GAME_WIDTH / 2 - 150, GAME_HEIGHT / 2 - 20, 300, 120, 15, 15);

        // Border
        gc.setStroke(Color.CYAN);
        gc.setLineWidth(3);
        gc.strokeRoundRect(GAME_WIDTH / 2 - 150, GAME_HEIGHT / 2 - 20, 300, 120, 15, 15);

        // Final Score
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        gc.setFill(Color.YELLOW);
        gc.fillText("FINAL SCORE", GAME_WIDTH / 2 - 85, GAME_HEIGHT / 2 + 15);

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        gc.setFill(Color.WHITE);
        gc.fillText(String.valueOf(finalScore), GAME_WIDTH / 2 - 40, GAME_HEIGHT / 2 + 55);

        // Level Reached
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        gc.setFill(Color.LIGHTGRAY);
        gc.fillText("Level Reached: " + levelReached, GAME_WIDTH / 2 - 70, GAME_HEIGHT / 2 + 85);
    }

    private void drawInstructions() {
        // Blinking instruction
        double blinkSpeed = 2.5;
        double opacity = (Math.sin(animationTime * blinkSpeed) + 1) / 2;

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gc.setFill(Color.rgb(255, 255, 255, opacity));
        gc.fillText("Press SPACE to Play Again", GAME_WIDTH / 2 - 155, GAME_HEIGHT / 2 + 150);

        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        gc.setFill(Color.rgb(200, 200, 200, opacity * 0.8));
        gc.fillText("Press ESC to Return to Menu", GAME_WIDTH / 2 - 130, GAME_HEIGHT / 2 + 180);
    }

    private void drawDecorativeElements() {
        // Floating broken bricks effect
        drawFloatingBricks();

        // Bottom decoration
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        gc.setFill(Color.DARKGRAY);
        gc.fillText("Thanks for playing!", GAME_WIDTH / 2 - 70, GAME_HEIGHT - 30);
    }

    private void drawFloatingBricks() {
        String[] colors = {"RED", "YELLOW", "BLUE", "MAGENTA", "ORANGE", "CYAN"};
        int numBricks = 6;

        for (int i = 0; i < numBricks; i++) {
            Image brickImg = assetManager.getBrickImage(colors[i]);
            if (brickImg != null) {
                // Calculate floating position
                double angle = animationTime * 0.5 + i * Math.PI * 2 / numBricks;
                double radius = 150;
                double x = GAME_WIDTH / 2 + Math.cos(angle) * radius - 19;
                double y = 80 + Math.sin(angle) * 40;
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
    }
}
