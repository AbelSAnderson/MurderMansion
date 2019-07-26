package Logic;

import Objects.Card;
import Objects.GameState;
import Objects.Player;
import Scenes.EndGameScene;

import static Logic.Constants.*;
import static Panes.GameBoardPanes.DialoguePane.dialogue;
import static Panes.GameBoardPanes.GuessesPane.comboBoxes;
import static Panes.GameBoardPanes.Score.scoreNumber;

public class Guesses {

	/**Fills the Three ComboBoxes that are used to Accuse or Suggest.*/
	public static void guessesBox() {

		Card[][] cardLists = {CHARACTERS, WEAPONS, ROOMS};
		String[] text = {"Select a character", "Select a weapon", "Select a room"};

		for(int i = 0; i < cardLists.length; i++) {
			for (Card card : cardLists[i]) {
				comboBoxes.get(i).getItems().add(card.getName());
			}
			comboBoxes.get(i).setPromptText(text[i]);
		}
	}

	/**Checks if an Accusation is Correct.*/
	public static void correctAccusation(GameState gameState) {

		boolean correct = false;

		for(int i = 0; i < comboBoxes.size(); i++) {
			if(comboBoxes.get(i).getSelectionModel().getSelectedItem().equals(CASE_FILE[i].getName())) {
				correct = true;
			}
			else {
				correct = false;
				CURRENT_PLAYER().setScore(CURRENT_PLAYER().getScore() - 144);
				scoreNumber.setText(CURRENT_PLAYER().getScore() + "");
				dialogue.appendText("Your accusation was incorrect. Shame on you!\n");
				break;
			}
		}	
		if(correct)  {
			gameState.getMainStage().setScene(new EndGameScene(gameState));
		}
	}

	/**Checks whether players have any of the cards suggested.*/
	public static void checkCards() {
		
		CURRENT_PLAYER().setScore(CURRENT_PLAYER().getScore() - 36);
		scoreNumber.setText(CURRENT_PLAYER().getScore() + "");

		String[] cardsSelected = new String[3];

		for (int i = 0; i < cardsSelected.length; i++) {
			cardsSelected[i] = comboBoxes.get(i).getSelectionModel().getSelectedItem();
		}

		int z = 1;

		outerLoop:
		while(z < PLAYERS.size()) {

			Player player = PLAYERS.get(z);

			for(int i = 0; i < player.getCards().length; i++) {

				if(player.getCards()[i].getName().equals(cardsSelected[0]) || player.getCards()[i].getName().equals(cardsSelected[1]) || player.getCards()[i].getName().equals(cardsSelected[2])) {
					dialogue.appendText(player.getCharacter().getName() + " had the card " + player.getCards()[i].getName() + "\n");
					break outerLoop;
				}
			}
			dialogue.appendText(player.getCharacter().getName() + " doesn't have any of those cards\n");
			z++;
		}
	}
}