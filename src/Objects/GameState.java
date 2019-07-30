package Objects;

import Scenes.GameScene;

public class GameState {

    //Properties
    private Tile[][] gameBoard;
    private GameScene gameScene;
    private Player[] players;
    private Card[] caseFile;
    private int currentPlayer;

    //Constructor
    public GameState(Tile[][] gameBoard, GameScene gameScene, Player[] players, Card[] caseFile) {
        this.gameBoard = gameBoard;
        this.gameScene = gameScene;
        this.players = players;
        this.caseFile = caseFile;
        currentPlayer = 0;
    }

    //Methods
    /**@return The Current Player.*/
    public Player currentPlayer() {return players[currentPlayer];}

    /**@return Current Player's X Position.*/
    public int playerX() {return  currentPlayer().getCurrentCoordX();}

    /**@return Current Player's Y Position.*/
    public int playerY() {return  currentPlayer().getCurrentCoordY();}

    /**@return Tile that the Current Player is on.*/
    public Tile currentLocation() {return getGameBoard()[playerY()][playerX()];}


    //Getters and Setters
    public Tile[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Tile[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public void setGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
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