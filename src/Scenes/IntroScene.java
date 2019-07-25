package Scenes;

import Logic.Constants;
import Panes.IntroPane;
import javafx.scene.Scene;

/**
 * Author - Cordelle
 * Created - 28/03/2019
 * Note - Created file with IntroPane class
 * Description - Scene for Introduction screen
 */

public class IntroScene extends Scene {
    public IntroScene() {
    	super(new IntroPane(), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
}