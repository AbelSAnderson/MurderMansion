package Scenes;

import Logic.Constants;
import Objects.GameState;
import Panes.EndGamePane;
import javafx.scene.Scene;

public class EndGameScene extends Scene {
	public EndGameScene(GameState gameState) {
		super(new EndGamePane(gameState), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		getStylesheets().add("CharacterSelectionStyle.css");
	}
}