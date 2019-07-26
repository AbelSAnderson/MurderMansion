package Objects;

import Objects.Characters.*;
import Objects.Rooms.*;
import Objects.Weapons.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class GameState {

    //Properties
    private Stage mainStage;
    private AudioClip backgroundMusic;
    private double screenWidth;
    private double screenHeight;
    private Tile[][] gameBoard;

    //Player and AI Objects
    private ArrayList<Player> players = new ArrayList<>();

    //Case File Cards
    private Card[] caseFile = new Card[3];

    //Arrays for all the Character, Weapon, and Room Objects
    private Character[] characters;
    private Card[] weapons;
    private Room[] rooms;

    //Constructor
    public GameState(Stage mainStage) {
        this.mainStage = mainStage;

        characters = new Character[] {new Yellow(), new Brown(), new Black(), new Orange(), new Blue(), new Red()};
        weapons = new Card[] {new Arrow(), new Axe(), new Candlestick(), new Knife(), new Poison(), new Revolver()};
        rooms = new Room[] {new Ballroom(), new Conservatory(), new BilliardRoom(), new Library(), new Laboratory(), new Lounge(), new Pool(), new DiningRoom(), new Kitchen()};

        gameBoard = createGameBoard();

        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 65;
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

    //Create the GameBoard
    private Tile[][] createGameBoard() {

        /*
          2D Array of the GameBoard

          Legend:
          0 - walkable space
          1 - empty space
          2 - Ballroom
          3 - Conservatory
          4 - Billiard Room
          5 - Library
          6 - Laboratory
          7 - Lounge
          8 - Pool
          9 - Dining Room
          10 - Kitchen

         Coordinates are used as [y][x] instead of [x][y]
        */

        final int[][] GAMEBOARD = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,0,1,1,1,2,2,2,1,1,0,0,1,1,3,3,3,1,1},
            {1,1,1,10,10,10,1,0,0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0,0,0,3,1,1,1,1,1},
            {1,1,1,1,1,10,1,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1},
            {1,1,0,0,0,0,0,0,0,1,1,1,2,1,1,1,1,0,0,0,0,0,0,0,1,1},
            {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,4,1,4,4,4,1,1},
            {1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1},
            {1,1,1,9,9,9,1,1,1,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1},
            {1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,9,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1},
            {1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,5,1,1,5,5,5,1,1},
            {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
            {1,1,0,0,0,0,0,0,0,0,1,1,1,7,1,1,0,0,0,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,8,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1},
            {1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1},
            {1,1,1,8,8,8,1,1,0,0,1,1,7,7,7,1,0,0,6,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,6,6,6,1,1},
            {1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        Tile[][] tempGameBoard = new Tile[GAMEBOARD.length][GAMEBOARD[0].length];

        int[][] entrances = {{},{},{12,8},{20,6},{19,11},{18,17},{18,22},{13,19},{7,20},{7,16},{5,7}};
        int[][] roomPlacements = {{},{},{12,4},{21,4},{21,11},{21,17},{21,23},{12,22},{3,22},{3,14},{3,5}};

        for (int y = 0; y < GAMEBOARD.length; y++) {
            for (int x = 0; x < GAMEBOARD[y].length; x++) {

                int tileNum = GAMEBOARD[y][x];
                Tile newTile;

                switch(tileNum) {
                    case 0:
                        newTile = new Tile(true);
                        break;
                    case 1:
                        newTile = new Tile(false);
                        break;
                    default:
                        if(y == entrances[tileNum][1] && x == entrances[tileNum][0]) {
                            newTile = new Tile(roomPlacements[tileNum][0], roomPlacements[tileNum][1], true, tileNum);
                        } else {
                            newTile = new Tile(entrances[tileNum][0], entrances[tileNum][1], false, tileNum);
                        }
                }

                tempGameBoard[y][x] = newTile;
            }
        }
        return tempGameBoard;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public AudioClip getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(AudioClip backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(double screenWidth) {
        this.screenWidth = screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(double screenHeight) {
        this.screenHeight = screenHeight;
    }

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

    public Character[] getCharacters() {
        return characters;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public Card[] getWeapons() {
        return weapons;
    }

    public void setWeapons(Card[] weapons) {
        this.weapons = weapons;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }
}