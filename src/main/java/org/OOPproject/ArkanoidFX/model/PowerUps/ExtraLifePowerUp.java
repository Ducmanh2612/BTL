package org.OOPproject.ArkanoidFX.model.PowerUps;

import org.OOPproject.ArkanoidFX.model.Paddle;

public class ExtraLifePowerUp extends PowerUp {
    private ExtraLifeCallback callback;

    public ExtraLifePowerUp(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.type = PowerUpTypes.EXTRA_LIFE;
        this.duration = 0; // Instant effect, no duration
    }

    public void setCallback(ExtraLifeCallback callback) {
        this.callback = callback;
    }

    @Override
    public void applyEffect(Paddle paddle) {
        // When caught, add an extra life
        if (callback != null) {
            callback.addExtraLife();
        }
    }

    @Override
    public void removeEffect(Paddle paddle) {
        // No removal needed - life is permanently added
    }

    // Callback interface for adding a life (GameEngine will implement this)
    public interface ExtraLifeCallback {
        void addExtraLife();
    }
}
