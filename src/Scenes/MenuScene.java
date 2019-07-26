package Scenes;

import Objects.GameState;
import Panes.MenuPane;
import javafx.scene.Scene;

/**
 * Author - Abel
 * Created - 01/03/2019
 * Note - Created file with MenuPane
 * Description - Scene for MenuPane
 */

public class MenuScene extends Scene {

    public MenuScene(GameState gameState) {
        super(new MenuPane(gameState), gameState.getScreenWidth(), gameState.getScreenHeight());
        getStylesheets().add("MenuStyle.css");
    }
}