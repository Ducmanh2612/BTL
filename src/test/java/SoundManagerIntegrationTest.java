import javafx.application.Platform;
import org.OOPproject.ArkanoidFX.model.SoundManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test cho SoundManager với AssetManager thật.
 * test này sẽ tải và phát các file âm thanh thật trong /assets/sfx/.
 * Đảm bảo các file WAV tồn tại ở đúng đường dẫn resources.
 */
public class SoundManagerIntegrationTest {

    private static SoundManager soundManager;

    @BeforeAll
    static void init() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException ignored) {
        }

        soundManager = SoundManager.getInstance();
        assertNotNull(soundManager, "SoundManager instance should not be null");
    }

    @Test
    void testPlaySound_ExistingFile() {
        assertDoesNotThrow(() -> soundManager.playSound("ball_block.wav"),
                "Should play existing sound without throwing an exception");
    }

    @Test
    void testPlaySound_NonExistingFile() {
        assertDoesNotThrow(() -> soundManager.playSound("this_file_does_not_exist.wav"),
                "Should not throw exception even if sound file is missing");
    }

    @Test
    void testGetInstance_IsSingleton() {
        SoundManager instance1 = SoundManager.getInstance();
        SoundManager instance2 = SoundManager.getInstance();
        assertSame(instance1, instance2, "SoundManager must be a singleton");
    }
}
