package Scenes;

import Objects.GameState;
import Panes.TransitionPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class TransitionScene extends Scene {
    public TransitionScene(GameState gameState) {
        super(new TransitionPane(gameState), gameState.getScreenWidth()/3 + 50, gameState.getScreenHeight()/3 + 50);
        getStylesheets().add("CharacterSelectionStyle.css");
        setFill(Color.TRANSPARENT);
    }
}