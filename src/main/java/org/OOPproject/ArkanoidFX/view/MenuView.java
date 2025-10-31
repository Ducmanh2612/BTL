package org.OOPproject.ArkanoidFX.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.OOPproject.ArkanoidFX.model.LeaderboardManager;
import org.OOPproject.ArkanoidFX.model.LeaderboardManager.LeaderboardEntry;

import java.util.List;

import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_HEIGHT;
import static org.OOPproject.ArkanoidFX.utils.Constants.GAME_WIDTH;

public class MenuView extends StackPane {
    private static MenuView instance;
    private Canvas canvas;
    private GraphicsContext gc;
    private AssetManager assetManager;
    private LeaderboardManager leaderboardManager;
    private double animationTime = 0;
    private boolean showingLeaderboard = false;

    private MenuView() {
        canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        assetManager = AssetManager.getInstance();
        leaderboardManager = LeaderboardManager.getInstance();
        getChildren().add(canvas);
    }

    public static MenuView getInstance() {
        if (instance == null) {
            instance = new MenuView();
        }
        return instance;
    }

    public void setShowingLeaderboard(boolean showing) {
        this.showingLeaderboard = showing;
    }

    public boolean isShowingLeaderboard() {
        return showingLeaderboard;
    }

    public void render(double deltaTime) {
        animationTime += deltaTime;

        // Draw background
        gc.setFill(Color.rgb(20, 20, 40));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        drawMenuBackground();

        if (showingLeaderboard) {
            renderLeaderboard();
        } else {
            renderMainMenu();
        }
    }

    private void drawMenuBackground() {
        Image pattern = assetManager.getBackgroundPattern(showingLeaderboard ? 2 : 1);
        if (pattern != null) {
            ImagePattern patternFill = new ImagePattern(pattern, 0, 0,
                    pattern.getWidth(), pattern.getHeight(), false);
            gc.setFill(patternFill);
            gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        }

        // Add overlay for better text visibility
        gc.setFill(Color.rgb(0, 0, 0, showingLeaderboard ? 0.5 : 0.4));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
    }

    // ==================== MAIN MENU ====================
    private void renderMainMenu() {
        drawTitle();
        drawMainMenuInstructions();
        drawDecorativeElements();
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

    private void drawMainMenuInstructions() {
        // Blinking "Press SPACE to Start" text
        double blinkSpeed = 2.0;
        double opacity = (Math.sin(animationTime * blinkSpeed) + 1) / 2;

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        gc.setFill(Color.rgb(255, 255, 255, opacity));
        gc.fillText("Press SPACE to Start", GAME_WIDTH / 2 - 140, GAME_HEIGHT / 2 + 50);

        // Leaderboard instruction
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        gc.setFill(Color.rgb(255, 215, 0, opacity * 0.9));
        gc.fillText("Press L for Leaderboard", GAME_WIDTH / 2 - 130, GAME_HEIGHT / 2 + 90);

        // Controls info
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        gc.setFill(Color.LIGHTGRAY);
        gc.fillText("Controls:", GAME_WIDTH / 2 - 40, GAME_HEIGHT / 2 + 140);

        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        gc.fillText("A / D - Move Paddle", GAME_WIDTH / 2 - 80, GAME_HEIGHT / 2 + 170);
        gc.fillText("P - Pause / Resume", GAME_WIDTH / 2 - 80, GAME_HEIGHT / 2 + 195);
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
            double ballY = GAME_HEIGHT / 2 + 240;
            gc.drawImage(ballImg, ballX, ballY, 12, 12);
        }

        // Draw decorative paddle
        Image paddleImg = assetManager.getPaddleStdImg();
        if (paddleImg != null) {
            int paddleX = GAME_WIDTH / 2 - 40;
            int paddleY = GAME_HEIGHT / 2 + 270;
            gc.drawImage(paddleImg, paddleX, paddleY, 80, 22);
        }
    }

