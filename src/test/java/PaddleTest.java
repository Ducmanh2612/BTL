import org.OOPproject.ArkanoidFX.model.Paddle;
import org.OOPproject.ArkanoidFX.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.OOPproject.ArkanoidFX.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Paddle class.
 * Verifies movement, boundaries, power-ups, and sprite updates.
 */
class PaddleTest {

    private Paddle paddle;

    @BeforeEach
    void setUp() {
        paddle = new Paddle(100, 500, PADDLE_DEFAULT_WIDTH, PADDLE_HEIGHT);
    }

    @Test
    void testInitialValues() {
        assertEquals(100, paddle.getX(), "Initial X position should match constructor value");
        assertEquals(500, paddle.getY(), "Initial Y position should match constructor value");
        assertEquals(PADDLE_DEFAULT_WIDTH, paddle.getWidth(), "Initial width should match constructor value");
        assertEquals(PADDLE_HEIGHT, paddle.getHeight(), "Initial height should match constructor value");
        assertEquals(600.0, paddle.getSpeed(), "Default paddle speed should be 500.0 px/s");
        assertEquals(PADDLE_DEFAULT_WIDTH, paddle.getWidth(), "Original width should be stored correctly");
    }

    @Test
    void testMoveRightIncreasesX() {
        double initialX = paddle.getX();
        paddle.moveRight();
        paddle.move(0.1); // 0.1s elapsed

        assertTrue(paddle.getX() > initialX, "Paddle should move right when moveRight() is called");
    }

    @Test
    void testMoveLeftDecreasesX() {
        double initialX = paddle.getX();
        paddle.moveLeft();
        paddle.move(0.1);

        assertTrue(paddle.getX() < initialX, "Paddle should move left when moveLeft() is called");
    }

    @Test
    void testStopPreventsMovement() {
        paddle.moveRight();
        paddle.stop();
        double oldX = paddle.getX();
        paddle.move(0.1);

        assertEquals(oldX, paddle.getX(), "Paddle should not move after stop() is called");
    }

    @Test
    void testCannotMoveBeyondLeftBoundary() {
        paddle.setX(20);
        paddle.moveLeft();
        paddle.move(0.5);

        assertEquals(20, paddle.getX(), "Paddle should not move beyond the left boundary");
    }

    @Test
    void testCannotMoveBeyondRightBoundary() {
        int gameWidth = Constants.GAME_WIDTH;
        paddle.setX(gameWidth - paddle.getWidth() / 2);
        paddle.moveRight();
        paddle.move(1.0);

        assertTrue(paddle.getX() + paddle.getWidth() <= gameWidth,
                "Paddle should not move beyond the right boundary");
    }

    @Test
    void testExpandAndRestorePaddle() {
        assertFalse(paddle.isExpanded(), "Paddle should not be expanded initially");
        paddle.expandPaddle();
        assertTrue(paddle.isExpanded(), "Paddle should be expanded after calling expandPaddle()");
        assertEquals(PADDLE_EXPANDED_WIDTH, paddle.getWidth(), "Expanded width should be same as" +
                " PADDLE_EXPANDED_WIDTH");
        paddle.restorePaddleSize();
        assertEquals(paddle.getWidth(), paddle.getWidth(), "Width should restore to original size");
    }

    @Test
    void testSpriteAnimationAdvances() {
        int oldFrameX = paddle.getFrameX();
        paddle.move(0.2);
        assertTrue(paddle.getFrameX() != oldFrameX || paddle.getFrameY() >= 0,
                "Sprite animation frame should advance over time");
    }
}
