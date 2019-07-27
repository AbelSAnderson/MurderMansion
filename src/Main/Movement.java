package Main;

import Objects.State;
import Objects.Tile;
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
	private static void entryMusic(State state) {
		MediaPlayer clipMusicPlayer1 = new MediaPlayer(state.currentPlayer().getRoom().getEntryDoorAudio()[0]);
		clipMusicPlayer1.setVolume(0.1);
		clipMusicPlayer1.play();

		MediaPlayer clipMusicPlayer2 = new MediaPlayer(state.currentPlayer().getRoom().getEntryDoorAudio()[1]);
		clipMusicPlayer2.play();

		clipMusicPlayer1.setOnEndOfMedia(() -> {
			MediaPlayer clipMusicPlayer3 = new MediaPlayer(state.currentPlayer().getRoom().getEntryAudio());
			clipMusicPlayer3.play();
		});
	}

	/**Places Current Player into a Room.*/
	public static void placeInRoom(State state) {
		disableGuessClicks(false);

		state.currentLocation().setTraversable(true);
		state.currentPlayer().setRoom(state.getRooms()[state.currentLocation().getRoomNum()-2]);

		dialogue.appendText(state.currentPlayer().getCharacter().getName() + " entered the " + state.currentPlayer().getRoom().getName() + "\n");

		int newX = state.currentLocation().getMoveCharacterX();
		int newY = state.currentLocation().getMoveCharacterY();
		
		state.currentPlayer().setCurrentCoordX(newX);
		state.currentPlayer().setCurrentCoordY(newY);
		
		while(state.currentLocation().hasPlayer()) {
			newX++;
			state.currentPlayer().setCurrentCoordX(newX);
			state.currentPlayer().setCurrentCoordY(newY);
		}

		state.currentLocation().setHasPlayer(true);
		
		GridPane.setColumnIndex(state.currentPlayer().getPiece(), state.playerX());
		GridPane.setRowIndex(state.currentPlayer().getPiece(), state.playerY());

		entryMusic(state);
	}

	/**Places the Current Player at the entrance of the Room they are in.*/
	static void placeEntranceRoom(State state) {
		Tile boardPosition = state.currentLocation();
		
		if(!state.currentLocation().isTraversable()) {
			MediaPlayer exitSound = new MediaPlayer(state.currentPlayer().getRoom().getExitAudio());
			exitSound.play();
			
			state.currentLocation().setHasPlayer(false);
			state.currentPlayer().setCurrentCoordX(boardPosition.getMoveCharacterX());
			state.currentPlayer().setCurrentCoordY(boardPosition.getMoveCharacterY());
			
			state.currentLocation().setTraversable(false);
			blockedX = state.playerX();
			blockedY = state.playerY();
			
			GridPane.setColumnIndex(state.currentPlayer().getPiece(), state.playerX());
			GridPane.setRowIndex(state.currentPlayer().getPiece(), state.playerY());
			setButtons(state);
		}
	}
}