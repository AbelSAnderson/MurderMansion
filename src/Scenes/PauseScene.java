package Scenes;

import Objects.State;
import Panes.PausePane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class PauseScene extends Scene {
    public PauseScene(State state) {
        super(new PausePane(state), state.getScreenWidth()/1.5, state.getScreenHeight()/3);
        setFill(Color.TRANSPARENT);
        getStylesheets().add("CharacterSelectionStyle.css");
    }
}