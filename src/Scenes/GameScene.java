package Scenes;

import Logic.Constants;
import Objects.GameState;
import Panes.MainGamePane;
import javafx.scene.Scene;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Note - Created file with MainGamePane class
 * Description - Scene for Main Game screen
 */

public class GameScene extends Scene{
	
	public GameScene(GameState gameState) {
		super(new MainGamePane(gameState), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		getStylesheets().add("MainGameStyle.css");
	}
}