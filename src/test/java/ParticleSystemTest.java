import javafx.scene.paint.Color;
import org.OOPproject.ArkanoidFX.model.Particle;
import org.OOPproject.ArkanoidFX.model.ParticleSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ParticleSystem class.
 * Verifies correct creation, updating, and cleanup of particles.
 */
class ParticleSystemTest {

    private ParticleSystem particleSystem;

    @BeforeEach
    void setUp() {
        particleSystem = new ParticleSystem();
    }

    @Test
    void testInitialParticleListIsEmpty() {
        assertTrue(particleSystem.getParticles().isEmpty(),
                "Particle list should start empty");
    }

    @Test
    void testCreateBurstEffectAddsParticles() {
        int count = 20;
        particleSystem.createBurstEffect(100, 200, Color.BLUE, count);
        List<Particle> particles = particleSystem.getParticles();

        assertEquals(count, particles.size(),
                "ParticleSystem should contain the correct number of particles after burst creation");

        // Verify that all particles were initialized near the origin point
        for (Particle p : particles) {
            assertEquals(100, p.getX(), 0.001, "Particle X should match the burst origin");
            assertEquals(200, p.getY(), 0.001, "Particle Y should match the burst origin");
            assertEquals(Color.BLUE, p.getColor(), "All particles should have the same color as input");
        }
    }


    //TODO rewrite test case or logic
//    @Test
//    void testParticlesMoveAfterUpdate() {
//        particleSystem.createBurstEffect(0, 0, Color.RED, 5);
//        List<Particle> beforeUpdate = List.copyOf(particleSystem.getParticles());
//
//        particleSystem.update(0.1);
//
//        boolean anyMoved = false;
//        for (int i = 0; i < beforeUpdate.size(); i++) {
//            Particle old = beforeUpdate.get(i);
//            Particle updated = particleSystem.getParticles().get(i);
//            if (old.getX() != updated.getX() || old.getY() != updated.getY()) {
//                anyMoved = true;
//                break;
//            }
//        }
//        assertTrue(anyMoved, "At least one particle should move after update()");
//    }

    @Test
    void testDeadParticlesAreRemovedAfterUpdate() {
        particleSystem.createBurstEffect(50, 50, Color.GREEN, 10);

        // Simulate several seconds passing so all particles die
        for (int i = 0; i < 10; i++) {
            particleSystem.update(0.5);
        }

        assertTrue(particleSystem.getParticles().isEmpty(),
                "All particles should be removed when their lifetime ends");
    }

    @Test
    void testClearRemovesAllParticles() {
        particleSystem.createBurstEffect(20, 20, Color.YELLOW, 15);
        assertFalse(particleSystem.getParticles().isEmpty(),
                "Particles should exist after creation");

        particleSystem.clear();
        assertTrue(particleSystem.getParticles().isEmpty(),
                "clear() should remove all particles");
    }
}
