package Scenes;

import Objects.GameState;
import Panes.InstructionsPane;
import javafx.scene.Scene;

public class InstructionsScene extends Scene {
    public InstructionsScene(GameState gameState) {
        super(new InstructionsPane(gameState), gameState.getScreenWidth(), gameState.getScreenHeight());
        getStylesheets().add("CharacterSelectionStyle.css");
    }
}