package Scenes;

import Objects.State;
import Panes.MenuPane;
import javafx.scene.Scene;

/**
 * Author - Abel
 * Created - 01/03/2019
 * Note - Created file with MenuPane
 * Description - Scene for MenuPane
 */

public class MenuScene extends Scene {

    public MenuScene(State state) {
        super(new MenuPane(state), state.getScreenWidth(), state.getScreenHeight());
        getStylesheets().add("MenuStyle.css");
    }
}