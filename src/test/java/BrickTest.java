import org.OOPproject.ArkanoidFX.model.Bricks.Brick;
import org.OOPproject.ArkanoidFX.model.Bricks.BrickType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test cho lớp Brick (abstract) và enum BrickType.
 * Sử dụng lớp giả lập để kiểm thử hành vi chung.
 */
class BrickTest {

    private static class FakeBrick extends Brick {
        public FakeBrick(int x, int y, int width, int height, BrickType type, int hitPoints, int scoreValue) {
            super(x, y, width, height, type);
            this.hitPoints = hitPoints;
            this.scoreValue = scoreValue;
        }
    }

    private FakeBrick brick;

    @BeforeEach
    void setUp() {
        brick = new FakeBrick(100, 200, 50, 20, BrickType.BLUE, 3, 150);
    }

    @Test
    void testInitialValues() {
        assertEquals(100, brick.getX());
        assertEquals(200, brick.getY());
        assertEquals(50, brick.getWidth());
        assertEquals(20, brick.getHeight());
        assertEquals(3, brick.getHitPoints());
        assertEquals(BrickType.BLUE, brick.getType());
        assertEquals(150, brick.getScoreValue());
        assertFalse(brick.isDestroyed());
    }

    @Test
    void testTakeHitReducesHitPoints() {
        brick.takeHit();
        assertEquals(2, brick.getHitPoints());
        assertFalse(brick.isDestroyed());

        brick.takeHit();
        brick.takeHit();
        assertEquals(0, brick.getHitPoints());
        assertTrue(brick.isDestroyed());
    }

    @Test
    void testIsDestroyedWhenHitPointsZeroOrLess() {
        brick.takeHit();
        brick.takeHit();
        brick.takeHit();
        assertTrue(brick.isDestroyed(), "Brick should be destroyed when hitPoints <= 0");

        // Giảm thêm lần nữa vẫn phải true
        brick.takeHit();
        assertTrue(brick.isDestroyed());
    }

    @Test
    void testDifferentBrickTypes() {
        FakeBrick gold = new FakeBrick(0, 0, 50, 20, BrickType.GOLD, 5, BrickType.GOLD.ScoreValue);
        FakeBrick ruby = new FakeBrick(0, 0, 50, 20, BrickType.RUBY, 1, BrickType.RUBY.ScoreValue);

        assertEquals(BrickType.GOLD, gold.getType());
        assertEquals(BrickType.RUBY, ruby.getType());
        assertEquals(0, gold.getScoreValue());
        assertEquals(90, ruby.getScoreValue());
    }

    @Test
    void testBrickTypeValues() {
        // Đảm bảo mỗi BrickType có giá trị hợp lệ theo thiết kế
        assertAll(
                () -> assertEquals(0, BrickType.NONE.maxHits),
                () -> assertEquals(-1, BrickType.GOLD.maxHits),
                () -> assertEquals(2, BrickType.GRAY.maxHits),
                () -> assertEquals(1, BrickType.RUBY.maxHits),
                () -> assertEquals(1, BrickType.YLLW.maxHits),
                () -> assertEquals(1, BrickType.BLUE.maxHits),
                () -> assertEquals(1, BrickType.MGNT.maxHits),
                () -> assertEquals(1, BrickType.LIME.maxHits),
                () -> assertEquals(1, BrickType.WHIT.maxHits),
                () -> assertEquals(1, BrickType.ORNG.maxHits),
                () -> assertEquals(1, BrickType.CYAN.maxHits)
        );

        assertAll(
                () -> assertEquals(0, BrickType.NONE.ScoreValue),
                () -> assertEquals(0, BrickType.GOLD.ScoreValue),
                () -> assertEquals(0, BrickType.GRAY.ScoreValue),
                () -> assertEquals(90, BrickType.RUBY.ScoreValue),
                () -> assertEquals(120, BrickType.YLLW.ScoreValue),
                () -> assertEquals(100, BrickType.BLUE.ScoreValue),
                () -> assertEquals(110, BrickType.MGNT.ScoreValue),
                () -> assertEquals(80, BrickType.LIME.ScoreValue),
                () -> assertEquals(50, BrickType.WHIT.ScoreValue),
                () -> assertEquals(60, BrickType.ORNG.ScoreValue),
                () -> assertEquals(70, BrickType.CYAN.ScoreValue)
        );
    }
}
