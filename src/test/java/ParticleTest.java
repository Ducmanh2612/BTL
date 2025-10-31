import javafx.scene.paint.Color;
import org.OOPproject.ArkanoidFX.model.Particle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Particle class.
 * Tests motion, gravity, lifetime, and size behavior.
 */
class ParticleTest {

    private Particle particle;

    @BeforeEach
    void setUp() {
        particle = new Particle(100, 100, 50, -100, Color.RED, 10);
    }

    @Test
    void testInitialValues() {
        assertEquals(100, particle.getX(), "Initial X position should match constructor value");
        assertEquals(100, particle.getY(), "Initial Y position should match constructor value");
        assertEquals(Color.RED, particle.getColor(), "Color should match the one provided in constructor");
        assertEquals(1.0, particle.getLife(), "Particle life should start at 1.0");
        assertEquals(10.0, particle.getSize(), "Initial size should match constructor value");
    }

    @Test
    void testUpdateChangesPosition() {
        double oldX = particle.getX();
        double oldY = particle.getY();
        particle.update(0.1); // simulate 0.1s frame

        assertNotEquals(oldX, particle.getX(), "X should change after update due to velocity");
        assertNotEquals(oldY, particle.getY(), "Y should change after update due to velocity and gravity");
    }

    @Test
    void testGravityIncreasesVelocityY() {
        double oldVelocityY = -100; // initial
        particle.update(0.5);
        assertTrue(particle.getY() > -100, "Y should increase because gravity pulls down");
        assertTrue(particle.getLife() < 1.0, "Life should decrease after update");
    }

    @Test
    void testLifeDecreasesOverTime() {
        particle.update(0.25);
        assertTrue(particle.getLife() < 1.0, "Life should decrease after update()");
        double previousLife = particle.getLife();
        particle.update(0.25);
        assertTrue(particle.getLife() < previousLife, "Life should continue to decrease over time");
    }

    @Test
    void testIsDeadReturnsTrueWhenLifeEnds() {
        particle.update(1.0); // after 1s, life should reach 0
        assertTrue(particle.isDead(), "Particle should be considered dead after enough time passes");
    }

    @Test
    void testSizeShrinksWithLife() {
        double originalSize = particle.getSize();
        particle.update(0.25);
        double smallerSize = particle.getSize();
        assertTrue(smallerSize < originalSize, "Particle size should shrink as life decreases");
    }
}
