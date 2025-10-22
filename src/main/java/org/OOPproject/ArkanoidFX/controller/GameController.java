package org.OOPproject.ArkanoidFX.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import org.OOPproject.ArkanoidFX.model.GameEngine;
import org.OOPproject.ArkanoidFX.view.GameView;

public class GameController {
    private AnimationTimer gameLoop;
    private static GameEngine gameEngine;
    private static GameView gameView;
    private static GameController instance;

    private long lastFrameUpdate = 0;

    private GameController (Scene scene) {
        // Initialize game state and start game loop
        gameEngine = GameEngine.getInstance();
        gameView = GameView.getInstance();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastFrameUpdate == 0) {
                    lastFrameUpdate = now;
                    return;
                }

                double deltaTime = (now - lastFrameUpdate) / 1_000_000_000.0; // Convert nanoseconds to seconds
                lastFrameUpdate = now;
                gameEngine.updateGame(deltaTime);
                //gameView.render();
            }
        };
    }

    public static GameController getInstance(Scene scene) {
        if (instance == null) {
            instance = new GameController(scene);
        }
        return instance;
    }

    public void startGameLoop() {
        gameLoop.start();
    }

    public void handlePressedKeys(KeyEvent event) {

    }

    public void handleReleasedKeys(KeyEvent event) {
    }

}
