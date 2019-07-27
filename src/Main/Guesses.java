package Main;

import Enums.Cards;
import Objects.Card;
import Objects.GameState;
import Objects.State;
import Objects.Player;
import Scenes.EndGameScene;

import static Panes.GameBoardPanes.DialoguePane.dialogue;
import static Panes.GameBoardPanes.GuessesPane.comboBoxes;
import static Panes.GameBoardPanes.Score.scoreNumber;

public class Guesses {

	/**Fills the Three ComboBoxes that are used to Accuse or Suggest.*/
	public static void guessesBox(State state) {

		Card[][] cardLists = {Cards.CHARACTERS.getCards(), Cards.WEAPONS.getCards(), Cards.ROOMS.getCards()};
		String[] text = {"Select a character", "Select a weapon", "Select a room"};

		for(int i = 0; i < cardLists.length; i++) {
			for (Card card : cardLists[i]) {
				comboBoxes.get(i).getItems().add(card.getName());
			}
			comboBoxes.get(i).setPromptText(text[i]);
		}
	}

	/**Checks if an Accusation is Correct.*/
	public static void correctAccusation(State state) {

		GameState gameState = state.getCurrentGame();

		boolean correct = false;

		for(int i = 0; i < comboBoxes.size(); i++) {
			if(comboBoxes.get(i).getSelectionModel().getSelectedItem().equals(gameState.getCaseFile()[i].getName())) {
				correct = true;
			}
			else {
				correct = false;
				gameState.currentPlayer().setScore(gameState.currentPlayer().getScore() - 144);
				scoreNumber.setText(gameState.currentPlayer().getScore() + "");
				dialogue.appendText("Your accusation was incorrect. Shame on you!\n");
				break;
			}
		}	
		if(correct)  {
			state.getMainStage().setScene(new EndGameScene(state));
		}
	}

	/**Checks whether players have any of the cards suggested.*/
	public static void checkCards(GameState gameState) {
		
		gameState.currentPlayer().setScore(gameState.currentPlayer().getScore() - 36);
		scoreNumber.setText(gameState.currentPlayer().getScore() + "");

		String[] cardsSelected = new String[3];

		for (int i = 0; i < cardsSelected.length; i++) {
			cardsSelected[i] = comboBoxes.get(i).getSelectionModel().getSelectedItem();
		}

		int z = 1;

		outerLoop:
		while(z < gameState.getPlayers().length) {

			Player player = gameState.getPlayers()[z];

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