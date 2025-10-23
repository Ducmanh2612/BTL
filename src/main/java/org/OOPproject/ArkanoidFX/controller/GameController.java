package org.OOPproject.ArkanoidFX.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.OOPproject.ArkanoidFX.model.GameEngine;
import org.OOPproject.ArkanoidFX.utils.InputSignal;
import org.OOPproject.ArkanoidFX.view.GameView;

public class GameController {
    private final AnimationTimer gameLoop;
    private static GameEngine gameEngine;
    private static GameView gameView;
    private static GameController instance;
    private long lastFrameUpdate = 0;

    private GameController(Scene scene) {
        gameEngine = GameEngine.getInstance();
        gameView = GameView.getInstance(gameEngine);
        Pane root = (Pane) scene.getRoot();
        root.getChildren().add(gameView);
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastFrameUpdate == 0) {
                    lastFrameUpdate = now;
                    return;
                }
                double deltaTime = (now - lastFrameUpdate) / 1_000_000_000.0;
                lastFrameUpdate = now;
                gameEngine.updateGame(deltaTime);
                gameView.render();
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
        switch (event.getCode()) {
            case A -> gameEngine.handleInput(InputSignal.MOVE_LEFT);
            case D -> gameEngine.handleInput(InputSignal.MOVE_RIGHT);
            case P -> gameEngine.handleInput(InputSignal.PAUSE_RESUME);
        }
    }

    public void handleReleasedKeys(KeyEvent event) {
        switch (event.getCode()) {
            case A -> gameEngine.handleInput(InputSignal.STOP_LEFT);
            case D -> gameEngine.handleInput(InputSignal.STOP_RIGHT);
        }
    }
}
