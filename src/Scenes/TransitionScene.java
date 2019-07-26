package Scenes;

import Objects.GameState;
import Panes.TransitionPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import static Logic.Constants.SCREEN_HEIGHT;
import static Logic.Constants.SCREEN_WIDTH;

public class TransitionScene extends Scene {
    public TransitionScene(GameState gameState) {
        super(new TransitionPane(gameState), SCREEN_WIDTH/3 + 50, SCREEN_HEIGHT/3 + 50);
        this.getStylesheets().add("CharacterSelectionStyle.css");
        this.setFill(Color.TRANSPARENT);
    }
}