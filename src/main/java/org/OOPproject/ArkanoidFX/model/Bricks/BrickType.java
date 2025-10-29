package org.OOPproject.ArkanoidFX.model.Bricks;

public enum BrickType {
    NONE(0, 0),
    GOLD(-1, 0),
    GRAY(2, 0),
    RUBY(1, 90),
    YLLW(1, 120),
    BLUE(1, 100),
    MGNT(1, 110),
    LIME(1, 80),
    WHIT(1, 50),
    ORNG(1, 60),
    CYAN(1, 70);

    public final int maxHits;
    public final int ScoreValue;
    BrickType(final int maxHits, final int scoreValue) {
        this.maxHits = maxHits;
        this.ScoreValue = scoreValue;
    }

}
