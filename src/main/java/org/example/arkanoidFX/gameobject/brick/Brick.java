package org.example.arkanoidFX.gameobject.brick;

import javafx.scene.image.ImageView;
import org.example.arkanoidFX.gameobject.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all brick types.
 * Demonstrates OOP principle: Abstraction
 */
public abstract class Brick extends GameObject {
    protected int hitPoints;
    protected String type;
    protected int scoreValue;
    protected List<ImageView> textures;
    protected BrickState state;

    public Brick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.textures = new ArrayList<>();
        this.state = BrickState.NORMAL;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public String getType() {
        return type;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public abstract void takeHit();

    public abstract boolean isDestroyed();
}
