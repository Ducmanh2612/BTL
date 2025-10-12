package org.example.arkanoidFX.gameobject.brick;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Strong brick that requires multiple hits to destroy.
 * Demonstrates OOP principle: Inheritance and Polymorphism
 */
public class StrongBrick extends Brick {
    public StrongBrick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitPoints = 3;
        this.type = "Strong";
        this.scoreValue = 30;

        this.textures.add(new ImageView(new Image(getClass().getResourceAsStream("/textures/StrongBrick.png"))));
        this.textures.add(new ImageView(new Image(getClass().getResourceAsStream("/textures/StrongBrick_Cracked.png"))));
        this.textures.add(new ImageView(new Image(getClass().getResourceAsStream("/textures/StrongBrick_Cracked1.png"))));
        this.textures.add(new ImageView(new Image(getClass().getResourceAsStream("/textures/StrongBrick_Cracked2.png"))));
        this.state = BrickState.NORMAL;
    }

    @Override
    public void takeHit() {
        hitPoints--;
        if (hitPoints == 2) {
            state = BrickState.CRACKED1;
        } else if (hitPoints == 1) {
            state = BrickState.CRACKED2;
        } else if (hitPoints == 0) {
            state = BrickState.CRACKED3;
        }
    }

    @Override
    public boolean isDestroyed() {
        return hitPoints <= 0;
    }

    @Override
    public void update() {
        // Strong bricks don't need updating
    }

    @Override
    public void render() {
        // Render method for terminal
    }

    @Override
    public ImageView getTexture() {
        switch (state) {
            case NORMAL: return textures.get(0);
            case CRACKED1: return textures.get(1);
            case CRACKED2: return textures.get(2);
            case CRACKED3: return textures.get(3);
            default: return textures.get(0);
        }
    }
}
