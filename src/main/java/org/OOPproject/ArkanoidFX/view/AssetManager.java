package org.OOPproject.ArkanoidFX.view;

import javafx.scene.image.Image;
import org.OOPproject.ArkanoidFX.model.Bricks.BrickType;
import org.OOPproject.ArkanoidFX.model.PowerUps.PowerUpTypes;

import javafx.scene.media.AudioClip;
import org.OOPproject.ArkanoidFX.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import static org.OOPproject.ArkanoidFX.utils.Constants.PADDLE_HEIGHT;

public class AssetManager {
    private static AssetManager instance;
    
    // Cache for loaded images
    private Map<String, Image> imageCache;
    
    // Cache for loaded audio
    private Map<String, AudioClip> audioCache;

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
    private Image PowerupCMapImg;  // Catch
    private Image PowerupFMapImg;  // Expand (F for wide)
    private Image PowerupDMapImg;  // Duplicate balls
    private Image PowerupSMapImg;  // Slow down
    private Image PowerupLMapImg;  // Laser
    private Image PowerupBMapImg;  // Break through
    private Image PowerupPMapImg;  // Extra life (P for player)
    private Image PowerupShadowImg;
    
    // Blink effect sprite map
    private Image blinkMapImg;

    private AssetManager() {
        imageCache = new HashMap<>();
        audioCache = new HashMap<>();
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
            goldBlockImg = loadImage("goldBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            grayBlockImg = loadImage("grayBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            redBlockImg = loadImage("redBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            yellowBlockImg = loadImage("yellowBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            blueBlockImg = loadImage("blueBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            magentaBlockImg = loadImage("magentaBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            limeBlockImg = loadImage("limeBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            whiteBlockImg = loadImage("whiteBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            orangeBlockImg = loadImage("orangeBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            cyanBlockImg = loadImage("cyanBlock.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            blockShadowImg = loadImage("block_shadow.png", Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);

            ballImg = loadImage("ball.png", Constants.BALL_SIZE, Constants.BALL_SIZE);
            ballShadowImg = loadImage("ball_shadow.png", 12, 12);

            paddleStdImg = loadImage("paddle_std.png", Constants.PADDLE_DEFAULT_WIDTH, PADDLE_HEIGHT);
            paddleStdShadowImg = loadImage("paddle_std_shadow.png", Constants.PADDLE_DEFAULT_WIDTH, PADDLE_HEIGHT);
            paddleStdSpriteMapImg = loadImage("paddlemap_std.png", Constants.PADDLE_DEFAULT_WIDTH * 8, PADDLE_HEIGHT * 8);

            paddleWideImg = loadImage("paddle_wide.png", Constants.PADDLE_EXPANDED_WIDTH, PADDLE_HEIGHT);
            paddleWideShadowImg = loadImage("paddle_wide_shadow.png", Constants.PADDLE_EXPANDED_WIDTH, PADDLE_HEIGHT);
            paddleWideSpriteMapImg = loadImage("paddlemap_wide.png", Constants.PADDLE_EXPANDED_WIDTH * 8, PADDLE_HEIGHT * 8);


            PowerupCMapImg = loadImage("block_map_bonus_c.png", Constants.POWER_UP_MAP_WIDTH, Constants.POWER_UP_MAP_HEIGHT);
            PowerupFMapImg = loadImage("block_map_bonus_f.png", Constants.POWER_UP_MAP_WIDTH, Constants.POWER_UP_MAP_HEIGHT);
            PowerupDMapImg = loadImage("block_map_bonus_d.png", Constants.POWER_UP_MAP_WIDTH, Constants.POWER_UP_MAP_HEIGHT);
            PowerupSMapImg = loadImage("block_map_bonus_s.png", Constants.POWER_UP_MAP_WIDTH, Constants.POWER_UP_MAP_HEIGHT);
            PowerupLMapImg = loadImage("block_map_bonus_l.png", Constants.POWER_UP_MAP_WIDTH, Constants.POWER_UP_MAP_HEIGHT);
            PowerupBMapImg = loadImage("block_map_bonus_b.png", Constants.POWER_UP_MAP_WIDTH, Constants.POWER_UP_MAP_HEIGHT);
            PowerupPMapImg = loadImage("block_map_bonus_p.png", Constants.POWER_UP_MAP_WIDTH, Constants.POWER_UP_MAP_HEIGHT);
            PowerupShadowImg = loadImage("bonus_block_shadow.png", Constants.POWER_UP_SHADOW_WIDTH, Constants.POWER_UP_SHADOW_HEIGHT);

            blinkMapImg = loadImage("blink_map.png", 304, 60);

            // Load audio files
            loadMedia("brick_destroyed.wav");
            loadMedia("click.wav");
            loadMedia("brick_hit.wav");
            loadMedia("powerUp.wav");
            loadMedia("bounce.wav");
            loadMedia("game_over.wav");

        } catch (Exception e) {
            System.err.println("Error loading assets: " + e.getMessage());
        }
    }

    private Image loadImage(String filename, double width, double height) {
        try {
            String path = "/assets/textures/" + filename;
            Image img = new Image(getClass().getResourceAsStream(path), width, height, true, false);
            imageCache.put(filename, img);
            return img;
        } catch (Exception e) {
            System.err.println("Failed to load: " + filename);
            return null;
        }
    }

    private AudioClip loadMedia(String filename) {
        try {
            String path = "/assets/sfx/" + filename;
            AudioClip audioClip = new AudioClip(getClass().getResource(path).toExternalForm());
            audioCache.put(filename, audioClip);
            return audioClip;
        } catch (Exception e) {
            System.err.println("Failed to load media: " + filename);
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

    public Image getBrickImage(BrickType type) {
        if (type == null) return null;
        switch (type) {
            case BrickType.RUBY: return redBlockImg;
            case BrickType.YLLW: return yellowBlockImg;
            case BrickType.BLUE: return blueBlockImg;
            case BrickType.MGNT: return magentaBlockImg;
            case BrickType.LIME: return limeBlockImg;
            case BrickType.WHIT: return whiteBlockImg;
            case BrickType.ORNG: return orangeBlockImg;
            case BrickType.CYAN: return cyanBlockImg;
            case BrickType.GOLD: return goldBlockImg;
            case BrickType.GRAY: return grayBlockImg;
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
                return PowerupFMapImg;  // Wide paddle
            case FAST_BALL:
                return PowerupSMapImg;
            case MULTI_BALL:
                return PowerupDMapImg;     // Duplicate balls
            case LASER_PADDLE:
                return PowerupLMapImg;         // Laser
            case SKIP_LEVEL:
                return PowerupBMapImg;         // Break through
            case EXTRA_LIFE:
                return PowerupPMapImg;          // Extra life
            default:
                return PowerupFMapImg;  // Default to expand
        }
    }
    
    public Image getPowerupShadowImg() { return PowerupShadowImg; }
    public Image getBlinkMapImg() { return blinkMapImg; }

    public AudioClip getAudioClip(String filename) {
        return audioCache.get(filename);
    }
}
