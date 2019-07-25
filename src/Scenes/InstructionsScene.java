package Scenes;

import Panes.InstructionsPane;
import javafx.scene.Scene;

import static Logic.Constants.SCREEN_HEIGHT;
import static Logic.Constants.SCREEN_WIDTH;

public class InstructionsScene extends Scene {
    public InstructionsScene() {
        super(new InstructionsPane(), SCREEN_WIDTH, SCREEN_HEIGHT);
        this.getStylesheets().add("CharacterSelectionStyle.css");
    }
}