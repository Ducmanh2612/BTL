package org.OOPproject.ArkanoidFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.OOPproject.ArkanoidFX.controller.GameController;

import static org.OOPproject.ArkanoidFX.utils.Constants.WINDOW_HEIGHT;
import static org.OOPproject.ArkanoidFX.utils.Constants.WINDOW_WIDTH;

//TODO: add sound for the game
//TODO: add start menu and end screen
public class ArkanoidGame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ArkanoidFX");
        Scene gameScene = new Scene(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        GameController gameController = GameController.getInstance(gameScene);
        gameScene.setOnKeyPressed(gameController::handlePressedKeys);
        gameScene.setOnKeyReleased(gameController::handleReleasedKeys);
        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        gameController.startGameLoop();
    }

    @Override
    public void stop() {
        System.out.println("Game closing... Goodbye!");
    }
}
