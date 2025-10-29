package org.OOPproject.ArkanoidFX.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.OOPproject.ArkanoidFX.view.AssetManager;

import java.util.ArrayList;
import java.util.List;

public class SoundManager {
    private static SoundManager instance;
    private AssetManager assetManager;
    private List<MediaPlayer> activePlayers;

    private SoundManager() {
        assetManager = AssetManager.getInstance();
        activePlayers = new ArrayList<>();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void playSound(String soundName) {
        try {
            Media media = assetManager.getMedia(soundName);
            MediaPlayer player = new MediaPlayer(media);
            activePlayers.add(player);

            player.setOnEndOfMedia(() -> {
                player.dispose();
                activePlayers.remove(player);
            });

            player.play();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + soundName + " - " + e.getMessage());
        }
    }

    /**
     * Stop all currently playing sounds
     */
    public void stop() {
        for (MediaPlayer player : new ArrayList<>(activePlayers)) {
            if (player != null) {
                player.stop();
                player.dispose();
            }
        }
        activePlayers.clear();
    }
}

