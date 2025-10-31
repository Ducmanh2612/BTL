package org.OOPproject.ArkanoidFX.model.PowerUps;

import org.OOPproject.ArkanoidFX.model.Paddle;

public class MultiBallPowerUp extends PowerUp {
    private MultiBallCallback callback;

    public MultiBallPowerUp(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.type = PowerUpTypes.MULTI_BALL;
        this.duration = 0; // Instant effect, no duration
    }

    public void setCallback(MultiBallCallback callback) {
        this.callback = callback;
    }

    @Override
    public void applyEffect(Paddle paddle) {
        // When caught, spawn additional balls
        if (callback != null) {
            callback.spawnExtraBalls(paddle);
        }
    }

    @Override
    public void removeEffect(Paddle paddle) {
        // No removal needed - balls stay in play until they fall off
    }

    // Callback interface for spawning balls (GameEngine will implement this)
    public interface MultiBallCallback {
        void spawnExtraBalls(Paddle paddle);
    }
}
