package Panes.GameBoardPanes;

import Enums.Cards;
import Enums.Fonts;
import Objects.Card;
import Objects.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Note - Temporary items were added that will be removed later
 * Description - This file is for the Guess Sheet
 * 
 * Changed - 30/03/2019 - Cordelle in consultation with Abel and Hasan (group-work Saturday)
 * Description - designed Guess Sheet
 * 
 * Changed - 04/04/2019 - Hasan 
 * Description - Made it more responsive with our player objects so we can save each players guess sheet
 * 
 */

public class GuessSheetPane extends HBox{
	
private int grid;

	public GuessSheetPane(GameState gameState, boolean isTransition) {
		
		// create array for type of category (same code as CreatePlayers)
		List[] categoryArray = {new ArrayList<>(Arrays.asList(Cards.CHARACTERS.getCards())), new ArrayList<>(Arrays.asList(Cards.WEAPONS.getCards())), new ArrayList<>(Arrays.asList(Cards.ROOMS.getCards()))};
		String[] categoryNames = {"Suspects", "Weapons", "Rooms"};
				
		GridPane[] guessGridPane = new GridPane[3];
		
		// set all the values using a for loop of the primary GridPane
		for (int indexList = 0; indexList < categoryNames.length; indexList++) {
			
			// populate the primary GridPane array with x3 category arrays
			
			// set titles for the GridPane
			Text categoryName = new Text(categoryNames[indexList]);
			categoryName.getStyleClass().add("sheetNames");
			guessGridPane[indexList] = new GridPane();
			guessGridPane[indexList].add(categoryName, 0, 0);
			
			HBox.setMargin(guessGridPane[indexList], new Insets(0, 0, 0, 10));
			guessGridPane[indexList].setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

			for (int indexItems = 0; indexItems < categoryArray[indexList].size(); indexItems++) {

				// add item names
				Text itemName = new Text(((Card) categoryArray[indexList].get(indexItems)).getName());
				itemName.getStyleClass().add("sheetCards");
				itemName.setFont(Fonts.BLANK_FONT.FellCursive(getClass(), 20));

				guessGridPane[indexList].add(itemName, 0, indexItems + 1);

				// add item images for the checkboxes
				ImageView temp;
				if(isTransition || gameState.currentPlayer().getGuessSheet().getCheckedBox()[indexList][indexItems] == 0) {
					temp=new ImageView(new Image("/Resources/Images/checkUnmark.jpg"));
				}
				else {
					temp=new ImageView(new Image("/Resources/Images/checkMark.jpg"));
				}
				guessGridPane[indexList].add(temp, 1, indexItems + 1);

			}
			guessGridPane[indexList].getStyleClass().add("gridpane");
			getChildren().add(guessGridPane[indexList]);
		}

		if(!isTransition) {
			grid = 0;
			for (int i = 0; i < getChildren().size(); i++) {
				for (Node node : guessGridPane[i].getChildren()) {
					(node).setOnMousePressed(e -> {
						try {
							if (node.getParent() == guessGridPane[0]) {
								grid = 0;
							} else if (node.getParent() == guessGridPane[1]) {
								grid = 1;
							} else if (node.getParent() == guessGridPane[2]) {
								grid = 2;
							}
							if (gameState.currentPlayer().getGuessSheet().getCheckedBox()[grid][GridPane.getRowIndex(node) - 1] == 1) {

								((ImageView) node).setImage(new Image("/Resources/Images/checkUnmark.jpg"));
								int[][] temp = gameState.currentPlayer().getGuessSheet().getCheckedBox();
								temp[grid][GridPane.getRowIndex(node) - 1] = 0;
								gameState.currentPlayer().getGuessSheet().setCheckedBox(temp);
							} else {
								int[][] temp = gameState.currentPlayer().getGuessSheet().getCheckedBox();
								temp[grid][GridPane.getRowIndex(node) - 1] = 1;
								gameState.currentPlayer().getGuessSheet().setCheckedBox(temp);
								((ImageView) node).setImage(new Image("/Resources/Images/checkMark.jpg"));
							}
						} catch (ClassCastException e1) {
							e1.printStackTrace();
						}
					});
				}
			}
			setAlignment(Pos.TOP_CENTER);
		}
		HBox.setMargin(guessGridPane[0], new Insets(0, 0, 0, 22));
	}
}