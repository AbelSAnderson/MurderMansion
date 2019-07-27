package Objects;

import java.util.ArrayList;

public class GameState {

    //Properties
    private Tile[][] gameBoard;
    private ArrayList<Player> players;
    private Card[] caseFile;

    //Constructor
    public GameState(Tile[][] gameBoard, ArrayList<Player> players, Card[] caseFile) {
        this.gameBoard = gameBoard;
        this.players = players;
        this.caseFile = caseFile;
    }

    //Methods
    /**@return The Current Player.*/
    public Player currentPlayer() {return players.get(0);}

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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Card[] getCaseFile() {
        return caseFile;
    }

    public void setCaseFile(Card[] caseFile) {
        this.caseFile = caseFile;
    }
}