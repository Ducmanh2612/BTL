package org.OOPproject.ArkanoidFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.OOPproject.ArkanoidFX.controller.GameController;
import org.OOPproject.ArkanoidFX.controller.MenuController;

import static org.OOPproject.ArkanoidFX.utils.Constants.WINDOW_HEIGHT;
import static org.OOPproject.ArkanoidFX.utils.Constants.WINDOW_WIDTH;

//TODO: add sound for the game
//TODO: add start menu and end screen
public class ArkanoidGame extends Application {
    private Stage primaryStage;
    private Scene menuScene;
    private Scene gameScene;
    private MenuController menuController;
    private GameController gameController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("ArkanoidFX");
        primaryStage.setResizable(false);

        // Create Menu Scene
        createMenuScene();

        // Show menu first
        primaryStage.setScene(menuScene);
        primaryStage.show();

        // Start menu animation
        menuController.startMenuLoop();
    }

    private void createMenuScene() {
        menuScene = new Scene(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        menuController = MenuController.getInstance(menuScene);

        // Set up key handler for menu
        menuScene.setOnKeyPressed(menuController::handleKeyPressed);

        // Set callback to switch to game when SPACE is pressed
        menuController.setOnStartGame(this::switchToGame);
    }

    private void createGameScene() {
        gameScene = new Scene(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        gameController = GameController.getInstance(gameScene);

        // Set up key handlers for game
        gameScene.setOnKeyPressed(gameController::handlePressedKeys);
        gameScene.setOnKeyReleased(gameController::handleReleasedKeys);
    }

    private void switchToGame() {
        // Create game scene if it doesn't exist
        if (gameScene == null) {
            createGameScene();
        }

        // Switch to game scene
        primaryStage.setScene(gameScene);

        // Start game loop
        gameController.startGameLoop();
    }

    @Override
    public void stop() {
        System.out.println("Game closing... Goodbye!");
    }
}
