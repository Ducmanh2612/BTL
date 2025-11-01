package org.OOPproject.ArkanoidFX.model;

public class Destroy extends GameObject{
    private Sprite sprite;
    private Enemy attachtedEnemy;

    public Destroy(Enemy enemy) {
        super(enemy.getX(), enemy.getY(), 32, 32);
        attachtedEnemy = enemy;
        this.sprite = new Sprite(4, 4, 0.03, false);
    }


    public void update(double deltaTime) {
        sprite.update(deltaTime);
        // Keep blink position synced with brick
        if (attachtedEnemy != null) {
            this.x = attachtedEnemy.getX();
            this.y = attachtedEnemy.getY();
        }
    }

    /**
     * Check if animation is complete and should be removed.
     *
     * @return true if animation has played through all frames
     */
    public boolean isFinished() {
        return sprite.isFinished();
    }

    /**
     * Get the brick this blink is attached to.
     *
     * @return The attached brick
     */
    public Enemy getAttachedEnemy() {
        return attachtedEnemy;
    }

    // Getters for sprite animation (delegate to Sprite)
    public int getFrameX() { return sprite.getFrameX(); }
    public int getFrameY() { return sprite.getFrameY(); }
    public int getMaxFrameX() { return sprite.getMaxFrameX(); }
    public int getMaxFrameY() { return sprite.getMaxFrameY(); }
}
