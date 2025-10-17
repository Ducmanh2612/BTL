package org.example.arkanoidFX.gamemanager;

import org.example.arkanoidFX.renderer.Renderer;

/**
 * Manages game states and transitions between different screens (menu, playing, paused, game over).
 * Demonstrates OOP principle: Single Responsibility Principle (focused on state management)
 */
public class GameStateManager {
    public enum GameState {
        MENU,
        PLAYING,
        PAUSED,
        GAME_OVER
    }

    private GameState currentState;
    private final Renderer renderer;
    private final GameManager gameManager;

    public GameStateManager(Renderer renderer, GameManager gameManager) {
        this.renderer = renderer;
        this.gameManager = gameManager;
        this.currentState = GameState.MENU;
    }

    public void showMenu() {
        currentState = GameState.MENU;
        renderer.showFXML("/org/example/arkanoidFX/menu-view.fxml", gameManager);
    }

    public void startPlaying() {
        currentState = GameState.PLAYING;
        renderer.switchToGameScene();
    }

    public void pause() {
        if (currentState == GameState.PLAYING) {
            currentState = GameState.PAUSED;
        }
    }

    public void resume() {
        if (currentState == GameState.PAUSED) {
            currentState = GameState.PLAYING;
        }
    }

    public void togglePause() {
        if (currentState == GameState.PLAYING) {
            pause();
        } else if (currentState == GameState.PAUSED) {
            resume();
        }
    }

    public void gameOver() {
        currentState = GameState.GAME_OVER;
        renderer.showFXML("/org/example/arkanoidFX/gameOver-view.fxml", gameManager);
    }

    public boolean isPlaying() {
        return currentState == GameState.PLAYING;
    }

    public boolean isPaused() {
        return currentState == GameState.PAUSED;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAME_OVER;
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
