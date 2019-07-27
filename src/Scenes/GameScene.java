package Scenes;

import Objects.State;
import Panes.MainGamePane;
import javafx.scene.Scene;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Note - Created file with MainGamePane class
 * Description - Scene for Main Game screen
 */

public class GameScene extends Scene{
	
	public GameScene(State state) {
		super(new MainGamePane(state), state.getScreenWidth(), state.getScreenHeight());
		getStylesheets().add("MainGameStyle.css");
	}
}