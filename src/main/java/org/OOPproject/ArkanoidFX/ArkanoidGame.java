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
        // Set the title that appears at the top of the window
        primaryStage.setTitle("ArkanoidFX - Brick Breaker Game");

        // Create a Scene with an empty StackPane (container)
        // We're making it 800 pixels wide and 600 pixels tall
        // GameController will add the game view to this scene
        Scene gameScene = new Scene(new StackPane(), 800, 600);
        
        // Get the GameController instance (Singleton pattern)
        // Controller sets up the game and connects Model with View
        GameController gameController = GameController.getInstance(gameScene);
        
        // Add keyboard controls to the scene
        // When you press keys, the scene will detect it and pass to controller
        gameScene.setOnKeyPressed(gameController::handlePressedKeys);
        gameScene.setOnKeyReleased(gameController::handleReleasedKeys);

        // Put the scene into the window (stage)
        primaryStage.setScene(gameScene);
        
        // Don't allow resizing (keeps game size consistent)
        primaryStage.setResizable(false);
        
        // Show the window to the user
        primaryStage.show();

        // Start the game loop (animation)
        // This begins the continuous update-render cycle
        gameController.startGameLoop();
    }
    
    /**
     * This is called when the application is closing.
     * Good place to clean up resources, save game state, etc.
     */
    @Override
    public void stop() {
        System.out.println("Game closing... Goodbye!");
        // Future: Add cleanup code here if needed
        // - Save high scores
        // - Close database connections
        // - Stop background threads
    }
}
