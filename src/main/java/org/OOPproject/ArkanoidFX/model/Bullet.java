package org.OOPproject.ArkanoidFX.model;

import static org.OOPproject.ArkanoidFX.utils.Constants.BULLET_VELOCITY;
import static org.OOPproject.ArkanoidFX.utils.Constants.ENEMY_SIZE;

public class Bullet extends MovableObject {
    public Bullet(int x, int y, int width, int height, double velocityX, double velocityY) {
        super(x, y, width, height);
        setVelocityX(velocityX);
        setVelocityY(velocityY);
    }

    public boolean willCollideEnemy(Enemy enemy, double deltaTime) {
        //because handle perfectly is very hard, create a loop with step is deltaTime / 10
        //to check if enemy and ball is collide

        Bullet fakeBullet = new Bullet(0,0, width, height, 0, BULLET_VELOCITY);
        Enemy fakeEnemy = new Enemy(0, 0, ENEMY_SIZE);

        for (double t = 0; t <= deltaTime; t += deltaTime/10) {

            //next position if ball continues on current path
            double x0 = x + velocityX * t;
            double y0 = y + velocityY * t;

            // Next position if enemy continues on current path
            double x1 = enemy.x + enemy.velocityX * t;
            double y1 = enemy.y + enemy.velocityY * t;

            fakeBullet.setX((int)x0);
            fakeBullet.setY((int)y0);
            fakeEnemy.setX((int)x1);
            fakeEnemy.setY((int)y1);

            if (fakeBullet.collidesWith(fakeEnemy)) {
                return true;
            }
        }

        return false; // No hit
    }

    public boolean willCollideBrick(GameObject brick, double deltaTime) {
        Bullet fakeBullet = new Bullet(0,0, width, height, 0, BULLET_VELOCITY);
        for (double t = 0; t <= deltaTime; t += deltaTime/10) {

            //next position if ball continues on current path
            double x0 = x + velocityX * t;
            double y0 = y + velocityY * t;


            fakeBullet.setX((int)x0);
            fakeBullet.setY((int)y0);

            if (fakeBullet.collidesWith(brick)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void move(double deltaTime) {
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;
    }
}
