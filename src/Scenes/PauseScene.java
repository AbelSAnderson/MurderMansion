package Scenes;

import Objects.GameState;
import Panes.PausePane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import static Logic.Constants.SCREEN_HEIGHT;
import static Logic.Constants.SCREEN_WIDTH;

public class PauseScene extends Scene {
    public PauseScene(GameState gameState) {
        super(new PausePane(gameState), SCREEN_WIDTH/1.5, SCREEN_HEIGHT/3);
        this.setFill(Color.TRANSPARENT);
        this.getStylesheets().add("CharacterSelectionStyle.css");
    }
}