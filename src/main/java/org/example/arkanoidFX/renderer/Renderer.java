package org.example.arkanoidFX.renderer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.arkanoidFX.gameobject.GameObject;
import org.example.arkanoidFX.gameobject.brick.Brick;
import org.example.arkanoidFX.gameobject.movable.Ball;
import org.example.arkanoidFX.gameobject.movable.Paddle;
import org.example.arkanoidFX.gameobject.powerup.PowerUp;

import java.io.IOException;
import java.util.List;

public class Renderer {
    private Group gameRoot;
    private Scene gameScene;
    private Scene uiScene;
    private Stage primaryStage;
    private int width;
    private int height;

    public Renderer(int width, int height, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.width = width;
        this.height = height;
        gameRoot = new Group();
        gameScene = new Scene(gameRoot, width, height, Color.BLACK);
        primaryStage.setTitle("Arkanoid!");
    }

    public void switchToGameScene() {
        if (primaryStage.getScene() != gameScene) {
            primaryStage.setScene(gameScene);
            primaryStage.show();
        }
    }

    public void showFXML(String FXMLFilePath, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLFilePath));
            loader.setController(controller);
            uiScene = new Scene(loader.load());
            primaryStage.setScene(uiScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderGame(List<Brick> bricks, Paddle paddle, Ball ball, List<PowerUp> powerUps) {
        gameRoot.getChildren().clear();

        // Render bricks
        for (Brick brick : bricks) {
            drawGameObject(brick);
        }

        // Render power-ups
        for (PowerUp powerUp : powerUps) {
            drawGameObject(powerUp);
        }

        // Render paddle and ball
        drawGameObject(paddle);
        drawGameObject(ball);
    }

    public void renderUI(int score, int lives, int level, boolean isPaused) {
        drawText("Score: " + score, 10, 20);
        drawText("Lives: " + lives, width - 100, 20);
        drawText("Level: " + level, width / 2 - 30, 20);

        if (isPaused) {
            drawText("PAUSED - Press P to Resume", width / 2 - 120, height / 2);
        }
    }

    private void drawGameObject(GameObject obj) {
        ImageView texture = obj.getTexture();
        if (texture != null) {
            texture.setX(obj.getX());
            texture.setY(obj.getY());
            texture.setFitWidth(obj.getWidth());
            texture.setFitHeight(obj.getHeight());
            gameRoot.getChildren().add(texture);
        }
    }

    private void drawText(String text, int x, int y, Color color) {
        Text txt = new Text(x, y, text);
        txt.setFill(color);
        txt.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        gameRoot.getChildren().add(txt);
    }

    private void drawText(String text, int x, int y) {
        drawText(text, x, y, Color.WHITE);
    }

    public Scene getGameScene() {
        return gameScene;
    }

}
