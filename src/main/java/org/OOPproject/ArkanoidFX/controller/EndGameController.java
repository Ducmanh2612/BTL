package org.OOPproject.ArkanoidFX.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.OOPproject.ArkanoidFX.model.GameEngine;
import org.OOPproject.ArkanoidFX.view.EndGameView;

public class EndGameController {
    private static EndGameController instance;
    private final AnimationTimer endGameLoop;
    private EndGameView endGameView;
    private GameEngine gameEngine;
    private long lastFrameUpdate = 0;
    private Runnable onPlayAgain;
    private Runnable onReturnToMenu;

    private EndGameController(Scene scene) {
        endGameView = EndGameView.getInstance();
        gameEngine = GameEngine.getInstance();
        Pane root = (Pane) scene.getRoot();
        root.getChildren().add(endGameView);

        endGameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastFrameUpdate == 0) {
                    lastFrameUpdate = now;
                    return;
                }
                double deltaTime = (now - lastFrameUpdate) / 1_000_000_000.0;
                lastFrameUpdate = now;

                endGameView.render(deltaTime);
            }
        };
    }

    public static EndGameController getInstance(Scene scene) {
        if (instance == null) {
            instance = new EndGameController(scene);
        }
        return instance;
    }

    public void startEndGameLoop() {
        // Get stats from GameEngine
        int finalScore = gameEngine.getScore();
        int levelReached = gameEngine.getLevelNumber();

        // Pass to view
        endGameView.setGameStats(finalScore, levelReached);
        endGameView.resetAnimation();
        lastFrameUpdate = 0;
        endGameLoop.start();
    }

    public void stopEndGameLoop() {
        endGameLoop.stop();
    }

    public void setOnPlayAgain(Runnable onPlayAgain) {
        this.onPlayAgain = onPlayAgain;
    }

    public void setOnReturnToMenu(Runnable onReturnToMenu) {
        this.onReturnToMenu = onReturnToMenu;
    }

    public void handleKeyPressed(KeyEvent event) {
        KeyCode key = event.getCode();

        if (key == KeyCode.SPACE) {
            if (onPlayAgain != null) {
                stopEndGameLoop();
                gameEngine.resetGame();
                onPlayAgain.run();
            }
        } else if (key == KeyCode.ESCAPE) {
            if (onReturnToMenu != null) {
                stopEndGameLoop();
                gameEngine.resetGame();
                onReturnToMenu.run();
            }
        }
    }
}
