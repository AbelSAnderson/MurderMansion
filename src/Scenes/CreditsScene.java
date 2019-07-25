package Scenes;

import Logic.Constants;
import Panes.CreditsPane;
import javafx.scene.Scene;

/**
 * Author - Cordelle
 * Created - 04/04/2019
 * Note - Created file with CreditsPane class
 * Description - Scene for Exit/Credits screen
 */

public class CreditsScene extends Scene {
    public CreditsScene() {
    	super(new CreditsPane(), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
}