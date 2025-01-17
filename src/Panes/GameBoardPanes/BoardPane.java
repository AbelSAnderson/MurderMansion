package Panes.GameBoardPanes;

import Enums.BackgroundMusic;
import Enums.Direction;
import Objects.GameState;
import Objects.State;
import Objects.Player;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

    public BoardPane(State state) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        ImageView boardImg = new ImageView(new Image("/Resources/Images/Gameboard.jpg"));
        boardImg.setFitWidth(780);
        boardImg.setFitHeight(810);

        getChildren().addAll(boardImg, gridPane);

        for (int rows = 0; rows < 27; rows++) {
            for (int columns = 0; columns < 26; columns++) {

                Rectangle rect = new Rectangle(30, 30);
                rect.setFill(Color.TRANSPARENT);

                gridPane.add(rect, columns, rows);
            }
        }

        //Set the Player's tokens ton the gameboard
        for (Player player : state.getCurrentGame().getPlayers()) {
            gridPane.add(player.getPiece(), player.getCharacter().getStartX(), player.getCharacter().getStartY());
        }

        //Start background music
        if (state.getBackgroundMusic().isPlaying()) state.getBackgroundMusic().stop();
        state.setBackgroundMusic(BackgroundMusic.BACKGROUND_MUSIC.getMusic());
        state.getBackgroundMusic().setCycleCount(AudioClip.INDEFINITE);
        state.getBackgroundMusic().setVolume(0.1);
        state.getBackgroundMusic().play();
    }

    /**
     * Moves the Player's token on the Gameboard.
     *
     * @param direction The direction the token is moving.
     */
    public void movement(State state, Direction direction) {

        GameState gameState = state.getCurrentGame();

        if (gameState.currentPlayer().getRollsLeft() > 0) {
            if (gameState.currentLocation().getRoomNum() == -1) {
                gameState.currentLocation().setTraversable(true);
            }

            switch (direction) {
                case UP:
                case DOWN:
                    gameState.currentPlayer().setYPos(gameState.playerY() + direction.getDirection());
                    GridPane.setRowIndex(gameState.currentPlayer().getPiece(), GridPane.getRowIndex(gameState.currentPlayer().getPiece()) + direction.getDirection());
                    break;
                case LEFT:
                case RIGHT:
                    gameState.currentPlayer().setXPos(gameState.playerX() + direction.getDirection());
                    GridPane.setColumnIndex(gameState.currentPlayer().getPiece(), GridPane.getColumnIndex(gameState.currentPlayer().getPiece()) + direction.getDirection());
                    break;
            }
            gameState.currentLocation().setTraversable(false);
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