package Scenes;

import Logic.Constants;
import Objects.GameState;
import Panes.IntroPane;
import javafx.scene.Scene;

/**
 * Author - Cordelle
 * Created - 28/03/2019
 * Note - Created file with IntroPane class
 * Description - Scene for Introduction screen
 */

public class IntroScene extends Scene {
    public IntroScene(GameState gameState) {
    	super(new IntroPane(gameState), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
}