package org.OOPproject.ArkanoidFX.model;

import javafx.scene.media.AudioClip;
import org.OOPproject.ArkanoidFX.view.AssetManager;

public class SoundManager {
    private static SoundManager instance;
    private final AssetManager assetManager;

    private SoundManager() {
        assetManager = AssetManager.getInstance();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void playSound(String soundName) {
        try {
            AudioClip clip = assetManager.getAudioClip(soundName);
            clip.play();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + soundName + " - " + e.getMessage());
        }
    }
}
