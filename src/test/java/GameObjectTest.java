import org.OOPproject.ArkanoidFX.model.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    private GameObject obj1;
    private GameObject obj2;

    @BeforeEach
    void setUp() {
        // Tạo hai GameObject ẩn danh để test (vì GameObject là abstract)
        obj1 = new GameObject(50, 50, 20, 20) {};
        obj2 = new GameObject(60, 60, 20, 20) {};
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(50, obj1.getX());
        assertEquals(50, obj1.getY());
        assertEquals(20, obj1.getWidth());
        assertEquals(20, obj1.getHeight());
    }

    @Test
    void testSetters() {
        obj1.setX(100);
        obj1.setY(200);
        obj1.setWidth(30);
        obj1.setHeight(40);

        assertEquals(100, obj1.getX());
        assertEquals(200, obj1.getY());
        assertEquals(30, obj1.getWidth());
        assertEquals(40, obj1.getHeight());
    }

    @Test
    void testCollidesWithTrue() {
        // Hai vật thể giao nhau
        assertTrue(obj1.collidesWith(obj2), "Objects should collide when overlapping");
    }

    @Test
    void testCollidesWithFalse_NoOverlap() {
        GameObject farAway = new GameObject(500, 500, 20, 20) {};
        assertFalse(obj1.collidesWith(farAway), "Objects should not collide when far apart");
    }

    @Test
    void testCollidesWithFalse_EdgeTouching() {
        GameObject touching = new GameObject(70, 50, 20, 20) {}; // obj1 right edge at 70
        assertFalse(obj1.collidesWith(touching), "Edge-touching objects should not collide (AABB strict)");
    }

    @Test
    void testPartialOverlapStillCollides() {
        GameObject slightlyOverlap = new GameObject(68, 50, 20, 20) {};
        assertTrue(obj1.collidesWith(slightlyOverlap), "Objects with slight overlap should collide");
    }
}
