package Main;

import Enums.Cards;
import Objects.*;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;

import static Main.Turn.disableGuessClicks;
import static Panes.GameBoardPanes.DialoguePane.dialogue;
import static Panes.GameBoardPanes.MovementPane.setButtons;

/**
 * Author - Hasan
 * Created - 21/03/2019
 * Description - This file is for the movement
 * 
 * Changed - 23/03/2019 - Cordelle
 * Description - added audioEntry for each room
 */

public class Movement {
	
	static int blockedX;
	static int blockedY;

	/**Plays Sound Effects when a Player enters a Room.*/
	private static void entryMusic(GameState gameState) {
		MediaPlayer clipMusicPlayer1 = new MediaPlayer(gameState.currentPlayer().getRoom().getEntryDoorAudio()[0]);
		clipMusicPlayer1.setVolume(0.1);
		clipMusicPlayer1.play();

		MediaPlayer clipMusicPlayer2 = new MediaPlayer(gameState.currentPlayer().getRoom().getEntryDoorAudio()[1]);
		clipMusicPlayer2.play();

		clipMusicPlayer1.setOnEndOfMedia(() -> {
			MediaPlayer clipMusicPlayer3 = new MediaPlayer(gameState.currentPlayer().getRoom().getEntryAudio());
			clipMusicPlayer3.play();
		});
	}

	/**Places Current Player into a Room.*/
	public static void placeInRoom(GameState gameState) {
		disableGuessClicks(false);

		gameState.currentLocation().setTraversable(true);
		gameState.currentPlayer().setRoom((Room) Cards.ROOMS.getCards()[gameState.currentLocation().getRoomNum()-2]);

		dialogue.appendText(gameState.currentPlayer().getCharacter().getName() + " entered the " + gameState.currentPlayer().getRoom().getName() + "\n");

		int newX = gameState.currentLocation().getMoveCharacterX();
		int newY = gameState.currentLocation().getMoveCharacterY();
		
		gameState.currentPlayer().setCurrentCoordX(newX);
		gameState.currentPlayer().setCurrentCoordY(newY);
		
		while(gameState.currentLocation().hasPlayer()) {
			newX++;
			gameState.currentPlayer().setCurrentCoordX(newX);
			gameState.currentPlayer().setCurrentCoordY(newY);
		}

		gameState.currentLocation().setHasPlayer(true);
		
		GridPane.setColumnIndex(gameState.currentPlayer().getPiece(), gameState.playerX());
		GridPane.setRowIndex(gameState.currentPlayer().getPiece(), gameState.playerY());

		entryMusic(gameState);
	}

	/**Places the Current Player at the entrance of the Room they are in.*/
	static void placeEntranceRoom(GameState gameState) {
		Tile boardPosition = gameState.currentLocation();
		
		if(!gameState.currentLocation().isTraversable()) {
			MediaPlayer exitSound = new MediaPlayer(gameState.currentPlayer().getRoom().getExitAudio());
			exitSound.play();
			
			gameState.currentLocation().setHasPlayer(false);
			gameState.currentPlayer().setCurrentCoordX(boardPosition.getMoveCharacterX());
			gameState.currentPlayer().setCurrentCoordY(boardPosition.getMoveCharacterY());
			
			gameState.currentLocation().setTraversable(false);
			blockedX = gameState.playerX();
			blockedY = gameState.playerY();
			
			GridPane.setColumnIndex(gameState.currentPlayer().getPiece(), gameState.playerX());
			GridPane.setRowIndex(gameState.currentPlayer().getPiece(), gameState.playerY());
			setButtons(gameState);
		}
	}
}