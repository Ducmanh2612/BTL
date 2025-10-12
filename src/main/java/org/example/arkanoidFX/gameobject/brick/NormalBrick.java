package org.example.arkanoidFX.gameobject.brick;

import javafx.scene.image.ImageView;


/**
 * Normal brick that breaks after one hit.
 * Demonstrates OOP principle: Inheritance and Polymorphism
 */
public class NormalBrick extends Brick {
    public NormalBrick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitPoints = 1;
        this.type = "Normal";
        this.scoreValue = 10;

        this.textures.add(new ImageView(new javafx.scene.image.Image(getClass().getResourceAsStream("/textures/NormalBrick.png"))));
        this.textures.add(new ImageView(new javafx.scene.image.Image(getClass().getResourceAsStream("/textures/NormalBrick_Cracked.png"))));
        this.state = BrickState.NORMAL;
    }

    @Override
    public void takeHit() {
        hitPoints--;
        if (hitPoints == 0) {
            state = BrickState.CRACKED;
        }
    }

    @Override
    public ImageView getTexture() {
        return state == BrickState.NORMAL ? textures.get(0) : textures.get(1);
    }

    @Override
    public boolean isDestroyed() {
        return hitPoints <= 0;
    }

    @Override
    public void update() {
        // Normal bricks don't need updating
    }

    @Override
    public void render() {
        // Render method for terminal
    }
}
