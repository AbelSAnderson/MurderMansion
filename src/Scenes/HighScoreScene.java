package Scenes;

import Objects.GameState;
import Panes.HighScorePane;
import javafx.scene.Scene;

/**
 * Author - Hasan
 * Created - 14/03/2019
 * Description - Scene for HighScorePane
 */

public class HighScoreScene extends Scene{
	public HighScoreScene(GameState gameState) {
		super(new HighScorePane(gameState), gameState.getScreenWidth(), gameState.getScreenHeight());
		getStylesheets().add("CharacterSelectionStyle.css");
	}
}