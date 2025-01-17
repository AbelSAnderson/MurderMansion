package Panes.GameBoardPanes;

import Enums.Cards;
import Enums.Fonts;
import Objects.Card;
import Objects.GameState;
import Objects.State;
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
 * <p>
 * Changed - 30/03/2019 - Cordelle in consultation with Abel and Hasan (group-work Saturday)
 * Description - designed Guess Sheet
 * <p>
 * Changed - 04/04/2019 - Hasan
 * Description - Made it more responsive with our player objects so we can save each players guess sheet
 */

public class GuessSheetPane extends HBox {

    private int grid = 0;
    private boolean isTransition = true;

    public GuessSheetPane(State state) {
        GameState gameState = state.getCurrentGame();

        List[] categoryArray = {new ArrayList<>(Arrays.asList(Cards.CHARACTERS.getCards())), new ArrayList<>(Arrays.asList(Cards.WEAPONS.getCards())), new ArrayList<>(Arrays.asList(Cards.ROOMS.getCards()))};
        String[] categoryNames = {"Suspects", "Weapons", "Rooms"};

        GridPane[] guessGridPane = new GridPane[3];

        // Loop through the Array of Cards
        for (int indexList = 0; indexList < categoryNames.length; indexList++) {

            //Set the title and styles for each GridPane
            Text categoryName = new Text(categoryNames[indexList]);
            categoryName.getStyleClass().add("sheetNames");
            guessGridPane[indexList] = new GridPane();
            guessGridPane[indexList].add(categoryName, 0, 0);

            HBox.setMargin(guessGridPane[indexList], new Insets(0, 0, 0, 10));
            guessGridPane[indexList].setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            //Loop through the Cards
            for (int indexItems = 0; indexItems < categoryArray[indexList].size(); indexItems++) {

                // Add the Card Name
                Text itemName = new Text(((Card) categoryArray[indexList].get(indexItems)).getName());
                itemName.getStyleClass().add("sheetCards");
                itemName.setFont(Fonts.BLANK_FONT.FellCursive(getClass(), 20));

                guessGridPane[indexList].add(itemName, 0, indexItems + 1);

                // Add the CheckBox
                ImageView temp;

                if (isTransition || gameState.currentPlayer().getGuessSheet().getCheckedBox()[indexList][indexItems] == 0) {
                    temp = new ImageView(new Image("/Resources/Images/checkBox.jpg"));
                } else {
                    temp = new ImageView(new Image("/Resources/Images/checkMark.jpg"));
                }
                guessGridPane[indexList].add(temp, 1, indexItems + 1);
            }

            guessGridPane[indexList].getStyleClass().add("gridPane");
            getChildren().add(guessGridPane[indexList]);
        }

        //Add Event Listeners to the gridPanes

        //Loop through the GridPanes
        for (int i = 0; i < getChildren().size(); i++) {
            for (Node node : guessGridPane[i].getChildren()) {

                if (!(node instanceof ImageView)) continue;

                node.setOnMousePressed(e -> {

                    //Determine GridPane
                    if (node.getParent() == guessGridPane[0]) {
                        grid = 0;
                    } else if (node.getParent() == guessGridPane[1]) {
                        grid = 1;
                    } else if (node.getParent() == guessGridPane[2]) {
                        grid = 2;
                    }

                    //Check if Transition
                    if (!isTransition) {
                        //Set temp variable of the guessSheet
                        int[][] temp = gameState.currentPlayer().getGuessSheet().getCheckedBox();

                        //Determine what Picture to switch it to
                        if (gameState.currentPlayer().getGuessSheet().getCheckedBox()[grid][GridPane.getRowIndex(node) - 1] == 1) {
                            temp[grid][GridPane.getRowIndex(node) - 1] = 0;
                            ((ImageView) node).setImage(new Image("/Resources/Images/checkBox.jpg"));
                        } else {
                            temp[grid][GridPane.getRowIndex(node) - 1] = 1;
                            ((ImageView) node).setImage(new Image("/Resources/Images/checkMark.jpg"));
                        }

                        //Set the new Value to the Player's guessSheet
                        gameState.currentPlayer().getGuessSheet().setCheckedBox(temp);
                    }
                });
            }
        }
        setAlignment(Pos.TOP_CENTER);
        HBox.setMargin(guessGridPane[0], new Insets(0, 0, 0, 22));
    }

    public void setGuessSheet(GameState gameState, boolean isTransition) {
        this.isTransition = isTransition;

        for (int i = 0; i < getChildren().size(); i++) {
            int cardPosition = 0;

            for (Node node : ((GridPane) getChildren().get(i)).getChildren()) {
                if (node instanceof ImageView) {
                    if (isTransition || gameState.currentPlayer().getGuessSheet().getCheckedBox()[i][cardPosition] == 0)
                        ((ImageView) node).setImage(new Image("/Resources/Images/checkBox.jpg"));
                    else ((ImageView) node).setImage(new Image("/Resources/Images/checkMark.jpg"));

                    cardPosition++;
                }
            }
        }
    }
}