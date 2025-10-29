package org.OOPproject.ArkanoidFX.view;

import javafx.scene.image.Image;
import org.OOPproject.ArkanoidFX.model.PowerUps.PowerUpTypes;
import org.OOPproject.ArkanoidFX.utils.newConstants;

import java.util.HashMap;
import java.util.Map;

import static org.OOPproject.ArkanoidFX.utils.Constants.PADDLE_HEIGHT;
import static org.OOPproject.ArkanoidFX.utils.Constants.PADDLE_WIDTH;

public class AssetManager {
    private static AssetManager instance;
    
    // Cache for loaded images
    private Map<String, Image> imageCache;
    
    // Background patterns for different levels
    private Image[] backgroundPatterns;
    
    // Brick images
    private Image goldBlockImg;
    private Image grayBlockImg;
    private Image redBlockImg;
    private Image yellowBlockImg;
    private Image blueBlockImg;
    private Image magentaBlockImg;
    private Image limeBlockImg;
    private Image whiteBlockImg;
    private Image orangeBlockImg;
    private Image cyanBlockImg;
    private Image blockShadowImg;
    
    // Ball images
    private Image ballImg;
    private Image ballShadowImg;
    
    // Paddle images
    private Image paddleStdImg;
    private Image paddleStdShadowImg;
    private Image paddleWideImg;
    private Image paddleWideShadowImg;
    private Image paddleStdSpriteMapImg;
    private Image paddleWideSpriteMapImg;

    // PowerUp sprite maps (animated)
    private Image bonusBlockCMapImg;  // Catch
    private Image bonusBlockFMapImg;  // Expand (F for wide)
    private Image bonusBlockDMapImg;  // Duplicate balls
    private Image bonusBlockSMapImg;  // Slow down
    private Image bonusBlockLMapImg;  // Laser
    private Image bonusBlockBMapImg;  // Break through
    private Image bonusBlockPMapImg;  // Extra life (P for player)
    private Image bonusBlockShadowImg;
    
    // Blink effect sprite map
    private Image blinkMapImg;

    private AssetManager() {
        imageCache = new HashMap<>();
        loadAllAssets();
    }

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    private void loadAllAssets() {
        try {
            // Load background patterns (4 different patterns for level variety)
            backgroundPatterns = new Image[4];
            backgroundPatterns[0] = loadImage("backgroundPattern_1.png", 68, 117);
            backgroundPatterns[1] = loadImage("backgroundPattern_2.png", 64, 64);
            backgroundPatterns[2] = loadImage("backgroundPattern_3.png", 64, 64);
            backgroundPatterns[3] = loadImage("backgroundPattern_4.png", 64, 64);
            
            // Load brick images (38x20 pixels each)
            goldBlockImg = loadImage("goldBlock.png", 38, 20);
            grayBlockImg = loadImage("grayBlock.png", 38, 20);
            redBlockImg = loadImage("redBlock.png", 38, 20);
            yellowBlockImg = loadImage("yellowBlock.png", 38, 20);
            blueBlockImg = loadImage("blueBlock.png", 38, 20);
            magentaBlockImg = loadImage("magentaBlock.png", 38, 20);
            limeBlockImg = loadImage("limeBlock.png", 38, 20);
            whiteBlockImg = loadImage("whiteBlock.png", 38, 20);
            orangeBlockImg = loadImage("orangeBlock.png", 38, 20);
            cyanBlockImg = loadImage("cyanBlock.png", 38, 20);
            blockShadowImg = loadImage("block_shadow.png", 38, 20);

            ballImg = loadImage("ball.png", 12, 12);
            ballShadowImg = loadImage("ball_shadow.png", 12, 12);

            //TODO: update this thing to use game constants for paddle sizes
            paddleStdImg = loadImage("paddle_std.png", 100, 22);
            paddleStdShadowImg = loadImage("paddle_std_shadow.png", 100, 22);
            paddleStdSpriteMapImg = loadImage("paddlemap_std.png", 80 * 8, 22 * 8);

            paddleWideImg = loadImage("paddle_wide.png", 121, 22);
            paddleWideShadowImg = loadImage("paddle_wide_shadow.png", 121, 22);
            paddleWideSpriteMapImg = loadImage("paddlemap_wide.png", 121 * 8, 22 * 8);
            

            bonusBlockCMapImg = loadImage("block_map_bonus_c.png", 190, 72);
            bonusBlockFMapImg = loadImage("block_map_bonus_f.png", 190, 72);
            bonusBlockDMapImg = loadImage("block_map_bonus_d.png", 190, 72);
            bonusBlockSMapImg = loadImage("block_map_bonus_s.png", 190, 72);
            bonusBlockLMapImg = loadImage("block_map_bonus_l.png", 190, 72);
            bonusBlockBMapImg = loadImage("block_map_bonus_b.png", 190, 72);
            bonusBlockPMapImg = loadImage("block_map_bonus_p.png", 190, 72);
            bonusBlockShadowImg = loadImage("bonus_block_shadow.png", 38, 18);

            blinkMapImg = loadImage("blink_map.png", 304, 60);

        } catch (Exception e) {
            System.err.println("Error loading assets: " + e.getMessage());
        }
    }

