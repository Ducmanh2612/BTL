import javafx.scene.image.Image;
import javafx.scene.media.Media;
import org.OOPproject.ArkanoidFX.model.Bricks.BrickType;
import org.OOPproject.ArkanoidFX.model.PowerUps.PowerUpTypes;
import org.OOPproject.ArkanoidFX.view.AssetManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AssetManager class.
 * These tests focus on verifying caching, singleton behavior,
 * and logical methods (not actual resource loading).
 */
class AssetManagerTest {

    @Test
    void testSingletonInstanceIsConsistent() {
        AssetManager instance1 = AssetManager.getInstance();
        AssetManager instance2 = AssetManager.getInstance();

        assertSame(instance1, instance2, "getInstance() should always return the same object");
    }

    @Test
    void testGetBackgroundPatternReturnsConsistentPattern() {
        AssetManager assets = AssetManager.getInstance();

        Image bg1 = assets.getBackgroundPattern(1);
        Image bg5 = assets.getBackgroundPattern(5); // wraps around (since 4 patterns)

        assertEquals(bg1, bg5,
                "Background pattern selection should wrap around correctly for levels beyond array length");
    }

    @Test
    void testGetBrickImageHandlesValidTypes() {
        AssetManager assets = AssetManager.getInstance();

        for (BrickType type : BrickType.values()) {
            Image img = assets.getBrickImage(type);
            // Even if file not found, method should not throw
            assertDoesNotThrow(() -> assets.getBrickImage(type),
                    "getBrickImage() should not throw exceptions for any BrickType");
        }
    }

    @Test
    void testGetBrickImageHandlesNullType() {
        AssetManager assets = AssetManager.getInstance();
        assertNull(assets.getBrickImage(null),
                "getBrickImage() should return null when passed a null BrickType");
    }

    @Test
    void testGetPowerUpSpriteMapReturnsNonNullForKnownTypes() {
        AssetManager assets = AssetManager.getInstance();

        for (PowerUpTypes type : PowerUpTypes.values()) {
            assertNotNull(assets.getPowerUpSpriteMap(type),
                    "getPowerUpSpriteMap() should return a non-null image for " + type);
        }
    }

    @Test
    void testMediaRetrievalIsSafe() {
        AssetManager assets = AssetManager.getInstance();
        Media media = assets.getMedia("brick_hit.wav");

        // Even if file not found, should not throw or crash
        assertDoesNotThrow(() -> assets.getMedia("brick_hit.wav"),
                "getMedia() should safely handle missing files");
        // Media may be null in a headless environment
        assertTrue(media == null || media.getSource().contains("brick_hit.wav"),
                "If not null, media source should match filename");
    }
}