    // ==================== LEADERBOARD ====================
    private void renderLeaderboard() {
        drawLeaderboardTitle();
        drawLeaderboardEntries();
        drawLeaderboardInstructions();
        drawLeaderboardDecorativeElements();
    }

    private void drawLeaderboardTitle() {
        // Animated title with glow
        double pulseScale = 1.0 + Math.sin(animationTime * 2) * 0.03;

        gc.save();
        gc.translate(GAME_WIDTH / 2, 70);
        gc.scale(pulseScale, pulseScale);

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 56));
        gc.setFill(Color.rgb(255, 215, 0, 0.5));
        gc.fillText("LEADERBOARD", -165, 5);

        gc.setFill(Color.GOLD);
        gc.fillText("LEADERBOARD", -168, 0);

        gc.restore();

        // Decorative line
        gc.setStroke(Color.CYAN);
        gc.setLineWidth(3);
        gc.strokeLine(GAME_WIDTH / 2 - 200, 90, GAME_WIDTH / 2 + 200, 90);
    }

    private void drawLeaderboardEntries() {
        List<LeaderboardEntry> topEntries = leaderboardManager.getTopEntries(10);

        if (topEntries.isEmpty()) {
            // No entries message
            gc.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
            gc.setFill(Color.LIGHTGRAY);
            gc.fillText("No scores yet. Be the first!", GAME_WIDTH / 2 - 150, GAME_HEIGHT / 2);
            return;
        }

        // Draw header
        int startY = 130;
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gc.setFill(Color.YELLOW);
        gc.fillText("RANK", 80, startY);
        gc.fillText("NAME", 180, startY);
        gc.fillText("SCORE", 380, startY);
        gc.fillText("LEVEL", 520, startY);

        // Draw entries
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        int y = startY + 40;
        int rank = 1;

        for (LeaderboardEntry entry : topEntries) {
            // Rank background
            Color bgColor = getRankColor(rank);
            gc.setFill(Color.rgb((int)(bgColor.getRed() * 255),
                    (int)(bgColor.getGreen() * 255),
                    (int)(bgColor.getBlue() * 255), 0.3));
            gc.fillRoundRect(60, y - 22, 520, 30, 8, 8);

            // Rank
            gc.setFill(bgColor);
            gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            gc.fillText("#" + rank, 80, y);

            // Name
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
            String displayName = entry.getPlayerName();
            if (displayName.length() > 20) {
                displayName = displayName.substring(0, 17) + "...";
            }
            gc.fillText(displayName, 180, y);

            // Score
            gc.setFill(Color.LIGHTGREEN);
            gc.fillText(String.valueOf(entry.getScore()), 380, y);

            // Level
            gc.setFill(Color.CYAN);
            gc.fillText(String.valueOf(entry.getLevel()), 520, y);

            y += 35;
            rank++;
        }
    }

    private Color getRankColor(int rank) {
        switch (rank) {
            case 1: return Color.GOLD;
            case 2: return Color.SILVER;
            case 3: return Color.rgb(205, 127, 50); // Bronze
            default: return Color.LIGHTGRAY;
        }
    }

    private void drawLeaderboardInstructions() {
        double blinkSpeed = 2.0;
        double opacity = (Math.sin(animationTime * blinkSpeed) + 1) / 2;

        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        gc.setFill(Color.rgb(255, 255, 255, opacity));
        gc.fillText("Press ESC to return to Menu", GAME_WIDTH / 2 - 150, GAME_HEIGHT - 50);
    }

    private void drawLeaderboardDecorativeElements() {
        // Trophy icon animation
        double bounceY = Math.sin(animationTime * 3) * 5;

        gc.setFill(Color.GOLD);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gc.fillText("🏆", 30, 60 + bounceY);
        gc.fillText("🏆", GAME_WIDTH - 60, 60 + bounceY);
    }

    public void resetAnimation() {
        animationTime = 0;
    }
}
