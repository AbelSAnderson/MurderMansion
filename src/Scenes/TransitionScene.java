package Scenes;

import Objects.State;
import Panes.TransitionPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class TransitionScene extends Scene {
    public TransitionScene(State state) {
        super(new TransitionPane(state), state.getScreenWidth()/3 + 50, state.getScreenHeight()/3 + 50);
        getStylesheets().add("CharacterSelectionStyle.css");
        setFill(Color.TRANSPARENT);
    }
}