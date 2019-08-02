package Panes.GameBoardPanes;

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

    public Button suggestion;
    public Button accusation;
    public ArrayList<ComboBox<String>> comboBoxes = new ArrayList<>();

    public GuessesPane(State state) {

        GameState gameState = state.getCurrentGame();

        HBox cardListBoxes = new HBox(15);
        HBox buttonsHBox = new HBox(30);

        for (int i = 0; i < 3; i++) {
            comboBoxes.add(new ComboBox<>());
            cardListBoxes.getChildren().add(comboBoxes.get(i));
        }

        gameState.guessesBox();

        suggestion = new Button("Suggest");
        accusation = new Button("Accuse");

        gameState.disableGuessClicks(true);

        accusation.setOnAction(e -> {
            gameState.correctAccusation(state);
            gameState.disableGuessClicks(true);
        });

        suggestion.setOnAction(e -> {
            if (comboBoxes.get(2).getSelectionModel().getSelectedIndex() == state.getCurrentGame().currentLocation().getRoomNum() - 2) {
                gameState.checkCards();
                gameState.disableGuessClicks(true);
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
}