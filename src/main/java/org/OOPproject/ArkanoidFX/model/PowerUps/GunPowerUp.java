package org.OOPproject.ArkanoidFX.model.PowerUps;

import org.OOPproject.ArkanoidFX.model.Paddle;

public class GunPowerUp extends PowerUp{
    public GunPowerUp(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.type = PowerUpTypes.GUN;
        this.duration = 300;
    }

    @Override
    public void applyEffect(Paddle paddle) {
        paddle.setGun(true);
    }

    @Override
    public void removeEffect(Paddle paddle) {
        paddle.setCoolDown(0);
        paddle.setGun(false);
    }
}
