package org.OOPproject.ArkanoidFX.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardManager {
    private static LeaderboardManager instance;
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    private List<LeaderboardEntry> entries;

    private LeaderboardManager() {
        entries = new ArrayList<>();
        loadLeaderboard();
    }

    public static LeaderboardManager getInstance() {
        if (instance == null) {
            instance = new LeaderboardManager();
        }
        return instance;
    }

    public void addEntry(String playerName, int score, int level) {
        LeaderboardEntry entry = new LeaderboardEntry(playerName, score, level);
        entries.add(entry);
        sortEntries();
        saveLeaderboard();
    }

    private void sortEntries() {
        // Sort by score (descending), then by level (descending)
        Collections.sort(entries, (e1, e2) -> {
            if (e1.getScore() != e2.getScore()) {
                return Integer.compare(e2.getScore(), e1.getScore());
            }
            return Integer.compare(e2.getLevel(), e1.getLevel());
        });
    }

    private void saveLeaderboard() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEADERBOARD_FILE))) {
            for (LeaderboardEntry entry : entries) {
                writer.write(entry.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving leaderboard: " + e.getMessage());
        }
    }

    private void loadLeaderboard() {
        File file = new File(LEADERBOARD_FILE);
        if (!file.exists()) {
            return;
        }

        entries.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(LEADERBOARD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LeaderboardEntry entry = LeaderboardEntry.fromString(line);
                if (entry != null) {
                    entries.add(entry);
                }
            }
            sortEntries();
        } catch (IOException e) {
            System.err.println("Error loading leaderboard: " + e.getMessage());
        }
    }

    public List<LeaderboardEntry> getTopEntries(int count) {
        int limit = Math.min(count, entries.size());
        return new ArrayList<>(entries.subList(0, limit));
    }

    public List<LeaderboardEntry> getAllEntries() {
        return new ArrayList<>(entries);
    }

    public static class LeaderboardEntry {
        private String playerName;
        private int score;
        private int level;

        public LeaderboardEntry(String playerName, int score, int level) {
            this.playerName = playerName;
            this.score = score;
            this.level = level;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        public int getLevel() {
            return level;
        }

        @Override
        public String toString() {
            return playerName + "," + score + "," + level;
        }

        public static LeaderboardEntry fromString(String line) {
            try {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    int level = Integer.parseInt(parts[2].trim());
                    return new LeaderboardEntry(name, score, level);
                }
            } catch (Exception e) {
                System.err.println("Error parsing leaderboard entry: " + line);
            }
            return null;
        }
    }
}
