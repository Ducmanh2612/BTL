package org.OOPproject.ArkanoidFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.OOPproject.ArkanoidFX.controller.GameController;


public class ArkanoidGame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ArkanoidFX");

        Scene gameScene = new Scene(new StackPane(), 800, 600);
        GameController gameController = GameController.getInstance(gameScene);
        gameScene.setOnKeyPressed(gameController::handlePressedKeys);
        gameScene.setOnKeyReleased(gameController::handleReleasedKeys);


        primaryStage.setScene(gameScene);
        primaryStage.show();

        gameController.startGameLoop();
    }
}
