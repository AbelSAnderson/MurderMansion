package Scenes;

import Logic.Constants;
import Panes.EndGamePane;
import javafx.scene.Scene;

public class EndGameScene extends Scene {
	
	public EndGameScene() {
		super(new EndGamePane(), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		getStylesheets().add("CharacterSelectionStyle.css");
	}

}
