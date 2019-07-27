package Scenes;

import Objects.State;
import Panes.InstructionsPane;
import javafx.scene.Scene;

public class InstructionsScene extends Scene {
    public InstructionsScene(State state) {
        super(new InstructionsPane(state), state.getScreenWidth(), state.getScreenHeight());
        getStylesheets().add("CharacterSelectionStyle.css");
    }
}