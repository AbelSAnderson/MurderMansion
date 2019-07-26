package Scenes;

import Objects.GameState;
import Panes.PausePane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class PauseScene extends Scene {
    public PauseScene(GameState gameState) {
        super(new PausePane(gameState), gameState.getScreenWidth()/1.5, gameState.getScreenHeight()/3);
        setFill(Color.TRANSPARENT);
        getStylesheets().add("CharacterSelectionStyle.css");
    }
}