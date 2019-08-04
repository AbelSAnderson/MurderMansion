package Panes.GameBoardPanes;

import Objects.Card;
import Objects.State;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Note - This file has temporary items that will be removed
 * Description - This file is for the inventory (The 6 cards the player holds)
 * <p>
 * Changed - 18/03/2019 - Hasan
 * Description - Added the 6 random cards that will appear on the screen as the inventory
 * <p>
 * Changed - 30/03/2019 - Abel
 * Description - Fixed sizing of cards and Removed Function
 */

public class InventoryPane extends VBox {

    public InventoryPane(State state) {
        createInventory(state.getCurrentGame().currentPlayer().getCards(), true);

        setMargin(this, new Insets(10, 10, 10, 75));
        setSpacing(15);
        setAlignment(Pos.CENTER);
    }

    public void createInventory(Card[] cards, boolean isTransition) {
        getChildren().clear();

        ImageView cardImg;

        for (int i = 0; i < 2; i++) {
            HBox cardBox = new HBox(10);

            for (int z = i * 3; z < cards.length; z++) {
                if (isTransition) {
                    cardImg = new ImageView(new Image("/Resources/Images/CardBack.jpg"));
                } else {
                    cardImg = new ImageView(cards[z].getImg());
                }
                cardImg.setFitHeight(168 * 1.2);
                cardImg.setFitWidth(120 * 1.2);
                cardBox.getChildren().add(cardImg);
                if (z == 2) break;
            }
            getChildren().add(cardBox);
        }
    }
}