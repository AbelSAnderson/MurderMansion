package Logic;

import Objects.Tile;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;

import static Logic.Constants.*;
import static Logic.Turn.disableGuessClicks;
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
	private static void entryMusic() {
		MediaPlayer clipMusicPlayer1 = new MediaPlayer(CURRENT_PLAYER().getRoom().getEntryDoorAudio()[0]);
		clipMusicPlayer1.setVolume(0.1);
		clipMusicPlayer1.play();

		MediaPlayer clipMusicPlayer2 = new MediaPlayer(CURRENT_PLAYER().getRoom().getEntryDoorAudio()[1]);
		clipMusicPlayer2.play();

		clipMusicPlayer1.setOnEndOfMedia(() -> {
			MediaPlayer clipMusicPlayer3 = new MediaPlayer(CURRENT_PLAYER().getRoom().getEntryAudio());
			clipMusicPlayer3.play();
		});
	}

	/**Places Current Player into a Room.*/
	public static void placeInRoom() {
		disableGuessClicks(false);

		CURRENT_LOCATION().setTraversable(true);
		CURRENT_PLAYER().setRoom(ROOMS[CURRENT_LOCATION().getRoomNum()-2]);

		dialogue.appendText(CURRENT_PLAYER().getCharacter().getName() + " entered the " + CURRENT_PLAYER().getRoom().getName() + "\n");

		int newX = CURRENT_LOCATION().getMoveCharacterX();
		int newY = CURRENT_LOCATION().getMoveCharacterY();
		
		CURRENT_PLAYER().setCurrentCoordX(newX);
		CURRENT_PLAYER().setCurrentCoordY(newY);
		
		while(CURRENT_LOCATION().hasPlayer()) {
			newX++;
			CURRENT_PLAYER().setCurrentCoordX(newX);
			CURRENT_PLAYER().setCurrentCoordY(newY);
		}

		CURRENT_LOCATION().setHasPlayer(true);
		
		GridPane.setColumnIndex(CURRENT_PLAYER().getPiece(), X());
		GridPane.setRowIndex(CURRENT_PLAYER().getPiece(), Y());

		entryMusic();
	}

	/**Places the Current Player at the entrance of the Room they are in.*/
	static void placeEntranceRoom() {
		Tile boardPosition = CURRENT_LOCATION();
		
		if(!CURRENT_LOCATION().isTraversable()) {
			MediaPlayer exitSound = new MediaPlayer(CURRENT_PLAYER().getRoom().getExitAudio());
			exitSound.play();
			
			CURRENT_LOCATION().setHasPlayer(false);
			CURRENT_PLAYER().setCurrentCoordX(boardPosition.getMoveCharacterX());
			CURRENT_PLAYER().setCurrentCoordY(boardPosition.getMoveCharacterY());
			
			CURRENT_LOCATION().setTraversable(false);
			blockedX = X();
			blockedY = Y();
			
			GridPane.setColumnIndex(CURRENT_PLAYER().getPiece(), X());
			GridPane.setRowIndex(CURRENT_PLAYER().getPiece(), Y());
			setButtons();
		}
	}
}