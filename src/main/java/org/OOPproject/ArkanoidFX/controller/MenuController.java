package org.OOPproject.ArkanoidFX.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.OOPproject.ArkanoidFX.view.MenuView;

public class MenuController {
    private static MenuController instance;
    private final AnimationTimer menuLoop;
    private MenuView menuView;
    private long lastFrameUpdate = 0;
    private Runnable onStartGame;

    private MenuController(Scene scene) {
        menuView = MenuView.getInstance();
        Pane root = (Pane) scene.getRoot();
        root.getChildren().add(menuView);

        menuLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastFrameUpdate == 0) {
                    lastFrameUpdate = now;
                    return;
                }
                double deltaTime = (now - lastFrameUpdate) / 1_000_000_000.0;
                lastFrameUpdate = now;

                menuView.render(deltaTime);
            }
        };
    }

    public static MenuController getInstance(Scene scene) {
        if (instance == null) {
            instance = new MenuController(scene);
        }
        return instance;
    }

    public void startMenuLoop() {
        menuLoop.start();
    }

    public void stopMenuLoop() {
        menuLoop.stop();
    }

    public void setOnStartGame(Runnable onStartGame) {
        this.onStartGame = onStartGame;
    }

    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            if (onStartGame != null) {
                stopMenuLoop();
                onStartGame.run();
            }
        }
    }
}
