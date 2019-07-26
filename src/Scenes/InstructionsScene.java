package Scenes;

import Objects.GameState;
import Panes.InstructionsPane;
import javafx.scene.Scene;

import static Logic.Constants.SCREEN_HEIGHT;
import static Logic.Constants.SCREEN_WIDTH;

public class InstructionsScene extends Scene {
    public InstructionsScene(GameState gameState) {
        super(new InstructionsPane(gameState), SCREEN_WIDTH, SCREEN_HEIGHT);
        getStylesheets().add("CharacterSelectionStyle.css");
    }
}