package org.OOPproject.ArkanoidFX.model;
import org.OOPproject.ArkanoidFX.model.Bricks.*;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private int levelNumber;
    private String name;
    private int[][] layout; // 2D array: 0=empty, 1=normal, 2=strong, 3=extra_strong, 9=unbreakable

    // Brick dimensions
    private static final int BRICK_WIDTH = 75;
    private static final int BRICK_HEIGHT = 25;
    private static final int SPACING = 2;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.layout = generateLayout(levelNumber);
        this.name = "Level " + levelNumber;
    }

    /**
     * Generate a layout based on level number.
     * Higher levels have more bricks and tougher patterns.
     */
    private int[][] generateLayout(int level) {
        switch (level) {
            case 1:
                return getLevel1Layout();
            case 2:
                return getLevel2Layout();
            case 3:
                return getLevel3Layout();
            case 4:
                return getLevel4Layout();
            case 5:
                return getLevel5Layout();
            default:
                return generateRandomLayout(level);
        }
    }

    /**
     * Level 1 - Simple pattern, mostly normal bricks.
     */
    private int[][] getLevel1Layout() {
        return new int[][] {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
    }

    /**
     * Level 2 - More strong bricks.
     */
    private int[][] getLevel2Layout() {
        return new int[][] {
                {2, 1, 1, 1, 1, 1, 1, 1, 1, 2},
                {1, 2, 1, 1, 1, 1, 1, 1, 2, 1},
                {1, 1, 2, 2, 1, 1, 2, 2, 1, 1},
                {1, 1, 1, 1, 2, 2, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
    }

    /**
     * Level 3 - Pattern with gaps and extra strong bricks.
     */
    private int[][] getLevel3Layout() {
        return new int[][] {
                {2, 0, 2, 0, 2, 2, 0, 2, 0, 2},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 3, 1, 1, 1, 1, 1, 1, 3, 1},
                {1, 1, 2, 2, 2, 2, 2, 2, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 0, 0, 0, 0, 2, 2, 2}
        };
    }

    /**
     * Level 4 - Fortress pattern with unbreakable walls.
     */
    private int[][] getLevel4Layout() {
        return new int[][] {
                {9, 1, 1, 1, 1, 1, 1, 1, 1, 9},
                {9, 2, 1, 1, 1, 1, 1, 1, 2, 9},
                {9, 2, 2, 1, 1, 1, 1, 2, 2, 9},
                {9, 3, 2, 2, 1, 1, 2, 2, 3, 9},
                {9, 3, 3, 2, 2, 2, 2, 3, 3, 9},
                {9, 9, 9, 9, 3, 3, 9, 9, 9, 9}
        };
    }

    /**
     * Level 5 - Checkerboard pattern with mixed brick types.
     */
    private int[][] getLevel5Layout() {
        return new int[][] {
                {2, 0, 2, 0, 2, 0, 2, 0, 2, 0},
                {0, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                {2, 0, 2, 0, 2, 0, 2, 0, 2, 0},
                {0, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 1, 2, 1, 1, 2, 1, 2, 1},
                {9, 9, 0, 0, 3, 3, 0, 0, 9, 9}
        };
    }

    /**
     * Generate random layout for higher levels.
     */
    private int[][] generateRandomLayout(int level) {
        int rows = Math.min(4 + level, 10); // Max 10 rows
        int cols = 10;
        int[][] randomLayout = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int rand = (int)(Math.random() * 100);
                if (rand < 10) {
                    randomLayout[r][c] = 0; // 10% empty
                } else if (rand < 40) {
                    randomLayout[r][c] = 2; // 30% strong
                } else if (rand < 50) {
                    randomLayout[r][c] = 3; // 10% extra strong
                } else if (rand < 55) {
                    randomLayout[r][c] = 9; // 5% unbreakable
                } else {
                    randomLayout[r][c] = 1; // 45% normal
                }
            }
        }

        return randomLayout;
    }

    /**
     * Create bricks from this level's layout.
     */
    public List<Brick> createBricks(int gameWidth, int startY) {
        List<Brick> bricks = new ArrayList<>();
        int rows = layout.length;
        int cols = layout[0].length;

        // Calculate starting X to center the bricks
        int totalWidth = cols * BRICK_WIDTH + (cols - 1) * SPACING;
        int startX = (gameWidth - totalWidth) / 2;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int brickType = layout[r][c];
                if (brickType == 0) continue; // Skip empty spaces

                int x = startX + c * (BRICK_WIDTH + SPACING);
                int y = startY + r * (BRICK_HEIGHT + SPACING);

                Brick brick = null;
                switch (brickType) {
                    case 1:
                        brick = new NormalBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                        break;
                    case 2:
                        brick = new StrongBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                        break;
                    case 3:
                        brick = new ExtraStrongBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                        break;
                    case 9:
                        brick = new UnbreakableBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                        break;
                }

                if (brick != null) {
                    bricks.add(brick);
                }
            }
        }

        return bricks;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getName() {
        return name;
    }
}
