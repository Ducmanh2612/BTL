import org.OOPproject.ArkanoidFX.model.GameEngine;

import org.OOPproject.ArkanoidFX.utils.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private GameEngine engine;

    @BeforeEach
    void setup() {
        engine = GameEngine.getInstance();
        engine.resetGame(); // đảm bảo trạng thái sạch trước mỗi test
    }

    @Test
    void testSingleton() {
        GameEngine e1 = GameEngine.getInstance();
        GameEngine e2 = GameEngine.getInstance();
        assertSame(e1, e2, "GameEngine phải là singleton");
    }

    @Test
    void testStartGameInitializesObjects() {
        engine.startGame();

        assertEquals(GameState.PLAYING, engine.getGameState());
        assertNotNull(engine.getPaddle());
        assertFalse(engine.getBalls().isEmpty());
        assertEquals(3, engine.getLives());
        assertEquals(0, engine.getScore());
        assertTrue(engine.getLevelNumber() >= 1);
    }

    @Test
    void testResetGameResetsAllState() {
        engine.startGame();
        engine.addExtraLife();
        engine.setGameState(GameState.PAUSED);
        engine.resetGame();

        assertEquals(3, engine.getLives());
        assertEquals(0, engine.getScore());
        assertEquals(GameState.PLAYING, engine.getGameState());
        assertFalse(engine.getBalls().isEmpty());
    }

    @Test
    void testAddExtraLifeIncreasesLives() {
        int before = engine.getLives();
        engine.addExtraLife();
        assertEquals(before + 1, engine.getLives());
    }

    @Test
    void testLoseLifeAndGameOverLogic() throws Exception {
        // lives = 3, giảm 3 lần sẽ game over
        Method loseLife = GameEngine.class.getDeclaredMethod("loseLife");
        loseLife.setAccessible(true);

        // Lần 1
        loseLife.invoke(engine);
        assertEquals(2, engine.getLives());
        assertEquals(GameState.PLAYING, engine.getGameState());

        // Lần 2
        loseLife.invoke(engine);
        assertEquals(1, engine.getLives());

        // Lần 3 → gameOver
        loseLife.invoke(engine);
        assertEquals(GameState.GAME_OVER, engine.getGameState());
    }

    @Test
    void testIsLevelComplete_AllUnbreakable_ReturnsTrue() throws Exception {
        var bricks = engine.getBricks();
        bricks.clear();
        bricks.add(new org.OOPproject.ArkanoidFX.model.Bricks.UnbreakableBrick(0,0,10,10));

        Method isLevelComplete = GameEngine.class.getDeclaredMethod("isLevelComplete");
        isLevelComplete.setAccessible(true);
        boolean complete = (boolean) isLevelComplete.invoke(engine);

        assertTrue(complete, "Level hoàn thành khi chỉ còn gạch không phá được");
    }

    @Test
    void testIsLevelComplete_WithBreakable_ReturnsFalse() throws Exception {
        var bricks = engine.getBricks();
        bricks.clear();
        bricks.add(new org.OOPproject.ArkanoidFX.model.Bricks.NormalBrick(0,0,10,10));

        Method isLevelComplete = GameEngine.class.getDeclaredMethod("isLevelComplete");
        isLevelComplete.setAccessible(true);
        boolean complete = (boolean) isLevelComplete.invoke(engine);

        assertFalse(complete, "Level chưa hoàn thành khi còn gạch phá được");
    }

    @Test
    void testUpdateEnemiesSpawningLogic() throws Exception {
        // Truy cập phương thức private
        Method updateEnemies = GameEngine.class.getDeclaredMethod("updateEnemies", double.class);
        updateEnemies.setAccessible(true);

        assertFalse(engine.isSpawningEnemies());
        updateEnemies.invoke(engine, 0.1);
        assertTrue(engine.isSpawningEnemies(), "Sau khi updateEnemies, trạng thái spawningEnemies phải bật");
    }

    @Test
    void testGameOverSetsState() {
        engine.gameOver();
        assertEquals(GameState.GAME_OVER, engine.getGameState());
    }

    @Test
    void testLevelCompleteIncreasesLevel() throws Exception {
        int before = engine.getLevelNumber();
        Method levelComplete = GameEngine.class.getDeclaredMethod("levelComplete");
        levelComplete.setAccessible(true);
        levelComplete.invoke(engine);

        assertTrue(engine.getLevelNumber() > before, "Level phải tăng sau khi levelComplete()");
    }
}
