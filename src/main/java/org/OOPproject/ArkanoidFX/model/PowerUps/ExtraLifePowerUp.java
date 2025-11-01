package org.OOPproject.ArkanoidFX.model.PowerUps;

import org.OOPproject.ArkanoidFX.model.GameEngine;
import org.OOPproject.ArkanoidFX.model.Paddle;

public class ExtraLifePowerUp extends PowerUp {
    private final GameEngine gameEngineRef;

    public ExtraLifePowerUp(int x, int y, int width, int height, GameEngine gameEngineRef) {
        super(x, y, width, height);
        this.type = PowerUpTypes.EXTRA_LIFE;
        this.duration = 0;
        this.gameEngineRef = gameEngineRef;
    }

    @Override
    public void applyEffect(Paddle paddle) {
        gameEngineRef.addExtraLife();
    }

    @Override
    public void removeEffect(Paddle paddle) {
        // No removal needed - life is permanently added
    }
}
