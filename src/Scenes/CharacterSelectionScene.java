package Scenes;

import Logic.Constants;
import Objects.GameState;
import Panes.CharacterSelectionPane;
import javafx.scene.Scene;

/**
 * Author - Hasan
 * Created - 14/03/2019
 * Note - Created file with CharacterSelectionPane class
 * Description - Scene for Character Selection
 */

public class CharacterSelectionScene extends Scene{
	public CharacterSelectionScene(GameState gameState) {
		super(new CharacterSelectionPane(gameState), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		getStylesheets().add("CharacterSelectionStyle.css");
	}
}