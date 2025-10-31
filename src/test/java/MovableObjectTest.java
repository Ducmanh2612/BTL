import org.OOPproject.ArkanoidFX.model.GameObject;
import org.OOPproject.ArkanoidFX.model.MovableObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests cho lớp MovableObject
 */
class MovableObjectTest {

    private MovableObject movable;

    @BeforeEach
    void setUp() {
        // Lớp giả cụ thể chỉ để test
        movable = new MovableObject(10, 20, 30, 40) {
            @Override
            public void move(double deltaTime) {
                // Đơn giản: cộng vận tốc vào vị trí
                this.x += velocityX * deltaTime;
                this.y += velocityY * deltaTime;
            }
        };
    }

    @Test
    void testInitialValues() {
        assertEquals(10, movable.getX());
        assertEquals(20, movable.getY());
        assertEquals(30, movable.getWidth());
        assertEquals(40, movable.getHeight());
        assertEquals(0.0, movable.getVelocityX());
        assertEquals(0.0, movable.getVelocityY());
    }

    @Test
    void testSetVelocity() {
        movable.setVelocityX(100.5);
        movable.setVelocityY(-50.25);
        assertEquals(100.5, movable.getVelocityX());
        assertEquals(-50.25, movable.getVelocityY());
    }

    @Test
    void testMoveChangesPosition() {
        movable.setVelocityX(200);
        movable.setVelocityY(100);
        movable.move(0.5); // Move half a second
        assertEquals(10 + 200 * 0.5, movable.getX(), 0.001);
        assertEquals(20 + 100 * 0.5, movable.getY(), 0.001);
    }

    @Test
    void testUpdateCallsMove() {
        movable.setVelocityX(50);
        movable.setVelocityY(20);
        movable.update(1.0); // Should move same as move(1.0)
        assertEquals(60.0, movable.getX(), 0.001);
        assertEquals(40.0, movable.getY(), 0.001);
    }

    @Test
    void testInheritance() {
        assertTrue(movable instanceof GameObject);
    }
}
