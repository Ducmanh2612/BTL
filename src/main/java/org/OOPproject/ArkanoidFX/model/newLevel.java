package org.OOPproject.ArkanoidFX.model;

import org.OOPproject.ArkanoidFX.model.Bricks.*;
import java.util.ArrayList;
import java.util.List;
import org.OOPproject.ArkanoidFX.model.Bricks.BrickType;

/**
 * BRICK TYPE ENCODING:
 * Empty space
 * 1 = Normal (cyan - 100 pts)
 * 2 = Strong Gray (2 hits - 50 pts)
 * 3 = Extra Strong Purple (5 hits - 200 pts)
 * 9 = Unbreakable Gold (cannot be destroyed)
 *
 * COLORED BRICKS (1 hit each):
 * 10 = Red/Ruby (90 pts)
 * 11 = Yellow (120 pts)
 * 12 = Blue (100 pts)
 * 13 = Magenta (110 pts)
 * 14 = Lime/Green (80 pts)
 * 15 = White (10 pts)
 * 16 = Orange (60 pts)
 * 17 = Cyan (70 pts)
 */
public class newLevel {
    private int levelNumber;;
    private BrickType[][] layout;

    // Brick dimensions
    private static final int BRICK_WIDTH = 38;
    private static final int BRICK_HEIGHT = 20;
    private static final int SPACING = 2;

    public newLevel(int levelNumber) {
        this.levelNumber = levelNumber;
        this.layout = generateLayout(levelNumber);
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public List<Brick> createBricks(int gameWidth, int startY) {
        List<Brick> bricks = new ArrayList<>();
        int rows = layout.length;
        int cols = layout[0].length;

        // Calculate starting X to center the bricks
        int totalWidth = cols * BRICK_WIDTH + (cols - 1) * SPACING;
        int startX = (gameWidth - totalWidth) / 2;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                BrickType brickType = layout[r][c];
                if (brickType == BrickType.NONE) continue; // Skip empty spaces

                int x = startX + c * (BRICK_WIDTH + SPACING);
                int y = startY + r * (BRICK_HEIGHT + SPACING);

                bricks.add(BrickFactory.createBrick(brickType, x, y));
            }
        }

