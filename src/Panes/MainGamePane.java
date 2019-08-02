package Panes;

import Enums.BackgroundColors;
import Objects.State;
import Panes.GameBoardPanes.*;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Description - This file is for the main game that gathers all the panes into a BorderPane
 */

public class MainGamePane extends BorderPane {

    public BoardPane boardPane;
    public ScorePane scorePane;
    public GuessSheetPane guessSheet;
    public InventoryPane inventory;
    public DialoguePane dialoguePane;
    public GuessesPane guessesPane;
    public MovementPane movementPane;
    public VBox leftContainer;

    public MainGamePane(State state) {
        boardPane = new BoardPane(state);

        HBox board = new HBox();
        board.getChildren().add(boardPane);
        board.setAlignment(Pos.CENTER);

        leftContainer = new VBox(60);

        scorePane = new ScorePane();
        guessSheet = new GuessSheetPane(state, true);
        inventory = new InventoryPane(state, true);

        leftContainer.getChildren().addAll(scorePane, guessSheet, inventory);
        leftContainer.setAlignment(Pos.CENTER);

        VBox rightContainer = new VBox(30);

        dialoguePane = new DialoguePane(state);
        guessesPane = new GuessesPane(state);
        movementPane = new MovementPane(state);

        rightContainer.getChildren().addAll(dialoguePane, guessesPane, movementPane);

        setCenter(board);
        setLeft(leftContainer);
        setRight(rightContainer);

        setBackground(BackgroundColors.BACKGROUND_DARK.getBackground());
    }
}