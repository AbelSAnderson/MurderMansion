package Logic;

import Objects.Card;
import Objects.Character;
import Objects.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static Logic.Constants.*;

/**
 * Author - Hasan
 * Created - 15/03/2019
 * Description - Creates List of Players Based off Player Character
 *
 * Changed - 16/03/2019 - Abel
 * Description - Combined two Functions and Shortened Code
 */

public class CreatePlayers {

	/**
	 * Creates and Stores the Player's Characters
	 * @param playerSelection Three Characters that were Selected by the Players
	 */
	public CreatePlayers(Character[] playerSelection) {
		
		//ArrayList for all the characters
		ArrayList<Character> characterList = new ArrayList<>(Arrays.asList(CHARACTERS));

		for (Character character : playerSelection) {
			characterList.remove(character);
		}

		//Create New List with only the Selections
		ArrayList<Character> playerList = new ArrayList<>(Arrays.asList(CHARACTERS));
		playerList.removeAll(characterList);
		
		//Get Random Cards for Players
		Card[][] cardChoices = DetermineCards();

		//Create and Set Players
		for (int i = 0; i < playerList.size(); i++) {
			PLAYERS.add(new Player(cardChoices[i], playerList.get(i)));
			PLAYERS.get(i).setCurrentCoordX(PLAYERS.get(i).getCharacter().getStartX());
			PLAYERS.get(i).setCurrentCoordY(PLAYERS.get(i).getCharacter().getStartY());
			if(playerList.get(i).getClass() == playerSelection[i].getClass()) {
				PLAYERS.get(i).setHuman(true);
			}
		}
	}

	/**
	 * @author Hasan
	 * @since 16/03/2019
	 * @return 2-D array that holds three sets of six cards for each Player.
	 */
	private static Card[][] DetermineCards() {

		//Declare the ArrayList to hold all the cards
		ArrayList<Card> masterList = new ArrayList<>();

		//Declare Array holding the ArrayLists of Characters, Weapons, and Rooms
		List[] cardsArray = {new ArrayList<>(Arrays.asList(CHARACTERS)), new ArrayList<>(Arrays.asList(WEAPONS)), new ArrayList<>(Arrays.asList(ROOMS))};

		//Loop through cardsArray, Shuffling each ArrayList, Setting the Case File with unique cards, and adding the ArrayList to masterList
		for (int i = 0; i < cardsArray.length; i++) {
			Collections.shuffle(cardsArray[i]);

			CASE_FILE[i] = (Card) cardsArray[i].get(0);
			System.out.println(cardsArray[i].get(0));
			cardsArray[i].remove(0);

			masterList.addAll(cardsArray[i]);
		}

		//Shuffle masterList
		Collections.shuffle(masterList);

		//Create 2D-Array for the Player's Cards
		Card[][] playersCards = {new Card[6], new Card[6], new Card[6]};

		//Assign different cards to each Array
		for(int i = 0; i < playersCards[0].length; i++) {
			playersCards[0][i] = masterList.get(i);
			playersCards[1][i] = masterList.get(i + 6);
			playersCards[2][i] = masterList.get(i + 12);
		}
	
		return playersCards;
	}
}