package Scenes;

import Logic.Constants;
import Panes.HighScorePane;
import javafx.scene.Scene;

/**
 * Author - Hasan
 * Created - 14/03/2019
 * Description - Scene for HighScorePane
 */

public class HighScoreScene extends Scene{
	public HighScoreScene() {
		super(new HighScorePane(), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		this.getStylesheets().add("CharacterSelectionStyle.css");
	}
}