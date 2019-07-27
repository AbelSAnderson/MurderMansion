package Scenes;

import Objects.State;
import Panes.CharacterSelectionPane;
import javafx.scene.Scene;

/**
 * Author - Hasan
 * Created - 14/03/2019
 * Note - Created file with CharacterSelectionPane class
 * Description - Scene for Character Selection
 */

public class CharacterSelectionScene extends Scene{
	public CharacterSelectionScene(State state) {
		super(new CharacterSelectionPane(state), state.getScreenWidth(), state.getScreenHeight());
		getStylesheets().add("CharacterSelectionStyle.css");
	}
}