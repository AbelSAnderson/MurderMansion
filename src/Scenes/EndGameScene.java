package Scenes;

import Objects.GameState;
import Panes.EndGamePane;
import javafx.scene.Scene;

public class EndGameScene extends Scene {
	public EndGameScene(GameState gameState) {
		super(new EndGamePane(gameState), gameState.getScreenWidth(), gameState.getScreenHeight());
		getStylesheets().add("CharacterSelectionStyle.css");
	}
}