package Scenes;

import Logic.Constants;
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
		super(new HighScorePane(gameState), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		this.getStylesheets().add("CharacterSelectionStyle.css");
	}
}