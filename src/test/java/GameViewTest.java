import javafx.embed.swing.JFXPanel;
import javafx.scene.canvas.GraphicsContext;
import org.OOPproject.ArkanoidFX.model.Ball;
import org.OOPproject.ArkanoidFX.model.Bricks.ColoredBrick;
import org.OOPproject.ArkanoidFX.model.GameEngine;
import org.OOPproject.ArkanoidFX.model.Paddle;
import org.OOPproject.ArkanoidFX.model.ParticleSystem;
import org.OOPproject.ArkanoidFX.model.PowerUps.ExpandPaddlePowerUp;
import org.OOPproject.ArkanoidFX.utils.GameState;
import org.OOPproject.ArkanoidFX.view.GameView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for GameView rendering logic.
 * These tests focus on verifying safe rendering paths and
 * correct handling of game states and null assets.
 */
class GameViewTest {

    private GameEngine mockEngine;
    private GameView gameView;
    private GraphicsContext mockGC;

    @BeforeAll
    static void initJavaFX() {
        // Initializes JavaFX toolkit (required for tests using Canvas/Image)
        new JFXPanel();
    }

    @BeforeEach
    void setup() {
        mockEngine = Mockito.mock(GameEngine.class);
        gameView = GameView.getInstance(mockEngine);

        // Access private GraphicsContext via reflection for mocking
        try {
            var gcField = GameView.class.getDeclaredField("gc");
            gcField.setAccessible(true);
            mockGC = Mockito.mock(GraphicsContext.class);
            gcField.set(gameView, mockGC);
        } catch (Exception e) {
            fail("Failed to inject mock GraphicsContext: " + e.getMessage());
        }
    }

    @Test
    void testRenderGameOverStateDoesNotThrow() {
        when(mockEngine.getGameState()).thenReturn(GameState.GAME_OVER);
        when(mockEngine.getScore()).thenReturn(1234);
        when(mockEngine.getLevelNumber()).thenReturn(3);

        assertDoesNotThrow(() -> gameView.render(),
                "Rendering game over state should not throw exceptions");
    }

    @Test
    void testRenderPausedStateDoesNotThrow() {
        when(mockEngine.getGameState()).thenReturn(GameState.PAUSED);
        when(mockEngine.getLevelNumber()).thenReturn(1);
        when(mockEngine.getBricks()).thenReturn(Collections.emptyList());
        when(mockEngine.getBlinks()).thenReturn(Collections.emptyList());
        when(mockEngine.getPowerUps()).thenReturn(Collections.emptyList());
        when(mockEngine.getPaddle()).thenReturn(new Paddle(0, 0, 80, 16));
        when(mockEngine.getBall()).thenReturn(new Ball(0, 0, 10, 10));
        when(mockEngine.getParticleSystem()).thenReturn(new ParticleSystem());

        assertDoesNotThrow(() -> gameView.render(),
                "Rendering paused state should not throw exceptions");
    }

    @Test
    void testRenderActiveGameDoesNotThrow() {
        when(mockEngine.getGameState()).thenReturn(GameState.PLAYING);
        when(mockEngine.getLevelNumber()).thenReturn(1);
        when(mockEngine.getBricks()).thenReturn(Collections.singletonList(
                new ColoredBrick(10, 10, 38, 20, org.OOPproject.ArkanoidFX.model.Bricks.BrickType.RUBY)
        ));
        when(mockEngine.getBlinks()).thenReturn(Collections.emptyList());
        when(mockEngine.getPowerUps()).thenReturn(Collections.singletonList(
                new ExpandPaddlePowerUp(50, 50)
        ));
        when(mockEngine.getPaddle()).thenReturn(new Paddle(100, 500, 80, 16));
        when(mockEngine.getBall()).thenReturn(new Ball(100, 480, 10, 10));
        when(mockEngine.getParticleSystem()).thenReturn(new ParticleSystem());

        assertDoesNotThrow(() -> gameView.render(),
                "Rendering active game should not throw exceptions");
    }

    @Test
    void testDrawLevelBackgroundHandlesMissingImageGracefully() {
        when(mockEngine.getGameState()).thenReturn(GameState.PLAYING);
        when(mockEngine.getLevelNumber()).thenReturn(1);

        AssetManager assets = AssetManager.getInstance();
        // Force background pattern to null to simulate missing image
        try {
            var bgField = AssetManager.class.getDeclaredField("backgroundPatterns");
            bgField.setAccessible(true);
            bgField.set(assets, null);
        } catch (Exception e) {
            fail("Failed to manipulate AssetManager for test: " + e.getMessage());
        }

        assertDoesNotThrow(() -> gameView.render(),
                "Should safely skip background drawing when pattern is missing");
    }

    @Test
    void testSingletonConsistency() {
        GameView anotherView = GameView.getInstance(mockEngine);
        assertSame(gameView, anotherView, "GameView should follow singleton pattern");
    }
}
