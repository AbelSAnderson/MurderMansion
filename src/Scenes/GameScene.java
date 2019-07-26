package Scenes;

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
		super(new MainGamePane(gameState), gameState.getScreenWidth(), gameState.getScreenHeight());
		getStylesheets().add("MainGameStyle.css");
	}
}