    private Image loadImage(String filename, double width, double height) {
        try {
            String path = "/textures/assets/" + filename;
            Image img = new Image(getClass().getResourceAsStream(path), width, height, true, false);
            imageCache.put(filename, img);
            return img;
        } catch (Exception e) {
            System.err.println("Failed to load: " + filename);
            return null;
        }
    }

    public Image getBackgroundPattern(int level) {
        if (backgroundPatterns == null || backgroundPatterns.length == 0) {
            return null;
        }
        int index = (level - 1) % backgroundPatterns.length;
        return backgroundPatterns[index];
    }

    public Image getBrickImage(newConstants.BlockType type) {
        if (type == null) return null;
        switch (type) {
            case newConstants.BlockType.RUBY: return redBlockImg;
            case newConstants.BlockType.YLLW: return yellowBlockImg;
            case newConstants.BlockType.BLUE: return blueBlockImg;
            case newConstants.BlockType.MGNT: return magentaBlockImg;
            case newConstants.BlockType.LIME: return limeBlockImg;
            case newConstants.BlockType.WHIT: return whiteBlockImg;
            case newConstants.BlockType.ORNG: return orangeBlockImg;
            case newConstants.BlockType.CYAN: return cyanBlockImg;
            case newConstants.BlockType.GOLD: return goldBlockImg;
            case newConstants.BlockType.GRAY: return grayBlockImg;
            default: return null;
        }
    }
    
    // Getters for all image assets
    public Image getBlockShadowImg() { return blockShadowImg; }
    public Image getBallImg() { return ballImg; }
    public Image getBallShadowImg() { return ballShadowImg; }
    public Image getPaddleStdImg() { return paddleStdImg; }
    public Image getPaddleStdShadowImg() { return paddleStdShadowImg; }
    public Image getPaddleStdSpriteMapImg() { return paddleStdSpriteMapImg; }
    public Image getPaddleWideImg() { return paddleWideImg; }
    public Image getPaddleWideShadowImg() { return paddleWideShadowImg; }
    public Image getPaddleWideSpriteMapImg() { return paddleWideSpriteMapImg; }

    public Image getPowerUpSpriteMap(PowerUpTypes powerUpType) {
        switch (powerUpType) {
            case EXPAND_PADDLE:
                return bonusBlockFMapImg;  // Wide paddle
            case FAST_BALL:
                return bonusBlockSMapImg;
            case MULTI_BALL:
                return bonusBlockDMapImg;     // Duplicate balls
            case LASER_PADDLE:
                return bonusBlockLMapImg;         // Laser
            case SKIP_LEVEL:
                return bonusBlockBMapImg;         // Break through
            case EXTRA_LIFE:
                return bonusBlockPMapImg;          // Extra life
            default:
                return bonusBlockFMapImg;  // Default to expand
        }
    }
    
    public Image getBonusBlockShadowImg() { return bonusBlockShadowImg; }
    public Image getBlinkMapImg() { return blinkMapImg; }
}
