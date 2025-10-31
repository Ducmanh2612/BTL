package org.OOPproject.ArkanoidFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.OOPproject.ArkanoidFX.controller.EndGameController;
import org.OOPproject.ArkanoidFX.controller.GameController;
import org.OOPproject.ArkanoidFX.controller.MenuController;
import org.OOPproject.ArkanoidFX.model.SoundManager;

import static org.OOPproject.ArkanoidFX.utils.Constants.WINDOW_HEIGHT;
import static org.OOPproject.ArkanoidFX.utils.Constants.WINDOW_WIDTH;

//TODO: add sound for the game
public class ArkanoidGame extends Application {
    private Stage primaryStage;
    private Scene menuScene;
    private Scene gameScene;
    private Scene endGameScene;

    private MenuController menuController;
    private GameController gameController;
    private EndGameController endGameController;

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

        SoundManager.getInstance().playSound("game_start.wav");

        // Start menu animation
        menuController.startMenuLoop();
    }

    private void createMenuScene() {
        menuScene = new Scene(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        menuController = MenuController.getInstance(menuScene);

        // Set up key handler for menu
        menuScene.setOnKeyPressed(menuController::handleKeyPressed);

        // Set callback
        menuController.setOnStartGame(this::switchToGame);
    }

    private void createGameScene() {
        gameScene = new Scene(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        gameController = GameController.getInstance(gameScene);

        // Set up key handlers for game
        gameScene.setOnKeyPressed(gameController::handlePressedKeys);
        gameScene.setOnKeyReleased(gameController::handleReleasedKeys);

        // Set callback for when game ends
        gameController.setOnGameOver(this::switchToEndGame);
    }

    private void createEndGameScene() {
        endGameScene = new Scene(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        endGameController = EndGameController.getInstance(endGameScene);

        // Set up key handler for end game
        endGameScene.setOnKeyPressed(endGameController::handleKeyPressed);

        // Set callbacks
        endGameController.setOnPlayAgain(this::restartGame);
        endGameController.setOnReturnToMenu(this::returnToMenu);
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

    private void switchToEndGame() {
        // Create end game scene if it doesn't exist
        if (endGameScene == null) {
            createEndGameScene();
        }

        // Switch to end game scene
        primaryStage.setScene(endGameScene);

        // Start end game animation
        endGameController.startEndGameLoop();
    }

    private void restartGame() {
        // Recreate game scene for fresh start
        gameScene = null;
        gameController = null;
        createGameScene();

        // Switch to game
        primaryStage.setScene(gameScene);
        gameController.startGameLoop();
    }

    private void returnToMenu() {
        // Switch to menu
        primaryStage.setScene(menuScene);
        menuController.startMenuLoop();
    }

    @Override
    public void stop() {
        System.out.println("Game closing... Goodbye!");
    }
}
