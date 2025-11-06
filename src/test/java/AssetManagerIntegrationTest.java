import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import org.OOPproject.ArkanoidFX.model.Bricks.BrickType;
import org.OOPproject.ArkanoidFX.model.PowerUps.PowerUpTypes;
import org.OOPproject.ArkanoidFX.view.AssetManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test cho AssetManager.
 * Test này xác minh việc load hình ảnh & âm thanh thật từ thư mục assets.
 *
 * Yêu cầu:
 *  - Thư mục /assets/textures/ và /assets/sfx/ tồn tại trong resources.
 *  - JavaFX media modules (javafx.controls, javafx.media) được bật.
 */
public class AssetManagerIntegrationTest {

    private static AssetManager assetManager;

    @BeforeAll
    static void setup() {
        // JavaFX cần khởi động trước khi sử dụng Image hoặc AudioClip
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException ignored) {
            // Đã khởi động rồi
        }

        assetManager = AssetManager.getInstance();
        assertNotNull(assetManager, "AssetManager instance must not be null");
    }

    // 🧩 Test singleton
    @Test
    void testSingleton() {
        AssetManager a1 = AssetManager.getInstance();
        AssetManager a2 = AssetManager.getInstance();
        assertSame(a1, a2, "AssetManager should behave as a singleton");
    }

    // 🧩 Test background patterns
    @Test
    void testBackgroundPatterns() {
        Image pattern1 = assetManager.getBackgroundPattern(1);
        Image pattern5 = assetManager.getBackgroundPattern(5); // test modulo logic

        assertNotNull(pattern1, "Background pattern 1 should not be null");
        assertNotNull(pattern5, "Background pattern 5 should not be null");
        assertSame(pattern1.getClass(), Image.class, "Should return valid Image objects");
    }

    // 🧩 Test brick images
    @Test
    void testBrickImagesLoaded() {
        for (BrickType type : BrickType.values()) {
            Image img = assetManager.getBrickImage(type);
            if (type == BrickType.NONE) {
                // NONE có thể không có hình — hợp lệ
                assertNull(img, "BrickType.NONE should return null image");
            } else {
                assertNotNull(img, "Brick image for type " + type + " should not be null");
            }
        }
    }

    // 🧩 Test power-up images
    @Test
    void testPowerUpSpriteMaps() {
        for (PowerUpTypes type : PowerUpTypes.values()) {
            Image img = assetManager.getPowerUpSpriteMap(type);
            assertNotNull(img, "PowerUp sprite map for " + type + " should not be null");
        }
    }

    // 🧩 Test AudioClip load
    @Test
    void testAudioClipsExist() {
        String[] files = {
                "ball_block.wav", "ball_hard_block.wav", "ball_paddle.wav",
                "bounce.wav", "click.wav", "explosion.wav",
                "game_over.wav", "game_start.wav", "gun.wav",
                "laserShoot.wav", "level_ready.wav", "powerUp.wav"
        };

        for (String file : files) {
            AudioClip clip = assetManager.getAudioClip(file);
            assertNotNull(clip, "AudioClip should not be null for " + file);
        }
    }

    // 🧩 Test missing audio file handled safely
    @Test
    void testMissingAudioClipHandledGracefully() {
        AudioClip clip = assetManager.getAudioClip("not_exists.wav");
        assertNull(clip, "Should return null when audio file not found");
    }

    // 🧩 Test direct image getters
    @Test
    void testDirectImageGetters() {
        assertNotNull(assetManager.getBallImg(), "Ball image should not be null");
        assertNotNull(assetManager.getHeartImg(), "Heart image should not be null");
        assertNotNull(assetManager.getEnemyMapImg(), "Enemy map image should not be null");
        assertNotNull(assetManager.getExplosionMapImg(), "Explosion map should not be null");
        assertNotNull(assetManager.getBulletImg(), "Bullet image should not be null");
    }
}
