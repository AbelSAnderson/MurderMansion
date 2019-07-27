package Scenes;

import Objects.State;
import Panes.HighScorePane;
import javafx.scene.Scene;

/**
 * Author - Hasan
 * Created - 14/03/2019
 * Description - Scene for HighScorePane
 */

public class HighScoreScene extends Scene{
	public HighScoreScene(State state) {
		super(new HighScorePane(state), state.getScreenWidth(), state.getScreenHeight());
		getStylesheets().add("CharacterSelectionStyle.css");
	}
}