        return bricks;
    }

    private BrickType[][] generateRandomLayout(int level) {
        int rows = Math.min(4 + level, 10); // Max 10 rows
        int cols = 10;
        BrickType[][] randomLayout = new BrickType[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int rand = (int)(Math.random() * 100);
                if (rand < 10) {
                    randomLayout[r][c] = BrickType.NONE; // 10% empty
                } else if (rand < 40) {
                    randomLayout[r][c] = BrickType.GRAY; // 30% strong
                } else if (rand < 50) {
                    randomLayout[r][c] = BrickType.BLUE; // 10% extra strong
                } else if (rand < 55) {
                    randomLayout[r][c] = BrickType.GOLD; // 5% unbreakable
                } else {
                    randomLayout[r][c] = BrickType.CYAN; // 45% normal
                }
            }
        }

        return randomLayout;
    }

    private BrickType[][] generateLayout(int level) {
        switch (level) {
            case 1:
                return LEVEL_1;
            case 2:
                return LEVEL_2;
            case 3:
                return LEVEL_3;
            case 4:
                return LEVEL_4;
            case 5:
                return LEVEL_5;
            case 6:
                return LEVEL_6;
            case 7:
                return LEVEL_7;
            case 8:
                return LEVEL_8;
            case 9:
                return LEVEL_9;
            case 10:
                return LEVEL_10;
            case 11:
                return LEVEL_11;
            case 12:
                return LEVEL_12;
            case 13:
                return LEVEL_13;
            case 14:
                return LEVEL_14;
            case 15:
                return LEVEL_15;
            case 16:
                return LEVEL_16;
            case 17:
                return LEVEL_17;
            case 18:
                return LEVEL_18;
            case 19:
                return LEVEL_19;
            case 20:
                return LEVEL_20;
            case 21:
                return LEVEL_21;
            case 22:
                return LEVEL_22;
            case 23:
                return LEVEL_23;
            case 24:
                return LEVEL_24;
            case 25:
                return LEVEL_25;
            case 26:
                return LEVEL_26;
            case 27:
                return LEVEL_27;
            case 28:
                return LEVEL_28;
            case 29:
                return LEVEL_29;
            case 30:
                return LEVEL_30;
            default:
                return generateRandomLayout(level);
        }
    }

    protected static final BrickType[][] LEVEL_1 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW},
                    {BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE},
                    {BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT},
                    {BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},};

    protected static final BrickType[][] LEVEL_2 =
            {{BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.RUBY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.NONE},
                    {BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.RUBY},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_3 =
            {{BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_4 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.NONE, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.NONE},
                    {BrickType.NONE, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.NONE, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.NONE, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.NONE, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.NONE},
                    {BrickType.NONE, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.NONE, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.NONE},
                    {BrickType.NONE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.NONE, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.NONE},
                    {BrickType.NONE, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.NONE, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.NONE},
                    {BrickType.NONE, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.NONE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.NONE},
                    {BrickType.NONE, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.NONE, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.NONE},
                    {BrickType.NONE, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.NONE, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.NONE, BrickType.ORNG, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.NONE, BrickType.CYAN, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.NONE},
                    {BrickType.NONE, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.NONE, BrickType.LIME, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.NONE},
                    {BrickType.NONE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.ORNG, BrickType.CYAN, BrickType.NONE, BrickType.GRAY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.WHIT, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_5 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.RUBY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.RUBY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.RUBY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.RUBY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_6 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.GOLD, BrickType.ORNG, BrickType.GOLD, BrickType.ORNG, BrickType.GOLD, BrickType.ORNG, BrickType.GOLD, BrickType.ORNG, BrickType.GOLD, BrickType.NONE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.ORNG, BrickType.NONE, BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG, BrickType.NONE, BrickType.ORNG},
                    {BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_7 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.MGNT, BrickType.MGNT, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.MGNT, BrickType.MGNT, BrickType.BLUE, BrickType.BLUE, BrickType.RUBY, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.MGNT, BrickType.MGNT, BrickType.BLUE, BrickType.BLUE, BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.MGNT, BrickType.MGNT, BrickType.BLUE, BrickType.BLUE, BrickType.RUBY, BrickType.RUBY, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.MGNT, BrickType.BLUE, BrickType.BLUE, BrickType.RUBY, BrickType.RUBY, BrickType.LIME, BrickType.LIME, BrickType.CYAN, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.BLUE, BrickType.BLUE, BrickType.RUBY, BrickType.RUBY, BrickType.LIME, BrickType.LIME, BrickType.CYAN, BrickType.CYAN, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.RUBY, BrickType.RUBY, BrickType.LIME, BrickType.LIME, BrickType.CYAN, BrickType.CYAN, BrickType.ORNG, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.RUBY, BrickType.RUBY, BrickType.LIME, BrickType.LIME, BrickType.CYAN, BrickType.CYAN, BrickType.ORNG, BrickType.ORNG, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.RUBY, BrickType.RUBY, BrickType.LIME, BrickType.LIME, BrickType.CYAN, BrickType.CYAN, BrickType.ORNG, BrickType.ORNG, BrickType.WHIT, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.CYAN, BrickType.CYAN, BrickType.ORNG, BrickType.ORNG, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.CYAN, BrickType.CYAN, BrickType.ORNG, BrickType.ORNG, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.CYAN, BrickType.ORNG, BrickType.ORNG, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.ORNG, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_8 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.ORNG, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.BLUE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_9 =
            {{BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.LIME, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.LIME, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.CYAN, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.CYAN, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.ORNG, BrickType.ORNG, BrickType.ORNG, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_10 =
            {{BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.CYAN, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.CYAN, BrickType.WHIT, BrickType.CYAN, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.CYAN, BrickType.WHIT, BrickType.CYAN, BrickType.WHIT, BrickType.CYAN, BrickType.BLUE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.BLUE, BrickType.CYAN, BrickType.WHIT, BrickType.CYAN, BrickType.GRAY, BrickType.CYAN, BrickType.WHIT, BrickType.CYAN, BrickType.BLUE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.CYAN, BrickType.WHIT, BrickType.CYAN, BrickType.WHIT, BrickType.CYAN, BrickType.BLUE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.CYAN, BrickType.WHIT, BrickType.CYAN, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.CYAN, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD}};

    protected static final BrickType[][] LEVEL_11 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_12 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.WHIT, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.LIME, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.BLUE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.CYAN, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.YLLW},
                    {BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD}};

    protected static final BrickType[][] LEVEL_13 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.NONE},
                    {BrickType.NONE, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.NONE, BrickType.ORNG, BrickType.ORNG, BrickType.ORNG, BrickType.NONE, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.NONE},
                    {BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE},
                    {BrickType.NONE, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.NONE},
                    {BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE},
                    {BrickType.NONE, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.NONE},
                    {BrickType.NONE, BrickType.ORNG, BrickType.ORNG, BrickType.ORNG, BrickType.NONE, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.NONE, BrickType.ORNG, BrickType.ORNG, BrickType.ORNG, BrickType.NONE},
                    {BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_14 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD},
                    {BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.ORNG, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.ORNG},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD},
                    {BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.CYAN, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.CYAN},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD}};

    protected static final BrickType[][] LEVEL_15 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.CYAN, BrickType.WHIT, BrickType.GOLD, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.GOLD, BrickType.WHIT, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.WHIT, BrickType.YLLW, BrickType.GOLD, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.GOLD, BrickType.LIME, BrickType.WHIT, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.WHIT, BrickType.YLLW, BrickType.YLLW, BrickType.GOLD, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.GOLD, BrickType.LIME, BrickType.LIME, BrickType.WHIT, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.WHIT, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.GOLD, BrickType.WHIT, BrickType.GOLD, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.WHIT, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.WHIT, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.WHIT, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.WHIT, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.WHIT, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.WHIT, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.WHIT, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.GRAY, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.GRAY, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.CYAN, BrickType.GRAY, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.GRAY, BrickType.CYAN, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.GRAY, BrickType.YLLW, BrickType.YLLW, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.GRAY, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.GRAY, BrickType.YLLW, BrickType.WHIT, BrickType.LIME, BrickType.GRAY, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN},
                    {BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.GRAY, BrickType.WHIT, BrickType.GRAY, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN}};

    protected static final BrickType[][] LEVEL_16 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.ORNG, BrickType.ORNG, BrickType.NONE, BrickType.ORNG, BrickType.ORNG, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.WHIT},
                    {BrickType.NONE, BrickType.NONE, BrickType.ORNG, BrickType.ORNG, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.ORNG, BrickType.ORNG, BrickType.NONE, BrickType.NONE},
                    {BrickType.ORNG, BrickType.ORNG, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.ORNG, BrickType.ORNG},
                    {BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.NONE, BrickType.NONE},
                    {BrickType.YLLW, BrickType.YLLW, BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.YLLW},
                    {BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.NONE, BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.LIME},
                    {BrickType.NONE, BrickType.NONE, BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.NONE},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.NONE},
                    {BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE}};

    protected static final BrickType[][] LEVEL_17 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.GRAY, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE},
                    {BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE},
                    {BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_18 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.GOLD, BrickType.NONE, BrickType.ORNG},
                    {BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.ORNG},
                    {BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG},
                    {BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.MGNT, BrickType.GOLD, BrickType.YLLW, BrickType.GOLD, BrickType.CYAN, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG},
                    {BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG},
                    {BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG},
                    {BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG},
                    {BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG},
                    {BrickType.ORNG, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.ORNG},
                    {BrickType.ORNG, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.MGNT, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.ORNG},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_19 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.GOLD, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.GOLD, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.GOLD, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.YLLW, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.GOLD, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.GOLD, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.LIME, BrickType.RUBY, BrickType.BLUE, BrickType.MGNT, BrickType.GOLD, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_20 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GOLD, BrickType.WHIT, BrickType.GOLD, BrickType.ORNG, BrickType.GOLD, BrickType.CYAN, BrickType.GOLD, BrickType.LIME, BrickType.GOLD, BrickType.RUBY, BrickType.GOLD, BrickType.BLUE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.GRAY, BrickType.GOLD, BrickType.GRAY, BrickType.GOLD, BrickType.GRAY, BrickType.GOLD, BrickType.GRAY, BrickType.GOLD, BrickType.YLLW, BrickType.GOLD},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_21 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.ORNG, BrickType.NONE, BrickType.ORNG, BrickType.NONE, BrickType.ORNG, BrickType.NONE, BrickType.ORNG, BrickType.NONE, BrickType.ORNG, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_22 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW},
                    {BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT},
                    {BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_23 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY},
                    {BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.LIME, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.LIME, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.LIME, BrickType.GRAY},
                    {BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.RUBY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.RUBY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.RUBY, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.NONE},
                    {BrickType.GRAY, BrickType.BLUE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.BLUE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.BLUE, BrickType.GRAY, BrickType.NONE, BrickType.NONE},
                    {BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_24 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.BLUE, BrickType.WHIT, BrickType.BLUE, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.WHIT, BrickType.BLUE, BrickType.BLUE, BrickType.WHIT, BrickType.BLUE, BrickType.BLUE, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE},
                    {BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE}};

    protected static final BrickType[][] LEVEL_25 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME},
                    {BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE},
                    {BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD},
                    {BrickType.GOLD, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GOLD},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_26 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GOLD, BrickType.NONE, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GOLD, BrickType.NONE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_27 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY},
                    {BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW},
                    {BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY}};

    protected static final BrickType[][] LEVEL_28 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.GOLD, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.GOLD, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.GOLD, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.GOLD, BrickType.MGNT, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.MGNT, BrickType.GOLD, BrickType.BLUE},
                    {BrickType.BLUE, BrickType.GOLD, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.GOLD, BrickType.BLUE},
                    {BrickType.NONE, BrickType.BLUE, BrickType.GOLD, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.NONE, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.GOLD, BrickType.BLUE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.GOLD, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.GOLD, BrickType.BLUE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.GOLD, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.GOLD, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.GOLD, BrickType.MGNT, BrickType.GOLD, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.MGNT, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_29 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW},
                    {BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT},
                    {BrickType.GOLD, BrickType.GOLD, BrickType.WHIT, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.GOLD, BrickType.GOLD, BrickType.WHIT, BrickType.GOLD, BrickType.GOLD},
                    {BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE},
                    {BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY},
                    {BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME, BrickType.LIME},
                    {BrickType.GRAY, BrickType.GRAY, BrickType.WHIT, BrickType.GRAY, BrickType.GRAY, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.GRAY, BrickType.GRAY, BrickType.WHIT, BrickType.GRAY, BrickType.GRAY},
                    {BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN, BrickType.CYAN},
                    {BrickType.ORNG, BrickType.ORNG, BrickType.ORNG, BrickType.ORNG, BrickType.ORNG, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.ORNG, BrickType.ORNG, BrickType.ORNG, BrickType.ORNG, BrickType.ORNG},
                    {BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT, BrickType.WHIT},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_30 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.YLLW, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.YLLW, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.YLLW, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.CYAN, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.YLLW, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.CYAN, BrickType.ORNG, BrickType.WHIT, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.YLLW, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.CYAN, BrickType.ORNG, BrickType.WHIT, BrickType.YLLW, BrickType.MGNT, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.GRAY, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME, BrickType.CYAN, BrickType.ORNG, BrickType.WHIT, BrickType.YLLW, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.NONE},
                    {BrickType.NONE, BrickType.GOLD, BrickType.GRAY, BrickType.RUBY, BrickType.LIME, BrickType.CYAN, BrickType.ORNG, BrickType.WHIT, BrickType.YLLW, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GRAY, BrickType.CYAN, BrickType.ORNG, BrickType.WHIT, BrickType.YLLW, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GRAY, BrickType.WHIT, BrickType.YLLW, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GRAY, BrickType.MGNT, BrickType.BLUE, BrickType.RUBY, BrickType.LIME},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GRAY, BrickType.RUBY, BrickType.LIME},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.GRAY},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_31 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.WHIT, BrickType.NONE, BrickType.ORNG},
                    {BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY},
                    {BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.ORNG, BrickType.NONE, BrickType.WHIT, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.WHIT},
                    {BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY},
                    {BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.ORNG, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.ORNG, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.YLLW},
                    {BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY},
                    {BrickType.NONE, BrickType.YLLW, BrickType.NONE, BrickType.MGNT, BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.CYAN, BrickType.NONE},
                    {BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE},
                    {BrickType.WHIT, BrickType.NONE, BrickType.ORNG, BrickType.NONE, BrickType.CYAN, BrickType.NONE, BrickType.LIME, BrickType.NONE, BrickType.RUBY, BrickType.NONE, BrickType.BLUE, BrickType.NONE, BrickType.MGNT},
                    {BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY, BrickType.NONE, BrickType.GRAY},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_32 =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.LIME, BrickType.LIME, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.RUBY, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.BLUE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.MGNT, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.GOLD, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.YLLW, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.GRAY, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};

    protected static final BrickType[][] LEVEL_TEMPLATE =
            {{BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE},
                    {BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE, BrickType.NONE}};







}
