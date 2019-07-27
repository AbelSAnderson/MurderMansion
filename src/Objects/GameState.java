package Objects;

public class GameState {

    //Properties
    private Tile[][] gameBoard;
    private Player[] players;
    private Card[] caseFile;

    //Constructor
    public GameState(Tile[][] gameBoard, Player[] players, Card[] caseFile) {
        this.gameBoard = gameBoard;
        this.players = players;
        this.caseFile = caseFile;
    }

    //Methods
    /**@return The Current Player.*/
    public Player currentPlayer() {return players[0];}

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
}