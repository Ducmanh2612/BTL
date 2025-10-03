package renderer;

import gameobject.brick.*;
import gameobject.movable.*;
import gameobject.powerup.*;
import gameobject.GameObject;


/**
 * Handles rendering the game to the terminal/console.
 * Uses a character buffer for drawing.
 */
public class Renderer {
    private char[][] buffer;
    private int width;
    private int height;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
        this.buffer = new char[height][width];
        clear();
    }

    public void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                buffer[y][x] = ' ';
            }
        }
    }

    public void drawChar(int x, int y, char c) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            buffer[y][x] = c;
        }
    }

    public void drawRect(int x, int y, int w, int h, char c) {
        for (int dy = 0; dy < h; dy++) {
            for (int dx = 0; dx < w; dx++) {
                drawChar(x + dx, y + dy, c);
            }
        }
    }

    public void drawText(int x, int y, String text) {
        for (int i = 0; i < text.length(); i++) {
            drawChar(x + i, y, text.charAt(i));
        }
    }

    public void display() {
        // Clear console (works on most terminals)
        clearConsole();

        // Draw top border
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        // Draw buffer with side borders
        for (int y = 0; y < height; y++) {
            System.out.print("|");
            for (int x = 0; x < width; x++) {
                System.out.print(buffer[y][x]);
            }
            System.out.println("|");
        }

        // Draw bottom border
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    private void clearConsole() {
        try {
            // Try to clear console in a cross-platform way
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // For Windows, print escape sequence or use newlines
                // ANSI escape codes work in Windows 10+ Command Prompt and PowerShell
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else {
                // For Unix/Linux/Mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: print many newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public void draw(GameObject obj) {
        // Generic draw method for GameObject
        drawRect(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight(), '*');
    }
}
