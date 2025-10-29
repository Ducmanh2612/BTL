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

public class MenuView extends StackPane {
    private static MenuView instance;
    private Canvas canvas;
    private GraphicsContext gc;
    private AssetManager assetManager;
    private double animationTime = 0;

    private MenuView() {
        canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        assetManager = AssetManager.getInstance();
        getChildren().add(canvas);
    }

    public static MenuView getInstance() {
        if (instance == null) {
            instance = new MenuView();
        }
        return instance;
    }

    public void render(double deltaTime) {
        animationTime += deltaTime;

        // Draw background
        gc.setFill(Color.rgb(20, 20, 40));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        drawMenuBackground();

        // Draw title with animation
        drawTitle();

        // Draw instructions with blinking effect
        drawInstructions();

        // Draw decorative elements
        drawDecorativeElements();
    }

    private void drawMenuBackground() {
        Image pattern = assetManager.getBackgroundPattern(1);
        if (pattern != null) {
            ImagePattern patternFill = new ImagePattern(pattern, 0, 0,
                    pattern.getWidth(), pattern.getHeight(), false);
            gc.setFill(patternFill);
            gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        }

        // Add overlay for better text visibility
        gc.setFill(Color.rgb(0, 0, 0, 0.4));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
    }

    private void drawTitle() {
        // Main title with glow effect
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 72));

        // Glow effect
        gc.setFill(Color.rgb(100, 200, 255, 0.5));
        gc.fillText("ARKANOID", GAME_WIDTH / 2 - 200, GAME_HEIGHT / 2 - 100);
        gc.fillText("ARKANOID", GAME_WIDTH / 2 - 196, GAME_HEIGHT / 2 - 100);

        // Main text
        gc.setFill(Color.CYAN);
        gc.fillText("ARKANOID", GAME_WIDTH / 2 - 198, GAME_HEIGHT / 2 - 100);

        // Subtitle
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        gc.setFill(Color.WHITE);
        gc.fillText("FX Edition", GAME_WIDTH / 2 - 65, GAME_HEIGHT / 2 - 40);
    }

    private void drawInstructions() {
        // Blinking "Press SPACE to Start" text
        double blinkSpeed = 2.0;
        double opacity = (Math.sin(animationTime * blinkSpeed) + 1) / 2;

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        gc.setFill(Color.rgb(255, 255, 255, opacity));
        gc.fillText("Press SPACE to Start", GAME_WIDTH / 2 - 140, GAME_HEIGHT / 2 + 50);

        // Controls info
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        gc.setFill(Color.LIGHTGRAY);
        gc.fillText("Controls:", GAME_WIDTH / 2 - 40, GAME_HEIGHT / 2 + 120);

        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        gc.fillText("A / D - Move Paddle", GAME_WIDTH / 2 - 80, GAME_HEIGHT / 2 + 150);
        gc.fillText("P - Pause / Resume", GAME_WIDTH / 2 - 80, GAME_HEIGHT / 2 + 175);
    }

    private void drawDecorativeElements() {
        // Draw some decorative bricks at the top
        Image brickImg = assetManager.getBrickImage("CYAN");
        if (brickImg != null) {
            int brickWidth = 38;
            int brickHeight = 20;
            int spacing = 10;
            int totalBricks = 8;
            int startX = (GAME_WIDTH - (totalBricks * brickWidth + (totalBricks - 1) * spacing)) / 2;
            int y = 50;

            String[] colors = {"RED", "YELLOW", "BLUE", "MAGENTA", "LIME", "ORANGE", "CYAN", "WHITE"};
            for (int i = 0; i < totalBricks; i++) {
                Image colorBrick = assetManager.getBrickImage(colors[i]);
                if (colorBrick != null) {
                    int x = startX + i * (brickWidth + spacing);
                    gc.drawImage(colorBrick, x, y, brickWidth, brickHeight);
                }
            }
        }

        // Draw decorative ball
        Image ballImg = assetManager.getBallImg();
        if (ballImg != null) {
            double ballX = GAME_WIDTH / 2 - 6 + Math.sin(animationTime * 2) * 50;
            double ballY = GAME_HEIGHT / 2 + 220;
            gc.drawImage(ballImg, ballX, ballY, 12, 12);
        }

        // Draw decorative paddle
        Image paddleImg = assetManager.getPaddleStdImg();
        if (paddleImg != null) {
            int paddleX = GAME_WIDTH / 2 - 40;
            int paddleY = GAME_HEIGHT / 2 + 250;
            gc.drawImage(paddleImg, paddleX, paddleY, 80, 22);
        }
    }
}
