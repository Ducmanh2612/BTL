package org.OOPproject.ArkanoidFX.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ParticleSystem {
    private List<Particle> particles;
    private Random random;

    public ParticleSystem() {
        this.particles = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Create a burst of particles at the given position.
     * Used when a brick is hit or destroyed.
     */
    public void createBurstEffect(double x, double y, Color color, int particleCount) {
        for (int i = 0; i < particleCount; i++) {
            // Random velocity in all directions
            double angle = random.nextDouble() * Math.PI * 2;
            double speed = 100 + random.nextDouble() * 200; // 100-300 pixels per second
            double velocityX = Math.cos(angle) * speed;
            double velocityY = Math.sin(angle) * speed - 150; // Bias upward

            double size = 3 + random.nextDouble() * 5; // 3-8 pixels

            particles.add(new Particle(x, y, velocityX, velocityY, color, size));
        }
    }

    /**
     * Update all particles.
     */
    public void update(double deltaTime) {
        Iterator<Particle> iterator = particles.iterator();
        while (iterator.hasNext()) {
            Particle particle = iterator.next();
            particle.update(deltaTime);

            if (particle.isDead()) {
                iterator.remove();
            }
        }
    }

    /**
     * Get all active particles for rendering.
     */
    public List<Particle> getParticles() {
        return particles;
    }

    /**
     * Clear all particles.
     */
    public void clear() {
        particles.clear();
    }
}
