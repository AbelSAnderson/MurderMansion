package Panes.GameBoardPanes;

import Objects.GameState;
import Objects.State;
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

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Note - Created File with a 26x27 grid of Rectangles(30x30)
 * Description - This file is to be a transparent board that'll be over our main board image
 * <p>
 * Changed - 23/03/2019 - Cordelle
 * Description - added game-play AudioClip for background music; sourced by Cordelle
 * <p>
 * Changed - 13/04/2019 - Cordelle
 * Description - stopped intro and credits AudioClip for background music
 */

public class BoardPane extends StackPane {

    GridPane gridpane;

    public BoardPane(State state) {
        gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);

        ImageView boardImg = new ImageView(new Image("/Resources/Images/Gameboard.jpg"));
        boardImg.setFitWidth(780);
        boardImg.setFitHeight(810);

        getChildren().addAll(boardImg, gridpane);

        for (int rows = 0; rows < 27; rows++) {
            for (int columns = 0; columns < 26; columns++) {

                Rectangle rect = new Rectangle(30, 30);
                rect.setFill(Color.TRANSPARENT);

                gridpane.add(rect, columns, rows);
            }
        }

        //Set the Player's tokens ton the gameboard
        for (Player player : state.getCurrentGame().getPlayers()) {
            gridpane.add(player.getPiece(), player.getCharacter().getStartX(), player.getCharacter().getStartY());
        }

        //Start background music
        if (state.getBackgroundMusic().isPlaying()) state.getBackgroundMusic().stop();
        state.setBackgroundMusic(new AudioClip(new File("src/Resources/Audio/background.wav").toURI().toString()));
        state.getBackgroundMusic().setCycleCount(AudioClip.INDEFINITE);
        state.getBackgroundMusic().setVolume(0.1);
        state.getBackgroundMusic().play();
    }

    /**
     * Moves the Player's token on the Gameboard.
     *
     * @param direction The direction the token is moving.
     */
    public void movement(State state, int direction) {

        GameState gameState = state.getCurrentGame();

        Tile newBoardPosition;

        if (gameState.currentPlayer().getRollsLeft() > 0) {
            if (gameState.currentLocation().getRoomNum() == -1) {
                gameState.currentLocation().setTraversable(true);
            }
            switch (direction) {
                case 0:
                    gameState.currentPlayer().setYPos(gameState.playerY() - 1);
                    gameState.getGameBoard()[gameState.playerY()][gameState.playerX()].setTraversable(false);
                    GridPane.setRowIndex(gameState.currentPlayer().getPiece(), GridPane.getRowIndex(gameState.currentPlayer().getPiece()) - 1);
                    System.out.println(GridPane.getRowIndex(gameState.currentPlayer().getPiece()) - 1 + " Player Y:" + gameState.currentPlayer().getYPos());
                    break;
                case 1:
                    newBoardPosition = gameState.getGameBoard()[gameState.playerY() + 1][gameState.playerX()];
                    gameState.currentPlayer().setYPos(gameState.playerY() + 1);
                    newBoardPosition.setTraversable(false);
                    GridPane.setRowIndex(gameState.currentPlayer().getPiece(), GridPane.getRowIndex(gameState.currentPlayer().getPiece()) + 1);
                    break;
                case 2:
                    newBoardPosition = gameState.getGameBoard()[gameState.playerY()][gameState.playerX() - 1];
                    gameState.currentPlayer().setXPos(gameState.playerX() - 1);
                    newBoardPosition.setTraversable(false);
                    GridPane.setColumnIndex(gameState.currentPlayer().getPiece(), GridPane.getColumnIndex(gameState.currentPlayer().getPiece()) - 1);
                    break;
                case 3:
                    newBoardPosition = gameState.getGameBoard()[gameState.playerY()][gameState.playerX() + 1];
                    gameState.currentPlayer().setXPos(gameState.playerX() + 1);
                    newBoardPosition.setTraversable(false);
                    GridPane.setColumnIndex(gameState.currentPlayer().getPiece(), GridPane.getColumnIndex(gameState.currentPlayer().getPiece()) + 1);
                    break;
            }

            setMoves(state);
        }
    }

    /**
     * Sets the Current Player's Moves left.
     */
    private void setMoves(State state) {

        GameState gameState = state.getCurrentGame();

        gameState.currentPlayer().setRollsLeft(gameState.currentPlayer().getRollsLeft() - 1);

        if (gameState.currentLocation().getRoomNum() != -1) {
            gameState.placeInRoom();
            gameState.getGamePane().movementPane.rollsText.setText("0");
        } else {
            if (gameState.currentPlayer().getRollsLeft() > 0) {
                gameState.getGamePane().dialoguePane.dialogue.appendText("You have " + gameState.currentPlayer().getRollsLeft() + " moves left\n");
                gameState.getGamePane().movementPane.rollsText.setText("" + gameState.currentPlayer().getRollsLeft());
            } else {
                gameState.getGamePane().dialoguePane.dialogue.appendText("You are out of moves\n");
                gameState.getGamePane().movementPane.rollsText.setText("" + gameState.currentPlayer().getRollsLeft());
            }
        }
        gameState.getGamePane().movementPane.setButtons(gameState);
    }
}