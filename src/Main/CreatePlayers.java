package Main;

import Objects.Card;
import Objects.Character;
import Objects.GameState;
import Objects.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
	public CreatePlayers(GameState gameState, Character[] playerSelection) {
		
		//ArrayList for all the characters
		ArrayList<Character> characterList = new ArrayList<>(Arrays.asList(gameState.getCharacters()));

		for (Character character : playerSelection) {
			characterList.remove(character);
		}

		//Create New List with only the Selections
		ArrayList<Character> playerList = new ArrayList<>(Arrays.asList(gameState.getCharacters()));
		playerList.removeAll(characterList);
		
		//Get Random Cards for Players
		Card[][] cardChoices = DetermineCards(gameState);

		//Create and Set Players
		for (int i = 0; i < playerList.size(); i++) {
			gameState.getPlayers().add(new Player(cardChoices[i], playerList.get(i)));
			gameState.getPlayers().get(i).setCurrentCoordX(gameState.getPlayers().get(i).getCharacter().getStartX());
			gameState.getPlayers().get(i).setCurrentCoordY(gameState.getPlayers().get(i).getCharacter().getStartY());
			if(playerList.get(i).getClass() == playerSelection[i].getClass()) {
				gameState.getPlayers().get(i).setHuman(true);
			}
		}
	}

	/**
	 * @author Hasan
	 * @since 16/03/2019
	 * @return 2-D array that holds three sets of six cards for each Player.
	 */
	private static Card[][] DetermineCards(GameState gameState) {

		//Declare the ArrayList to hold all the cards
		ArrayList<Card> masterList = new ArrayList<>();

		//Declare Array holding the ArrayLists of Characters, Weapons, and Rooms
		List[] cardsArray = {new ArrayList<>(Arrays.asList(gameState.getCharacters())), new ArrayList<>(Arrays.asList(gameState.getWeapons())), new ArrayList<>(Arrays.asList(gameState.getRooms()))};

		//Loop through cardsArray, Shuffling each ArrayList, Setting the Case File with unique cards, and adding the ArrayList to masterList
		for (int i = 0; i < cardsArray.length; i++) {
			Collections.shuffle(cardsArray[i]);

			gameState.getCaseFile()[i] = (Card) cardsArray[i].get(0);
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