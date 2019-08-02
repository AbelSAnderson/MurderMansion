package Objects;

import Enums.Cards;
import Panes.GameBoardPanes.GuessSheetPane;
import Panes.GameBoardPanes.InventoryPane;
import Panes.MainGamePane;
import Scenes.EndGameScene;
import Scenes.TransitionScene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameState {

    //Properties
    private Stage mainStage;
    private Stage pauseStage;
    private Tile[][] gameBoard;
    private MainGamePane gamePane;
    private Player[] players;
    private Card[] caseFile;
    private int currentPlayer;

    private int blockedX;
    private int blockedY;

    //Constructor
    public GameState(Stage mainStage, Tile[][] gameBoard, Player[] players, Card[] caseFile) {
        this.mainStage = mainStage;
        this.gameBoard = gameBoard;
        this.players = players;
        this.caseFile = caseFile;
        currentPlayer = 0;
    }

    //Methods

    /**
     * @return The Current Player.
     */
    public Player currentPlayer() {
        return players[currentPlayer];
    }

    /**
     * @return Current Player's X Position.
     */
    public int playerX() {
        return currentPlayer().getXPos();
    }

    /**
     * @return Current Player's Y Position.
     */
    public int playerY() {
        return currentPlayer().getYPos();
    }

    /**
     * @return Tile that the Current Player is on.
     */
    public Tile currentLocation() {
        return getGameBoard()[playerY()][playerX()];
    }

    public void disableGuessClicks(Boolean isDisabled) {
        gamePane.guessesPane.accusation.setDisable(isDisabled);
        gamePane.guessesPane.suggestion.setDisable(isDisabled);

        for (ComboBox comboBox : gamePane.guessesPane.comboBoxes) {
            comboBox.setDisable(isDisabled);
        }
    }

    public void endTurn(State state) {
        GameState gameState = state.getCurrentGame();

        gameState.currentPlayer().setScore(gameState.currentPlayer().getScore() - 18);

        gameState.getGameBoard()[blockedY][blockedX].setTraversable(true);

        gameState.currentPlayer().setRollsLeft(0);

        gameState.setCurrentPlayer(gameState.getCurrentPlayer() + 1);
        if (gameState.getPlayers().length == gameState.getCurrentPlayer()) gameState.setCurrentPlayer(0);

        disableGuessClicks(true);

        gamePane.scorePane.scoreNumber.setText("" + gameState.currentPlayer().getScore());
        gamePane.movementPane.rollsText.setText("0");

        switchPlayerUI(state);
        displayTransition(state);
    }

    /**
     * Switches the Player's UI when Changing Players.
     */
    private void switchPlayerUI(State state) {
        gamePane.leftContainer.getChildren().remove(gamePane.guessSheet);
        gamePane.guessSheet = new GuessSheetPane(state, true);

        gamePane.leftContainer.getChildren().remove(gamePane.inventory);
        gamePane.inventory = new InventoryPane(state, true);

        gamePane.leftContainer.getChildren().addAll(gamePane.guessSheet, gamePane.inventory);
    }

    /**
     * Displays the Transition Stage When Changing Players.
     */
    public void displayTransition(State state) {
        printDialogue();
        gamePane.movementPane.disableButtons(true);

        pauseStage = new Stage();
        pauseStage.initStyle(StageStyle.TRANSPARENT);
        pauseStage.setAlwaysOnTop(true);
        pauseStage.setScene(new TransitionScene(state));
        pauseStage.show();

        state.getMainStage().setOnCloseRequest(e -> pauseStage.close());
        pauseStage.setOnCloseRequest(e -> startTurn(state));
    }

    public void startTurn(State state) {
        GameState gameState = state.getCurrentGame();

        gamePane.leftContainer.getChildren().remove(gamePane.guessSheet);
        gamePane.guessSheet = new GuessSheetPane(state, false);

        gamePane.leftContainer.getChildren().remove(gamePane.inventory);
        gamePane.inventory = new InventoryPane(state, false);

        gamePane.leftContainer.getChildren().addAll(gamePane.guessSheet, gamePane.inventory);

        //Place the piece at the room entrance if the players current moveX and moveY's room number is not -1 (anything not -1 is a room)
        if (gameState.getGameBoard()[gameState.currentLocation().getMoveCharacterY()][gameState.currentLocation().getMoveCharacterX()].getRoomNum() != -1) {
            placeEntranceRoom();
        }

        pauseStage.close();
        gamePane.movementPane.disableButtons(false);
        gamePane.movementPane.setButtons(gameState);
    }

    private void entryMusic() {
        MediaPlayer clipMusicPlayer1 = new MediaPlayer(currentPlayer().getRoom().getEntryDoorAudio()[0]);
        clipMusicPlayer1.setVolume(0.1);
        clipMusicPlayer1.play();

        MediaPlayer clipMusicPlayer2 = new MediaPlayer(currentPlayer().getRoom().getEntryDoorAudio()[1]);
        clipMusicPlayer2.play();

        clipMusicPlayer1.setOnEndOfMedia(() -> {
            MediaPlayer clipMusicPlayer3 = new MediaPlayer(currentPlayer().getRoom().getEntryAudio());
            clipMusicPlayer3.play();
        });
    }

    /**
     * Places Current Player into a Room.
     */
    public void placeInRoom() {
        disableGuessClicks(false);

        currentLocation().setTraversable(true);
        currentPlayer().setRoom((Room) Cards.ROOMS.getCards()[currentLocation().getRoomNum() - 2]);

        printDialogue(currentPlayer().getCharacter().getName() + " entered the " + currentPlayer().getRoom().getName() + "\n");

        int newX = currentLocation().getMoveCharacterX();
        int newY = currentLocation().getMoveCharacterY();

        currentPlayer().setXPos(newX);
        currentPlayer().setYPos(newY);

        while (currentLocation().hasPlayer()) {
            newX++;
            currentPlayer().setXPos(newX);
            currentPlayer().setYPos(newY);
        }

        currentLocation().setHasPlayer(true);

        GridPane.setColumnIndex(currentPlayer().getPiece(), playerX());
        GridPane.setRowIndex(currentPlayer().getPiece(), playerY());

        entryMusic();
    }

    public void printDialogue() {
        gamePane.dialoguePane.dialogue.clear();
    }

    public void printDialogue(String text) {
        gamePane.dialoguePane.dialogue.appendText(text);
    }


    /**
     * Places the Current Player at the entrance of the Room they are in.
     */
    public void placeEntranceRoom() {
        Tile boardPosition = currentLocation();

        if (!currentLocation().isTraversable()) {
            MediaPlayer exitSound = new MediaPlayer(currentPlayer().getRoom().getExitAudio());
            exitSound.play();

            currentLocation().setHasPlayer(false);
            currentPlayer().setXPos(boardPosition.getMoveCharacterX());
            currentPlayer().setYPos(boardPosition.getMoveCharacterY());

            currentLocation().setTraversable(false);
            blockedX = playerX();
            blockedY = playerY();

            GridPane.setColumnIndex(currentPlayer().getPiece(), playerX());
            GridPane.setRowIndex(currentPlayer().getPiece(), playerY());
            gamePane.movementPane.setButtons(this);
        }
    }

    public void guessesBox() {
        Card[][] cardLists = {Cards.CHARACTERS.getCards(), Cards.WEAPONS.getCards(), Cards.ROOMS.getCards()};
        String[] text = {"Select a character", "Select a weapon", "Select a room"};

        for (int i = 0; i < cardLists.length; i++) {
            for (Card card : cardLists[i]) {
                gamePane.guessesPane.comboBoxes.get(i).getItems().add(card.getName());
            }
            gamePane.guessesPane.comboBoxes.get(i).setPromptText(text[i]);
        }
    }

    /**
     * Checks if an Accusation is Correct.
     */
    public void correctAccusation(State state) {
        boolean correct = false;

        for (int i = 0; i < gamePane.guessesPane.comboBoxes.size(); i++) {
            if (gamePane.guessesPane.comboBoxes.get(i).getSelectionModel().getSelectedItem().equals(getCaseFile()[i].getName())) {
                correct = true;
            } else {
                correct = false;
                currentPlayer().setScore(currentPlayer().getScore() - 144);
                gamePane.scorePane.scoreNumber.setText(currentPlayer().getScore() + "");
                printDialogue("Your accusation was incorrect. Shame on you!\n");
                break;
            }
        }
        if (correct) {
            state.getMainStage().setScene(new EndGameScene(state));
        }
    }

    /**
     * Checks whether players have any of the cards suggested.
     */
    public void checkCards() {
        currentPlayer().setScore(currentPlayer().getScore() - 36);
        gamePane.scorePane.scoreNumber.setText(currentPlayer().getScore() + "");

        String[] cardsSelected = new String[3];

        for (int i = 0; i < cardsSelected.length; i++) {
            cardsSelected[i] = gamePane.guessesPane.comboBoxes.get(i).getSelectionModel().getSelectedItem();
        }

        int z = 1;

        outerLoop:
        while (z < getPlayers().length) {

            Player player = getPlayers()[z];

            for (int i = 0; i < player.getCards().length; i++) {

                if (player.getCards()[i].getName().equals(cardsSelected[0]) || player.getCards()[i].getName().equals(cardsSelected[1]) || player.getCards()[i].getName().equals(cardsSelected[2])) {
                    printDialogue(player.getCharacter().getName() + " had the card " + player.getCards()[i].getName() + "\n");
                    break outerLoop;
                }
            }
            printDialogue(player.getCharacter().getName() + " doesn't have any of those cards\n");
            z++;
        }
    }

    //Getters and Setters
    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Tile[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Tile[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public MainGamePane getGamePane() {
        return gamePane;
    }

    public void setGamePane(MainGamePane gamePane) {
        this.gamePane = gamePane;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Card[] getCaseFile() {
        return caseFile;
    }

    public void setCaseFile(Card[] caseFile) {
        this.caseFile = caseFile;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}