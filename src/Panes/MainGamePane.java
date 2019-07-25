package Panes;

import Panes.GameBoardPanes.*;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

import static Logic.Constants.BACKGROUND_DARK;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Description - This file is for the main game that gathers all the panes into a BorderPane
 */

public class MainGamePane extends BorderPane{
	
	public static VBox leftContainer;
	public static GuessSheetPane guessSheet = new GuessSheetPane(true);
	public static InventoryPane inventory = new InventoryPane(true);

	public MainGamePane() {
		
		HBox board = new HBox();
		board.getChildren().add(new BoardPane());
		board.setAlignment(Pos.CENTER);
		
		leftContainer = new VBox(60);
		leftContainer.getChildren().addAll(new Score(), guessSheet, inventory);
		leftContainer.setAlignment(Pos.CENTER);
		
		VBox rightContainer = new VBox(30);
		rightContainer.getChildren().addAll(new DialoguePane(), new GuessesPane(), new MovementPane());
		
		setCenter(board);
		setLeft(leftContainer);
		setRight(rightContainer);
		
		setBackground(BACKGROUND_DARK);
	}
}