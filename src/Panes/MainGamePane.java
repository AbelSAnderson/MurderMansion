package Panes;

import Enums.BackgroundColors;
import Objects.GameState;
import Panes.GameBoardPanes.*;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Description - This file is for the main game that gathers all the panes into a BorderPane
 */

public class MainGamePane extends BorderPane{
	
	public static VBox leftContainer;
	public static GuessSheetPane guessSheet;
	public static InventoryPane inventory;

	public MainGamePane(GameState gameState) {

		guessSheet = new GuessSheetPane(gameState,true);
		inventory = new InventoryPane(gameState, true);

		HBox board = new HBox();
		board.getChildren().add(new BoardPane(gameState));
		board.setAlignment(Pos.CENTER);
		
		leftContainer = new VBox(60);
		leftContainer.getChildren().addAll(new Score(), guessSheet, inventory);
		leftContainer.setAlignment(Pos.CENTER);
		
		VBox rightContainer = new VBox(30);
		rightContainer.getChildren().addAll(new DialoguePane(gameState), new GuessesPane(gameState), new MovementPane(gameState));
		
		setCenter(board);
		setLeft(leftContainer);
		setRight(rightContainer);
		
		setBackground(BackgroundColors.BACKGROUND_DARK.getBackground());
	}
}