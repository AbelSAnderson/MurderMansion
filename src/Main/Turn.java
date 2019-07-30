package Main;

import Objects.GameState;
import Objects.State;
import Panes.GameBoardPanes.GuessSheetPane;
import Panes.GameBoardPanes.InventoryPane;
import Scenes.TransitionScene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	public static void endTurn(State state) {

		GameState gameState = state.getCurrentGame();

		gameState.currentPlayer().setScore(gameState.currentPlayer().getScore() - 18);

		gameState.getGameBoard()[Movement.blockedY][Movement.blockedX].setTraversable(true);

		gameState.currentPlayer().setRollsLeft(0);

		gameState.setCurrentPlayer(gameState.getCurrentPlayer() + 1);
		if(gameState.getPlayers().length == gameState.getCurrentPlayer()) gameState.setCurrentPlayer(0);

		disableGuessClicks(true);
		
		scoreNumber.setText("" + gameState.currentPlayer().getScore());
		rollsText.setText("0");

		switchPlayerUI(state);
		displayTransition(state);
	}

	/**Switches the Player's UI when Changing Players.*/
	private static void switchPlayerUI(State state) {
		leftContainer.getChildren().remove(guessSheet);
		guessSheet = new GuessSheetPane(state, true);

		leftContainer.getChildren().remove(inventory);
		inventory = new InventoryPane(state, true);

		leftContainer.getChildren().addAll(guessSheet, inventory);
	}

	/**Displays the Transition Stage When Changing Players.*/
	public static void displayTransition(State state) {
		dialogue.clear();
		disableButtons(true);

		transitionStage = new Stage();
		transitionStage.initStyle(StageStyle.TRANSPARENT);
		transitionStage.setAlwaysOnTop(true);
		transitionStage.setScene(new TransitionScene(state));
		transitionStage.show();

		state.getMainStage().setOnCloseRequest(e -> transitionStage.close());
		transitionStage.setOnCloseRequest(e -> startTurn(state));
	}

	/**Starts the Next Player's Turn.*/
	public static void startTurn(State state) {

		GameState gameState = state.getCurrentGame();

		leftContainer.getChildren().remove(guessSheet);
		guessSheet = new GuessSheetPane(state, false);

		leftContainer.getChildren().remove(inventory);
		inventory = new InventoryPane(state, false);

		leftContainer.getChildren().addAll(guessSheet,inventory);

		//Place the piece at the room entrance if the players current moveX and moveY's room number is not -1 (anything not -1 is a room)
		if(gameState.getGameBoard()[gameState.currentLocation().getMoveCharacterY()][gameState.currentLocation().getMoveCharacterX()].getRoomNum() != -1) {
			Movement.placeEntranceRoom(gameState);
		}

		transitionStage.close();
		disableButtons(false);
		setButtons(gameState);
	}
}