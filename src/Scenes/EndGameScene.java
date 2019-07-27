package Scenes;

import Objects.State;
import Panes.EndGamePane;
import javafx.scene.Scene;

public class EndGameScene extends Scene {
	public EndGameScene(State state) {
		super(new EndGamePane(state), state.getScreenWidth(), state.getScreenHeight());
		getStylesheets().add("CharacterSelectionStyle.css");
	}
}