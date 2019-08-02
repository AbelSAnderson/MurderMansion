package Panes.GameBoardPanes;

import Enums.Cards;
import Objects.Card;
import Objects.GameState;
import Objects.State;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Description - This file is for the accusation and suggestion buttons
 */

public class GuessesPane extends VBox {

    private Button suggestion;
    private Button accusation;
    public ArrayList<ComboBox<String>> comboBoxes = new ArrayList<>();

    public GuessesPane(State state) {

        GameState gameState = state.getCurrentGame();

        HBox cardListBoxes = new HBox(15);
        HBox buttonsHBox = new HBox(30);

        for (int i = 0; i < 3; i++) {
            comboBoxes.add(new ComboBox<>());
            cardListBoxes.getChildren().add(comboBoxes.get(i));
        }

        guessesBox();

        suggestion = new Button("Suggest");
        accusation = new Button("Accuse");

        disableGuessClicks(true);

        accusation.setOnAction(e -> {
            gameState.correctAccusation(state);
            gameState.getGamePane().guessesPane.disableGuessClicks(true);
        });

        suggestion.setOnAction(e -> {
            if (comboBoxes.get(2).getSelectionModel().getSelectedIndex() == state.getCurrentGame().currentLocation().getRoomNum() - 2) {
                gameState.checkCards();
                gameState.getGamePane().guessesPane.disableGuessClicks(true);
            } else {
                gameState.getGamePane().dialoguePane.dialogue.appendText("You must select the room you're in when suggesting\n");
            }
        });

        buttonsHBox.getChildren().addAll(accusation, suggestion);

        buttonsHBox.setAlignment(Pos.CENTER);
        cardListBoxes.setAlignment(Pos.CENTER);

        VBox.setMargin(this, new Insets(65, 0, 250, 0));

        setSpacing(15);
        setAlignment(Pos.CENTER);
        getChildren().addAll(buttonsHBox, cardListBoxes);
    }

    private void guessesBox() {
        Card[][] cardLists = {Cards.CHARACTERS.getCards(), Cards.WEAPONS.getCards(), Cards.ROOMS.getCards()};
        String[] text = {"Select a character", "Select a weapon", "Select a room"};

        for (int i = 0; i < cardLists.length; i++) {
            for (Card card : cardLists[i]) {
                comboBoxes.get(i).getItems().add(card.getName());
            }
            comboBoxes.get(i).setPromptText(text[i]);
        }
    }

    public void disableGuessClicks(Boolean isDisabled) {
        accusation.setDisable(isDisabled);
        suggestion.setDisable(isDisabled);

        for (ComboBox comboBox : comboBoxes) {
            comboBox.setDisable(isDisabled);
        }
    }

}