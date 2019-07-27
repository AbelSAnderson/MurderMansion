package Main;

import Objects.*;
import Panes.GameBoardPanes.GuessSheetPane;
import Panes.GameBoardPanes.InventoryPane;

import static Panes.GameBoardPanes.DialoguePane.pauseStage;
import static Panes.GameBoardPanes.GuessesPane.comboBoxes;
import static Panes.MainGamePane.*;

/**
 * Author - Abel
 * Created - 01/03/2019
 * Note - Created file and Added Default Screen Sizes
 * Description - Use this file for any constant values that appear often. Please separate and organize them as needed
 *
 * Changed - 12/03/2019 - Abel
 * Description - Built the 2D Array for the Game Board #Thanks Cordelle for the idea of marking the doors with distinct numbers
 *
 * Changed - 14/03/2019 - Abel
 * Description - Added Fonts Section and Changed Default Screen Size
 * 
 * Changed - 15/03/2019 - Hasan
 * Description - Added 3 arrays with the character, weapon, and room objects
 *
 * Changed - 21/03/2019 - Abel
 * Description - Added function to change the 2D-array into objects
 * 
 * Changed - 28/03/2019 - Cordelle
 * Description - Added fonts for the IntroPane
 *
 * Changed - 29/03/2019 - Abel
 * Description - Added Several Functions to help readability
 *
 * Changed - 30/03/2019 - Abel
 * Description - Changed PLAYERS into an ArrayList
 *
 * Changed - 06/04/2019 - Cordelle
 * Description - Added CREDITS_SUBTITLE_FONT for the CreditsPane
 */

public class Constants {

    /**Resets all Game Variables after winning or returning to the main menu.*/
    public static void resetGame(State state) {
        leftContainer.getChildren().remove(guessSheet);
        guessSheet = new GuessSheetPane(state, true);

        leftContainer.getChildren().remove(inventory);
        inventory = new InventoryPane(state, true);

        leftContainer.getChildren().addAll(guessSheet,inventory);

        state.getPlayers().clear();
        comboBoxes.clear();
        state.getBackgroundMusic().stop();
        pauseStage.close();
    }
}