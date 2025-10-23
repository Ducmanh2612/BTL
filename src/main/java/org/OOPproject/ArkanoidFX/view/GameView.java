package org.OOPproject.ArkanoidFX.view;

import javafx.scene.layout.StackPane;

public class GameView extends StackPane {
    private static GameView instance;
    private GameView() {
        // Initialize game view components here
    }
    public static GameView getInstance() {
        if (instance == null) {
            instance = new GameView();
        }
        return instance;
    }




}