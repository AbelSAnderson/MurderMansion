package Objects;

import Objects.Characters.*;
import Objects.Rooms.*;
import Objects.Weapons.*;
import Scenes.GameScene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class State {

    //Properties
    private Stage mainStage;
    private AudioClip backgroundMusic;
    private double screenWidth;
    private double screenHeight;
    private Tile[][] originalGameBoard; //DO NOT modify this gameboard.

    private GameState currentGame;
    private GameScene currentGameScene;

    //Arrays for all the Character, Weapon, and Room Objects
    private Character[] characters;
    private Card[] weapons;
    private Room[] rooms;

    //Constructor
    public State(Stage mainStage) {
        this.mainStage = mainStage;

        characters = new Character[] {new Yellow(), new Brown(), new Black(), new Orange(), new Blue(), new Red()};
        weapons = new Card[] {new Arrow(), new Axe(), new Candlestick(), new Knife(), new Poison(), new Revolver()};
        rooms = new Room[] {new Ballroom(), new Conservatory(), new BilliardRoom(), new Library(), new Laboratory(), new Lounge(), new Pool(), new DiningRoom(), new Kitchen()};

        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 65;
        originalGameBoard = createGameBoard();
    }

    //Methods
    public void createGameState(Character[] characters) {
        ArrayList<Card> masterList = new ArrayList<>();
        Card[] caseFile = createCaseFile(masterList);

        Player[] players = createPlayers(characters, masterList);

        currentGame = new GameState(originalGameBoard.clone(), players, caseFile);
    }

    private Player[] createPlayers(Character[] playerSelection, ArrayList<Card> masterList) {

        int playerNumber = 3; //Change this to the desired number of players

        //ArrayList for all the characters
        ArrayList<Character> characterList = new ArrayList<>(Arrays.asList(getCharacters()));

        for (Character character : playerSelection) {
            characterList.remove(character);
        }

        //Create New List with only the Selections
        ArrayList<Character> playerList = new ArrayList<>(Arrays.asList(getCharacters()));
        playerList.removeAll(characterList);

        //Get Random Cards for Players
        Card[][] playerCards = createPlayerCards(masterList, playerNumber);

        Player[] players = new Player[playerNumber];

        //Create and Set Players
        for (int i = 0; i < playerList.size(); i++) {
            players[i] = new Player(playerCards[i], playerList.get(i));
            players[i].setCurrentCoordX(players[i].getCharacter().getStartX());
            players[i].setCurrentCoordY(players[i].getCharacter().getStartY());
        }

        return players;
    }

    private Card[] createCaseFile(ArrayList<Card> masterList) {

        //Declare the ArrayList to hold all the cards
        Card[] caseFile = new Card[3];

        //Declare Array holding the ArrayLists of Characters, Weapons, and Rooms
        List[] cardsArray = {new ArrayList<>(Arrays.asList(getCharacters())), new ArrayList<>(Arrays.asList(getWeapons())), new ArrayList<>(Arrays.asList(getRooms()))};

        //Loop through cardsArray, Shuffling each ArrayList, Setting the Case File with unique cards, and adding the ArrayList to masterList
        for (int i = 0; i < cardsArray.length; i++) {
            Collections.shuffle(cardsArray[i]);

            caseFile[i] = (Card) cardsArray[i].get(0);

            System.out.println(((Card) cardsArray[i].get(0)).getName()); //TESTING ONLY

            cardsArray[i].remove(0);

            masterList.addAll(cardsArray[i]);
        }

        return caseFile;
    }

    /**
     * @author Hasan
     * @since 16/03/2019
     * @return 2-D array that holds three sets of six cards for each Player.
     */
    private Card[][] createPlayerCards(ArrayList<Card> masterList, int playerNumber) {

        //Shuffle masterList
        Collections.shuffle(masterList);

        //Calculate the amount of cards for each player
        int cardAmount = (int) Math.floor(masterList.size() / playerNumber);

        //Create 2D-Array for the Player's Cards
        Card[][] playersCards = new Card[playerNumber][];

        //Give each player a different set of cards
        for (int i = 0; i < playersCards.length; i++) {
            playersCards[i] = new Card[cardAmount];
            for(int j = 0; j < playersCards[i].length; j++) {
                playersCards[i][j] = masterList.get(j + cardAmount*i);
            }
        }

        return playersCards;
    }

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


    //Getters and Setters
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

    public GameState getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(GameState currentGame) {
        this.currentGame = currentGame;
    }

    public GameScene getCurrentGameScene() {
        return currentGameScene;
    }

    public void setCurrentGameScene(GameScene currentGameScene) {
        this.currentGameScene = currentGameScene;
    }

    public Tile[][] getOriginalGameBoard() {
        return originalGameBoard;
    }

    public void setOriginalGameBoard(Tile[][] originalGameBoard) {
        this.originalGameBoard = originalGameBoard;
    }
}