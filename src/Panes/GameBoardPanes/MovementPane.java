package Panes.GameBoardPanes;

import Enums.Fonts;
import Objects.GameState;
import Objects.State;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Random;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Description - This file is for the movement navigation and dice roll
 *
 * Changed - 21/03/2019 - Abel
 * Description - Minimized setButtons into one function
 */

public class MovementPane extends VBox{
	
	private Button moveUp;
	private Button moveDown;
	private Button moveRight;
	private Button moveLeft;
	private Button endTurn;
	private Button rollDice;
	public Text rollsText;
	
	public MovementPane(State state) {

		GameState gameState = state.getCurrentGame();
	
		GridPane gridpane = new GridPane();
		
		HBox rollsHBox = new HBox(20);
		
		HBox rollsLeftHBox = new HBox();
		
		Text rollsLeftText = new Text("Moves left: ");
		rollsLeftText.setFont(Fonts.BLANK_FONT.FellCursive(getClass(), 40));
		rollsLeftText.setFill(Color.WHITE);
		
		rollsText = new Text("0");
		rollsText.setFill(Color.WHITE);
		rollsText.setFont(Fonts.NUMBER_FONT.getFont());
		
		rollsLeftHBox.setAlignment(Pos.CENTER);
		rollsLeftHBox.getChildren().addAll(rollsLeftText, rollsText);
						
		moveUp = new Button("Up");
		moveDown = new Button("Down");
		moveRight = new Button("Right");
		moveLeft = new Button("Left");
		rollDice = new Button("Roll Dice");
		endTurn = new Button("End Turn");
								
		rollsHBox.setAlignment(Pos.BOTTOM_CENTER);
		rollsHBox.getChildren().addAll(rollDice, gridpane);
		
		setAlignment(Pos.CENTER);
		setSpacing(20);
		getChildren().addAll(rollsLeftHBox, rollsHBox, endTurn);
				
		gridpane.add(moveUp, 1, 0);
		gridpane.add(moveDown, 1, 1);
		gridpane.add(moveRight, 2, 1);
		gridpane.add(moveLeft, 0, 1);
		
		
		rollDice.setOnAction(e -> {
			MediaPlayer rollSound = new MediaPlayer(new Media(new File("src/Resources/Audio/diceroll.wav").toURI().toString()));
			rollSound.play();
			
			int roll = rollDice();
			
			gameState.currentPlayer().setRollsLeft(roll);
			gameState.getGamePane().dialoguePane.dialogue.appendText("You rolled a " + gameState.currentPlayer().getRollsLeft() + "\n");
			
			rollsText.setText("" + gameState.currentPlayer().getRollsLeft());
			rollDice.setDisable(true);
		});
		
		rollDice.setMinSize(120, 40);
		endTurn.setMinSize(120, 40);
			
		moveUp.setMinSize(80, 40);
		moveDown.setMinSize(80, 40);
		moveLeft.setMinSize(80, 40);
		moveRight.setMinSize(80, 40);
		
		moveUp.setOnAction(e -> gameState.getGamePane().boardPane.movement(state, 0));

		moveDown.setOnAction(e -> gameState.getGamePane().boardPane.movement(state, 1));
		
		moveLeft.setOnAction(e -> gameState.getGamePane().boardPane.movement(state, 2));
		
		moveRight.setOnAction(e -> gameState.getGamePane().boardPane.movement(state, 3));
		
		endTurn.setOnAction(e -> gameState.endTurn(state));
		
		gridpane.setVgap(7);
		gridpane.setHgap(7);
	}

	/**
	 * Rolls two dice.
	 * @return Random int between 2 and 12.
	 */
	private int rollDice() {
		return new Random().nextInt(11) + 2;
	}

	/**Checks whether you can move and disables and enables the movement buttons accordingly.*/
	public void setButtons(GameState gameState) {
		moveUp.setDisable(!gameState.getGameBoard()[gameState.playerY() - 1][gameState.playerX()].isTraversable());
		moveDown.setDisable(!gameState.getGameBoard()[gameState.playerY() + 1][gameState.playerX()].isTraversable());
		moveLeft.setDisable(!gameState.getGameBoard()[gameState.playerY()][gameState.playerX() - 1].isTraversable());
		moveRight.setDisable(!gameState.getGameBoard()[gameState.playerY()][gameState.playerX() + 1].isTraversable());
	}

	/**
	 * Disables or enables the movement buttons based on <i>isDisabled</i>
	 * @param isDisabled Boolean for enabling or disabling buttons.
	 */
	public void disableButtons(boolean isDisabled) {
		moveUp.setDisable(isDisabled);
		moveDown.setDisable(isDisabled);
		moveLeft.setDisable(isDisabled);
		moveRight.setDisable(isDisabled);
		endTurn.setDisable(isDisabled);
		rollDice.setDisable(isDisabled);
	}
}