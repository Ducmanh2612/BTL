package org.OOPproject.ArkanoidFX.model;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.OOPproject.ArkanoidFX.model.Bricks.*;

import javax.annotation.processing.FilerException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;

/**
 * BRICK TYPE ENCODING:
 * 0 = Empty space
 * 1 = Normal (cyan - 100 pts)
 * 2 = Strong Gray (2 hits - 50 pts)
 * 3 = Extra Strong Purple (5 hits - 200 pts)
 * 4-9 = Unbreakable Gold (cannot be destroyed)
 *
 * COLORED BRICKS (1 hit each):
 * 5-10 = Red/Ruby (90 pts)
 * 6-11 = Yellow (120 pts)
 * 7-12 = Blue (100 pts)
 * 8-13 = Magenta (110 pts)
 * 9-14 = Lime/Green (80 pts)
 * 10-15 = White (10 pts)
 * 11-16 = Orange (60 pts)
 * 12-17 = Cyan (70 pts)
 */
public class Level {
    private int levelNumber = 1;
    private String name;
    private int[][] layout;

    // Brick dimensions
    private static final int BRICK_WIDTH = 75;
    private static final int BRICK_HEIGHT = 25;
    private static final int SPACING = 2;
    private static final Gson gson = new Gson();

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.layout = generateLayout(levelNumber);
        this.name = "Level " + levelNumber;
    }

    /**
     * Generate a layout based on level number.
     * Higher levels have more bricks and tougher patterns.
     */
    private static int[][] generateLayout(int level) {
        String path = "src\\main\\resources\\levelofGame\\level" + 1 + ".json";
        try (FileReader reader = new FileReader(path)) {
            int[][] arr = gson.fromJson(reader, int[][].class);
            reader.close();
            return arr;
        } catch (JsonSyntaxException e) {
            System.err.println(e.getMessage());
            return null;
        } catch (JsonIOException e ) {
            System.err.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    //TODO: refactor these hardcoded layouts to external JSON or XML files for easier editing
    //  or add a new level

//    private int[][] generateRandomLayout(int level) {
//        int rows = Math.min(4 + level, 10); // Max 10 rows
//        int cols = 10;
//        int[][] randomLayout = new int[rows][cols];
//
//        for (int r = 0; r < rows; r++) {
//            for (int c = 0; c < cols; c++) {
//                int rand = (int)(Math.random() * 100);
//                if (rand < 10) {
//                    randomLayout[r][c] = 0; // 10% empty
//                } else if (rand < 40) {
//                    randomLayout[r][c] = 2; // 30% strong
//                } else if (rand < 50) {
//                    randomLayout[r][c] = 3; // 10% extra strong
//                } else if (rand < 55) {
//                    randomLayout[r][c] = 9; // 5% unbreakable
//                } else {
//                    randomLayout[r][c] = 1; // 45% normal
//                }
//            }
//        }
//
//        return randomLayout;
//    }

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
                    case 10: // Red
                        brick = new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, "RED", 90);
                        break;
                    case 11: // Yellow
                        brick = new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, "YELLOW", 120);
                        break;
                    case 12: // Blue
                        brick = new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, "BLUE", 100);
                        break;
                    case 13: // Magenta
                        brick = new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, "MAGENTA", 110);
                        break;
                    case 14: // Lime
                        brick = new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, "LIME", 80);
                        break;
                    case 15: // White
                        brick = new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, "WHITE", 50);
                        break;
                    case 16: // Orange
                        brick = new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, "ORANGE", 60);
                        break;
                    case 17: // Cyan
                        brick = new ColoredBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT, "CYAN", 70);
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
