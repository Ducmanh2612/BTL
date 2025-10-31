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
    private Runnable onGameOver;
    private boolean gameOverTriggered = false;

    private GameController(Scene scene) {
        gameEngine = GameEngine.getInstance();
        gameView = GameView.getInstance(gameEngine);
        Pane root = (Pane) scene.getRoot();
        root.getChildren().add(gameView);
        pressedKeys = new HashSet<>();
        gameOverTriggered = false;

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

                // Check for game over
                if (gameEngine.getGameState().equals("GAME_OVER") && !gameOverTriggered) {
                    gameOverTriggered = true;
                    stopGameLoop();
                    if (onGameOver != null) {
                        // Just signal game over, don't pass any data
                        onGameOver.run();
                    }
                }
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
        gameOverTriggered = false;
        lastFrameUpdate = 0;
        gameLoop.start();
    }

    public void stopGameLoop() {
        gameLoop.stop();
    }

    public void setOnGameOver(Runnable onGameOver) {
        this.onGameOver = onGameOver;
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
        //TODO: handle the bug when pressing P, the game freezes and not not unfreeze on next P press
        if (pressedKeys.contains(KeyCode.P)) {
            gameEngine.handleInput(InputSignal.PAUSE_RESUME);
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
