package org.OOPproject.ArkanoidFX.model.PowerUps;

import org.OOPproject.ArkanoidFX.model.GameEngine;
import org.OOPproject.ArkanoidFX.model.Paddle;

public class MultiBallPowerUp extends PowerUp {
    private final GameEngine gameEngineRef;

    public MultiBallPowerUp(int x, int y, int width, int height, GameEngine gameEngineRef) {
        super(x, y, width, height);
        this.type = PowerUpTypes.MULTI_BALL;
        this.duration = 0; // Instant effect, no duration
        this.gameEngineRef = gameEngineRef;
    }

    @Override
    public void applyEffect(Paddle paddle) {
        // When caught, spawn additional balls
        gameEngineRef.spawnExtraBalls(paddle);
    }

    @Override
    public void removeEffect(Paddle paddle) {
        // No removal needed - balls stay in play until they fall off
    }
}
