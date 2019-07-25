package Panes.GameBoardPanes;

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

import static Logic.Constants.*;
import static Logic.Turn.endTurn;
import static Panes.GameBoardPanes.BoardPane.movement;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Description - This file is for the movement navigation and dice roll
 *
 * Changed - 21/03/2019 - Abel
 * Description - Minimized setButtons into one function
 */

public class MovementPane extends VBox{
	
	private static Button moveUp;
	private static Button moveDown;
	private static Button moveRight;
	private static Button moveLeft;
	private static Button endTurn;
	private static Button rollDice;
	public static Text rollsText;
	
	public MovementPane() {
	
		GridPane gridpane = new GridPane();
		
		HBox rollsHBox = new HBox(20);
		
		HBox rollsLeftHBox = new HBox();
		
		Text rollsLeftText = new Text("Moves left: ");
		rollsLeftText.setFont(FellCursive(getClass(), 40));
		rollsLeftText.setFill(Color.WHITE);
		
		rollsText = new Text("0");
		rollsText.setFill(Color.WHITE);
		rollsText.setFont(NUMBER_FONT);
		
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
			
			CURRENT_PLAYER().setRollsLeft(roll);
			DialoguePane.dialogue.appendText("You rolled a " + CURRENT_PLAYER().getRollsLeft() + "\n");
			
			rollsText.setText("" + CURRENT_PLAYER().getRollsLeft());
			rollDice.setDisable(true);
		});
		
		rollDice.setMinSize(120, 40);
		endTurn.setMinSize(120, 40);
			
		moveUp.setMinSize(80, 40);
		moveDown.setMinSize(80, 40);
		moveLeft.setMinSize(80, 40);
		moveRight.setMinSize(80, 40);
		
		moveUp.setOnAction(e -> movement(0));
		
		moveDown.setOnAction(e -> movement(1));
		
		moveLeft.setOnAction(e -> movement(2));
		
		moveRight.setOnAction(e -> movement(3));
		
		endTurn.setOnAction(e -> endTurn());
		
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
	public static void setButtons() {
		moveUp.setDisable(!GAMEBOARD_OBJECTS[Y() - 1][X()].isTraversable());
		moveDown.setDisable(!GAMEBOARD_OBJECTS[Y() + 1][X()].isTraversable());
		moveLeft.setDisable(!GAMEBOARD_OBJECTS[Y()][X() - 1].isTraversable());
		moveRight.setDisable(!GAMEBOARD_OBJECTS[Y()][X() + 1].isTraversable());
	}

	/**
	 * Disables or enables the movement buttons based on <i>isDisabled</i>
	 * @param isDisabled Boolean for enabling or disabling buttons.
	 */
	public static void disableButtons(boolean isDisabled) {
		moveUp.setDisable(isDisabled);
		moveDown.setDisable(isDisabled);
		moveLeft.setDisable(isDisabled);
		moveRight.setDisable(isDisabled);
		endTurn.setDisable(isDisabled);
		rollDice.setDisable(isDisabled);
	}
}