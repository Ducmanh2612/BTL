package org.OOPproject.ArkanoidFX.model.Bricks;

import static org.OOPproject.ArkanoidFX.utils.newConstants.BRICK_HEIGHT;
import static org.OOPproject.ArkanoidFX.utils.newConstants.BRICK_WIDTH;

public class BrickFactory {
    public static Brick createBrick(BrickType brickType, int x, int y) {
        return switch (brickType) {
            case RUBY ->
                    new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, BrickType.RUBY, BrickType.RUBY.ScoreValue, BrickType.RUBY.maxHits);
            case YLLW ->
                    new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, BrickType.YLLW, BrickType.YLLW.ScoreValue, BrickType.YLLW.maxHits);
            case BLUE ->
                    new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, BrickType.BLUE, BrickType.BLUE.ScoreValue, BrickType.BLUE.maxHits);
            case MGNT ->
                    new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, BrickType.MGNT, BrickType.MGNT.ScoreValue, BrickType.MGNT.maxHits);
            case LIME ->
                    new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, BrickType.LIME, BrickType.LIME.ScoreValue, BrickType.LIME.maxHits);
            case WHIT ->
                    new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, BrickType.WHIT, BrickType.WHIT.ScoreValue, BrickType.WHIT.maxHits);
            case ORNG ->
                    new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, BrickType.ORNG, BrickType.ORNG.ScoreValue, BrickType.ORNG.maxHits);
            case CYAN ->
                    new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, BrickType.CYAN, BrickType.CYAN.ScoreValue, BrickType.CYAN.maxHits);
            case GRAY -> new StrongBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
            case GOLD -> new UnbreakableBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
            default -> new ExtraStrongBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        };
    }
}
