package Objects;

import Enums.Cards;
import Panes.MainGamePane;
import Scenes.EndGameScene;
import Scenes.TransitionScene;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameState {

    //Properties
    private final Tile[][] gameBoard;
    private final Player[] players;
    private final Card[] caseFile;

    private Stage pauseStage;
    private MainGamePane gamePane;
    private int currentPlayerNumber;

    private int blockedX;
    private int blockedY;

    //Constructor
    GameState(Tile[][] gameBoard, Player[] players, Card[] caseFile) {
        this.gameBoard = gameBoard;
        this.players = players;
        this.caseFile = caseFile;
        currentPlayerNumber = 0;
    }

    //Methods

    //Methods for referencing the current Player
    public Player currentPlayer() {
        return getPlayers()[getCurrentPlayerNumber()];
    }
    public int playerX() {
        return currentPlayer().getXPos();
    }
    public int playerY() {
        return currentPlayer().getYPos();
    }
    public Tile currentLocation() {
        return getGameBoard()[playerY()][playerX()];
    }

    //Methods for printing Dialogue
    private void printDialogue() {
        getGamePane().dialoguePane.dialogue.clear();
    }
    private void printDialogue(String text) {
        getGamePane().dialoguePane.dialogue.appendText(text);
    }

    //Main game Methods
    public void endTurn(State state) {
        currentPlayer().setScore(currentPlayer().getScore() - 18);

        getGameBoard()[getBlockedX()][getBlockedY()].setTraversable(true);

        currentPlayer().setRollsLeft(0);

        setCurrentPlayerNumber(getCurrentPlayerNumber() + 1);
        if (getPlayers().length == getCurrentPlayerNumber()) setCurrentPlayerNumber(0);

        getGamePane().guessesPane.disableGuessClicks(true);

        getGamePane().scorePane.scoreNumber.setText("" + currentPlayer().getScore());
        getGamePane().movementPane.rollsText.setText("0");

        switchPlayerUI();
        displayTransition(state);
    }

    /**
     * Switches the Player's UI when Changing Players.
     */
    private void switchPlayerUI() {
        getGamePane().leftContainer.getChildren().remove(getGamePane().guessSheet);
        getGamePane().guessSheet.setGuessSheet(this, true);

        getGamePane().inventory.createInventory(currentPlayer().getCards(), true);

        getGamePane().leftContainer.getChildren().add(1, getGamePane().guessSheet);
    }

    /**
     * Displays the Transition Stage When Changing Players.
     */
    public void displayTransition(State state) {
        printDialogue();
        getGamePane().movementPane.disableButtons(true);

        setPauseStage(new Stage());
        getPauseStage().initStyle(StageStyle.TRANSPARENT);
        getPauseStage().setAlwaysOnTop(true);
        getPauseStage().setScene(new TransitionScene(state));
        getPauseStage().show();

        state.getMainStage().setOnCloseRequest(e -> getPauseStage().close());
        getPauseStage().setOnCloseRequest(e -> startTurn());
    }

    public void startTurn() {
        getGamePane().leftContainer.getChildren().remove(getGamePane().guessSheet);
        getGamePane().guessSheet.setGuessSheet(this, false);

        getGamePane().inventory.createInventory(currentPlayer().getCards(), false);

        getGamePane().leftContainer.getChildren().add(1, getGamePane().guessSheet);

        //Place the piece at the room entrance if the players current moveX and moveY's room number is not -1 (anything not -1 is a room)
        if (getGameBoard()[currentLocation().getMoveCharacterY()][currentLocation().getMoveCharacterX()].getRoomNum() != -1) {
            placeEntranceRoom();
        }

        getPauseStage().close();
        getGamePane().movementPane.disableButtons(false);
        getGamePane().movementPane.setButtons(this);
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
        getGamePane().guessesPane.disableGuessClicks(false);

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

    /**
     * Places the Current Player at the entrance of the Room they are in.
     */
    private void placeEntranceRoom() {
        Tile boardPosition = currentLocation();

        if (!currentLocation().isTraversable()) {
            MediaPlayer exitSound = new MediaPlayer(currentPlayer().getRoom().getExitAudio());
            exitSound.play();

            currentLocation().setHasPlayer(false);
            currentPlayer().setXPos(boardPosition.getMoveCharacterX());
            currentPlayer().setYPos(boardPosition.getMoveCharacterY());

            currentLocation().setTraversable(false);
            setBlockedX(playerX());
            setBlockedY(playerY());

            GridPane.setColumnIndex(currentPlayer().getPiece(), playerX());
            GridPane.setRowIndex(currentPlayer().getPiece(), playerY());
            getGamePane().movementPane.setButtons(this);
        }
    }

    /**
     * Checks if an Accusation is Correct.
     */
    public void correctAccusation(State state) {
        boolean correct = false;

        for (int i = 0; i < getGamePane().guessesPane.comboBoxes.size(); i++) {
            if (getGamePane().guessesPane.comboBoxes.get(i).getSelectionModel().getSelectedItem().equals(getCaseFile()[i].getName())) {
                correct = true;
            } else {
                correct = false;
                currentPlayer().setScore(currentPlayer().getScore() - 144);
                getGamePane().scorePane.scoreNumber.setText(currentPlayer().getScore() + "");
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
        getGamePane().scorePane.scoreNumber.setText(currentPlayer().getScore() + "");

        String[] cardsSelected = new String[3];

        for (int i = 0; i < cardsSelected.length; i++) {
            cardsSelected[i] = getGamePane().guessesPane.comboBoxes.get(i).getSelectionModel().getSelectedItem();
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
    public Tile[][] getGameBoard() {
        return gameBoard;
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

    public Card[] getCaseFile() {
        return caseFile;
    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public void setCurrentPlayerNumber(int currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    public Stage getPauseStage() {
        return pauseStage;
    }

    public void setPauseStage(Stage pauseStage) {
        this.pauseStage = pauseStage;
    }

    public int getBlockedX() {
        return blockedX;
    }

    public void setBlockedX(int blockedX) {
        this.blockedX = blockedX;
    }

    public int getBlockedY() {
        return blockedY;
    }

    public void setBlockedY(int blockedY) {
        this.blockedY = blockedY;
    }
}