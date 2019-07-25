package Logic;

import Panes.GameBoardPanes.GuessSheetPane;
import Panes.GameBoardPanes.InventoryPane;
import Scenes.TransitionScene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static Logic.Constants.*;
import static Panes.GameBoardPanes.DialoguePane.dialogue;
import static Panes.GameBoardPanes.GuessesPane.*;
import static Panes.GameBoardPanes.MovementPane.*;
import static Panes.GameBoardPanes.Score.scoreNumber;
import static Panes.MainGamePane.*;

public class Turn {

	private static Stage transitionStage;

	/**
	 * Disables or Enables Accusation and Suggestions based on <i>isDisabled</i>.
	 * @param isDisabled Boolean for enabling or disabling Suggestions and Accusations.
	 */
	public static void disableGuessClicks(Boolean isDisabled) {
		accusation.setDisable(isDisabled);
		suggestion.setDisable(isDisabled);

		for (ComboBox comboBox : comboBoxes) {
			comboBox.setDisable(isDisabled);
		}
	}

	/**Ends Player's Turn*/
	public static void endTurn() {
		CURRENT_PLAYER().setScore(CURRENT_PLAYER().getScore() - 18);
		
		Constants.GAMEBOARD_OBJECTS[Movement.blockedY][Movement.blockedX].setTraversable(true);
		
		CURRENT_PLAYER().setRollsLeft(0);
		
		PLAYERS.add(PLAYERS.remove(0));

		disableGuessClicks(true);
		
		scoreNumber.setText("" + CURRENT_PLAYER().getScore());
		rollsText.setText("0");

		switchPlayerUI();
		displayTransition();
	}

	/**Switches the Player's UI when Changing Players.*/
	private static void switchPlayerUI() {
		leftContainer.getChildren().remove(guessSheet);
		guessSheet = new GuessSheetPane(true);
		
		leftContainer.getChildren().remove(inventory);
		inventory = new InventoryPane(true);
		
		leftContainer.getChildren().addAll(guessSheet, inventory);
	}

	/**Displays the Transition Stage When Changing Players.*/
	public static void displayTransition() {
		dialogue.clear();
		disableButtons(true);

		transitionStage = new Stage();
		transitionStage.initStyle(StageStyle.TRANSPARENT);
		transitionStage.setAlwaysOnTop(true);
		transitionStage.setScene(new TransitionScene());
		transitionStage.show();

		transitionStage.setOnCloseRequest(e -> startTurn());
	}

	/**Starts the Next Player's Turn.*/
	public static void startTurn() {
		leftContainer.getChildren().remove(guessSheet);
		guessSheet = new GuessSheetPane(false);

		leftContainer.getChildren().remove(inventory);
		inventory = new InventoryPane(false);
		
		leftContainer.getChildren().addAll(guessSheet,inventory);

		//Place the piece at the room entrance if the players current moveX and moveY's room number is not -1 (anything not -1 is a room)
		if(GAMEBOARD_OBJECTS[CURRENT_LOCATION().getMoveCharacterY()][CURRENT_LOCATION().getMoveCharacterX()].getRoomNum() != -1) {
			Movement.placeEntranceRoom();
		}

		transitionStage.close();
		disableButtons(false);
		setButtons();
	}
}