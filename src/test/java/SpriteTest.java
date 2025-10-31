import org.OOPproject.ArkanoidFX.model.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests cho lớp Sprite.
 * Mục tiêu: kiểm tra hoạt động của hệ thống hoạt ảnh (update, loop, reset, finished).
 */
class SpriteTest {

    private Sprite sprite;
    private static final double FRAME_TIME = 0.015; // seconds per frame

    @BeforeEach
    void setUp() {
        sprite = new Sprite(4, 2, FRAME_TIME, false); // 4 frames ngang, 2 dọc
    }

    @Test
    void testInitialState() {
        assertEquals(0, sprite.getFrameX());
        assertEquals(0, sprite.getFrameY());
        assertFalse(sprite.isFinished());
        assertFalse(sprite.isLoop());
    }

    @Test
    void testAdvanceFramesWithoutLoop() {
        // 4x2 => 8 frames tổng cộng, mỗi frame = 0.015s
        // Sau 8 lần update, animation phải kết thúc
        for (int i = 0; i < 8; i++) {
            sprite.update(FRAME_TIME);
        }

        assertTrue(sprite.isFinished(), "Sprite phải kết thúc sau khi đi hết frame");
        assertEquals(3, sprite.getFrameX(), "Frame X phải dừng ở cuối");
        assertEquals(1, sprite.getFrameY(), "Frame Y phải dừng ở dòng cuối");
    }

    @Test
    void testResetRestoresInitialState() {
        sprite.update(FRAME_TIME * 10);
        sprite.reset();

        assertEquals(0, sprite.getFrameX());
        assertEquals(0, sprite.getFrameY());
        assertFalse(sprite.isFinished());
    }

    @Test
    void testLoopingAnimationResetsAfterEnd() {
        Sprite loopingSprite = new Sprite(3, 2, FRAME_TIME, true);

        int totalFrames = 3 * 2; // 6 frames
        for (int i = 0; i < totalFrames + 2; i++) {
            loopingSprite.update(FRAME_TIME);
        }

        assertFalse(loopingSprite.isFinished(), "Looping animation không bao giờ kết thúc");
        assertTrue(loopingSprite.getFrameY() < loopingSprite.getMaxFrameY(),
                "Frame Y phải quay lại dòng đầu khi loop");
    }

    @Test
    void testUpdateStopsWhenFinishedAndNotLooping() {
        // Chạy đến khi hoàn thành
        for (int i = 0; i < 8; i++) {
            sprite.update(FRAME_TIME);
        }

        int frameX = sprite.getFrameX();
        int frameY = sprite.getFrameY();

        sprite.update(FRAME_TIME * 10); // thêm thời gian, không được thay đổi gì

        assertEquals(frameX, sprite.getFrameX());
        assertEquals(frameY, sprite.getFrameY());
        assertTrue(sprite.isFinished());
    }

    @Test
    void testPartialUpdatesDoNotAdvanceFrame() {
        sprite.update(FRAME_TIME / 2); // chưa đủ thời gian để đổi frame
        assertEquals(0, sprite.getFrameX());
        assertEquals(0, sprite.getFrameY());
        assertFalse(sprite.isFinished());
    }
}
