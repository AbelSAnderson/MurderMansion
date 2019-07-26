package Panes.GameBoardPanes;

import Objects.GameState;
import Objects.Player;
import Objects.Tile;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;

import static Logic.Constants.*;
import static Logic.Movement.placeInRoom;
import static Panes.GameBoardPanes.MovementPane.setButtons;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Note - Created File with a 26x27 grid of Rectangles(30x30)
 * Description - This file is to be a transparent board that'll be over our main board image
 * 
 * Changed - 23/03/2019 - Cordelle
 * Description - added game-play AudioClip for background music; sourced by Cordelle
 * 
 * Changed - 13/04/2019 - Cordelle
 * Description - stopped intro and credits AudioClip for background music
 * 
 */

public class BoardPane extends StackPane{
		
	public BoardPane(GameState gameState) {
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		
		ImageView boardImg = new ImageView(new Image("/Resources/Images/Gameboard.jpg"));
		boardImg.setFitWidth(780);
		boardImg.setFitHeight(810);
		
		getChildren().addAll(boardImg, gridpane);
		
		for(int rows = 0; rows < 27; rows++) {
			for(int columns = 0; columns < 26; columns++) {
				
				Rectangle rect = new Rectangle(30, 30);
				rect.setFill(Color.TRANSPARENT);
				
				gridpane.add(rect, columns, rows);	
			}
		}
		placePlayerStart(gameState, gridpane);
		
		//Start background music
		if(BACKGROUND_MUSIC.isPlaying()) BACKGROUND_MUSIC.stop();
		BACKGROUND_MUSIC = new AudioClip(new File("src/Resources/Audio/background.wav").toURI().toString());
		BACKGROUND_MUSIC.setCycleCount(AudioClip.INDEFINITE);
		BACKGROUND_MUSIC.setVolume(0.1);
		BACKGROUND_MUSIC.play();

	}

	/**
	 * Places each Player's token on the Gameboard.
	 * @param gridPane The Gameboard where the Player's tokens are positioned.
	 */
	private void placePlayerStart(GameState gameState, GridPane gridPane) {
		for ( Player player : gameState.getPlayers()) {
			gridPane.add(player.getPiece(), player.getCharacter().getStartX(), player.getCharacter().getStartY());
		}
	}

	/**
	 * Moves the Player's token on the Gameboard.
	 * @param direction The direction the token is moving.
	 */
	static void movement(GameState gameState, int direction) {
		Tile newBoardPosition;
		
		if(gameState.currentPlayer().getRollsLeft() > 0) {
			if(gameState.currentLocation().getRoomNum() == -1) {
				gameState.currentLocation().setTraversable(true);
			}
			switch(direction) {
				case 0:
					newBoardPosition = gameState.getGameBoard()[gameState.playerY() - 1][gameState.playerX()];
					gameState.currentPlayer().setCurrentCoordY(gameState.playerY() - 1);
					newBoardPosition.setTraversable(false);
					GridPane.setRowIndex(gameState.currentPlayer().getPiece(), GridPane.getRowIndex(gameState.currentPlayer().getPiece()) - 1);
				break;
				case 1:
					newBoardPosition = gameState.getGameBoard()[gameState.playerY() + 1][gameState.playerX()];
					gameState.currentPlayer().setCurrentCoordY(gameState.playerY() + 1);
					newBoardPosition.setTraversable(false);
					GridPane.setRowIndex(gameState.currentPlayer().getPiece(), GridPane.getRowIndex(gameState.currentPlayer().getPiece()) + 1);
				break;
				case 2:
					newBoardPosition = gameState.getGameBoard()[gameState.playerY()][gameState.playerX() - 1];
					gameState.currentPlayer().setCurrentCoordX(gameState.playerX() - 1);
					newBoardPosition.setTraversable(false);
					GridPane.setColumnIndex(gameState.currentPlayer().getPiece(), GridPane.getColumnIndex(gameState.currentPlayer().getPiece()) - 1);
				break;
				case 3:
					newBoardPosition = gameState.getGameBoard()[gameState.playerY()][gameState.playerX() + 1];
					gameState.currentPlayer().setCurrentCoordX(gameState.playerX() + 1);
					newBoardPosition.setTraversable(false);
					GridPane.setColumnIndex(gameState.currentPlayer().getPiece(), GridPane.getColumnIndex(gameState.currentPlayer().getPiece()) + 1);
				break;
			}

			setMoves(gameState);
		}
	}

	/**Sets the Current Player's Moves left.*/
	private static void setMoves(GameState gameState) {
		gameState.currentPlayer().setRollsLeft(gameState.currentPlayer().getRollsLeft() - 1);

		if(gameState.currentLocation().getRoomNum() != -1) {
			placeInRoom(gameState);
			MovementPane.rollsText.setText("0");
		} else {
			if(gameState.currentPlayer().getRollsLeft() > 0) {
				DialoguePane.dialogue.appendText("You have " + gameState.currentPlayer().getRollsLeft() + " moves left\n");
				MovementPane.rollsText.setText("" + gameState.currentPlayer().getRollsLeft());
			}
			else {
				DialoguePane.dialogue.appendText("You are out of moves\n");
				MovementPane.rollsText.setText("" + gameState.currentPlayer().getRollsLeft());
			}
		}
		setButtons(gameState);
	}
}