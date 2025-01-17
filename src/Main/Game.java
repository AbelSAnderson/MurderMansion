package Main;

import Objects.State;
import Scenes.IntroScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Author - Abel
 * Created - 20/02/2019
 * Note - Created file with sample buttons, styling, and fonts
 * Description - Main file for game. Everything will be run and controlled from here.
 * <p>
 * Changed - 01/03/2019 - Abel
 * Description - Moved bulk of code to MenuPane and refactored to work with MenuScene.
 * <p>
 * Changed - 21/03/2019 - Abel
 * Description - Added createGameBoardObjects function
 * <p>
 * Changed - 28/03/2019 - Cordelle
 * Description - Changed initial mainStage.setScene from MenuScene to IntroScene
 */

// IMPORTANT: FOR TESTING MODE GO TO MovementPane AND CHANGE 1 LINE OF CODE                             

public class Game extends Application {
    @Override
    public void start(Stage primaryStage) {

        State state = new State(primaryStage);

        primaryStage.setScene(new IntroScene(state));
        primaryStage.setTitle("Murder Mansion");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}