package org.OOPproject.ArkanoidFX.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.OOPproject.ArkanoidFX.model.GameEngine;
import org.OOPproject.ArkanoidFX.utils.InputSignal;
import org.OOPproject.ArkanoidFX.view.GameView;

import java.util.HashSet;
import java.util.Set;

public class GameController {
    private final AnimationTimer gameLoop;
    private static GameEngine gameEngine;
    private static GameView gameView;
    private static GameController instance;
    private long lastFrameUpdate = 0;
    private Set<KeyCode> pressedKeys;
    private boolean gameIsPaused;

    private GameController(Scene scene) {
        gameEngine = GameEngine.getInstance();
        gameView = GameView.getInstance(gameEngine);
        Pane root = (Pane) scene.getRoot();
        root.getChildren().add(gameView);
        pressedKeys = new HashSet<>();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastFrameUpdate == 0) {
                    lastFrameUpdate = now;
                    return;
                }
                double deltaTime = (now - lastFrameUpdate) / 1_000_000_000.0;
                lastFrameUpdate = now;
                handleCurrentKeys();
                gameEngine.updateGame(deltaTime);
                gameView.render();
            }
        };
        gameIsPaused = false;
    }

    public static GameController getInstance(Scene scene) {
        if (instance == null) {
            instance = new GameController(scene);
        }
        return instance;
    }

    public void startGameLoop() {
        gameEngine.startGame();
        gameLoop.start();
    }

    public void handleCurrentKeys() {
        if (pressedKeys.contains(KeyCode.A)) {
            gameEngine.handleInput(InputSignal.MOVE_LEFT);
        }
        if (pressedKeys.contains(KeyCode.D)) {
            gameEngine.handleInput(InputSignal.MOVE_RIGHT);
        }
        if(!pressedKeys.contains(KeyCode.A) && !pressedKeys.contains(KeyCode.D)) {
            gameEngine.handleInput(InputSignal.STOP);
        }
        if (pressedKeys.contains(KeyCode.P)) {
            gameEngine.handleInput(InputSignal.PAUSE_RESUME);
            // Remove P key to prevent multiple toggles in one press
            pressedKeys.remove(KeyCode.P);
        }
    }

    public void handlePressedKeys(KeyEvent event) {
        KeyCode key = event.getCode();
        switch (key) {
            case P -> {
                if(!gameIsPaused){
                    pressedKeys.add(key);
                    gameIsPaused = true;
                }
            }
            case A, D -> pressedKeys.add(key);
        }
    }

    public void handleReleasedKeys(KeyEvent event) {
        pressedKeys.remove(event.getCode());
        if(event.getCode().equals(KeyCode.P)){
            gameIsPaused = false;
        }
    }
}
