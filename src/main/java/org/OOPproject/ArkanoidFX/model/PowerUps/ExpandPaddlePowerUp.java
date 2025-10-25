package org.OOPproject.ArkanoidFX.model.PowerUps;

import org.OOPproject.ArkanoidFX.model.Paddle;

public class ExpandPaddlePowerUp extends PowerUp {
    public ExpandPaddlePowerUp(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.type = PowerUpTypes.EXPAND_PADDLE;
        this.duration = 300; // Duration in frames (5 seconds at 60 FPS)
    }

    @Override
    public void applyEffect(Paddle paddle) {
        paddle.expandPaddle();
    }

    @Override
    public void removeEffect(Paddle paddle) {
        paddle.restorePaddleSize();
    }
}
