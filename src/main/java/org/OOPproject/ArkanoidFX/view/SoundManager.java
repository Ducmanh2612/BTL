package org.OOPproject.ArkanoidFX.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
    private static SoundManager instance;
    private AssetManager assetManager;
    private MediaPlayer currentMediaPlayer;

    private SoundManager() {
        assetManager = AssetManager.getInstance();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Play a sound effect once
     * @param soundName The name of the sound file (e.g., "game_start.wav")
     */
    public void playSound(String soundName) {
        try {
            Media media = assetManager.getMedia(soundName);
            if (media != null) {
                // Stop previous sound if still playing
                if (currentMediaPlayer != null) {
                    currentMediaPlayer.stop();
                    currentMediaPlayer.dispose();
                }

                currentMediaPlayer = new MediaPlayer(media);
                currentMediaPlayer.setOnEndOfMedia(() -> {
                    currentMediaPlayer.dispose();
                });
                currentMediaPlayer.play();
            } else {
                System.err.println("Sound not found: " + soundName);
            }
        } catch (Exception e) {
            System.err.println("Error playing sound: " + soundName + " - " + e.getMessage());
        }
    }

    /**
     * Stop any currently playing sound
     */
    public void stopCurrentSound() {
        if (currentMediaPlayer != null) {
            currentMediaPlayer.stop();
            currentMediaPlayer.dispose();
            currentMediaPlayer = null;
        }
    }
}

