package Logic;

import Objects.GameState;
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
	public static void endTurn(GameState gameState) {
		CURRENT_PLAYER().setScore(CURRENT_PLAYER().getScore() - 18);
		
		Constants.GAMEBOARD_OBJECTS[Movement.blockedY][Movement.blockedX].setTraversable(true);
		
		CURRENT_PLAYER().setRollsLeft(0);
		
		PLAYERS.add(PLAYERS.remove(0));

		disableGuessClicks(true);
		
		scoreNumber.setText("" + CURRENT_PLAYER().getScore());
		rollsText.setText("0");

		switchPlayerUI(gameState);
		displayTransition(gameState);
	}

	/**Switches the Player's UI when Changing Players.*/
	private static void switchPlayerUI(GameState gameState) {
		leftContainer.getChildren().remove(guessSheet);
		guessSheet = new GuessSheetPane(gameState, true);

		leftContainer.getChildren().remove(inventory);
		inventory = new InventoryPane(gameState, true);

		leftContainer.getChildren().addAll(guessSheet, inventory);
	}

	/**Displays the Transition Stage When Changing Players.*/
	public static void displayTransition(GameState gameState) {
		dialogue.clear();
		disableButtons(true);

		transitionStage = new Stage();
		transitionStage.initStyle(StageStyle.TRANSPARENT);
		transitionStage.setAlwaysOnTop(true);
		transitionStage.setScene(new TransitionScene(gameState));
		transitionStage.show();

		transitionStage.setOnCloseRequest(e -> startTurn(gameState));
	}

	/**Starts the Next Player's Turn.*/
	public static void startTurn(GameState gameState) {
		leftContainer.getChildren().remove(guessSheet);
		guessSheet = new GuessSheetPane(gameState, false);

		leftContainer.getChildren().remove(inventory);
		inventory = new InventoryPane(gameState, false);

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