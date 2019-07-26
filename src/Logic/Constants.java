package Logic;

import Objects.Character;
import Objects.*;
import Objects.Characters.*;
import Objects.Rooms.*;
import Objects.Weapons.*;
import Panes.GameBoardPanes.GuessSheetPane;
import Panes.GameBoardPanes.InventoryPane;
import javafx.scene.media.AudioClip;

import java.awt.*;
import java.util.ArrayList;

import static Panes.GameBoardPanes.DialoguePane.pauseStage;
import static Panes.GameBoardPanes.GuessesPane.comboBoxes;
import static Panes.MainGamePane.*;


/**
 * Author - Abel
 * Created - 01/03/2019
 * Note - Created file and Added Default Screen Sizes
 * Description - Use this file for any constant values that appear often. Please separate and organize them as needed
 *
 * Changed - 12/03/2019 - Abel
 * Description - Built the 2D Array for the Game Board #Thanks Cordelle for the idea of marking the doors with distinct numbers
 *
 * Changed - 14/03/2019 - Abel
 * Description - Added Fonts Section and Changed Default Screen Size
 * 
 * Changed - 15/03/2019 - Hasan
 * Description - Added 3 arrays with the character, weapon, and room objects
 *
 * Changed - 21/03/2019 - Abel
 * Description - Added function to change the 2D-array into objects
 * 
 * Changed - 28/03/2019 - Cordelle
 * Description - Added fonts for the IntroPane
 *
 * Changed - 29/03/2019 - Abel
 * Description - Added Several Functions to help readability
 *
 * Changed - 30/03/2019 - Abel
 * Description - Changed PLAYERS into an ArrayList
 * 
 * Changed - 06/04/2019 - Cordelle
 * Description - Added CREDITS_SUBTITLE_FONT for the CreditsPane
 */

public class Constants {

    //Player and AI Objects
    public static final ArrayList<Player> PLAYERS = new ArrayList<>();

    //Case File Cards
    public static final Card[] CASE_FILE = new Card[3];
    
    //Arrays for all the Character, Weapon, and Room Objects
    public static final Character[] CHARACTERS = {new Yellow(), new Brown(), new Black(), new Orange(), new Blue(), new Red()};
    public static final Card[] WEAPONS = {new Arrow(), new Axe(), new Candlestick(), new Knife(), new Poison(), new Revolver()};
    public static final Room[] ROOMS = {new Ballroom(), new Conservatory(), new BilliardRoom(), new Library(), new Laboratory(), new Lounge(), new Pool(), new DiningRoom(), new Kitchen()};

    //Default Screen Sizes
    public static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 65;

	//Background Music
    public static AudioClip BACKGROUND_MUSIC;

	//Functions

    /**@return The Current Player.*/
    public static Player CURRENT_PLAYER() {return PLAYERS.get(0);}

    /**@return Current Player's X Position.*/
    public static int X() {return  CURRENT_PLAYER().getCurrentCoordX();}

    /**@return Current Player's Y Position.*/
    public static int Y() {return  CURRENT_PLAYER().getCurrentCoordY();}

    /**@return Tile that the Current Player is on.*/
    public static Tile CURRENT_LOCATION() {return GAMEBOARD_OBJECTS[Y()][X()];}

    /**Resets all Game Variables after winning or returning to the main menu.*/
    public static void resetGame() {
        leftContainer.getChildren().remove(guessSheet);
        guessSheet = new GuessSheetPane(true);

        leftContainer.getChildren().remove(inventory);
        inventory = new InventoryPane(true);

        leftContainer.getChildren().addAll(guessSheet,inventory);

        PLAYERS.clear();
        comboBoxes.clear();
        BACKGROUND_MUSIC.stop();
        pauseStage.close();
    }

    /**
     * 2D Array of the GameBoard
     *
     * Legend: 
     * 0 - walkable space
     * 1 - empty space
     * 2 - Ballroom
     * 3 - Conservatory
     * 4 - Billiard Room
     * 5 - Library
     * 6 - Laboratory
     * 7 - Lounge
     * 8 - Pool
     * 9 - Dining Room
     * 10 - Kitchen
     */
    
    // Coordinates are used as [y][x] instead of [x][y]
    private static final int[][] GAMEBOARD = {
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

    public static final Tile[][] GAMEBOARD_OBJECTS = new Tile[GAMEBOARD.length][GAMEBOARD[0].length];

    /**Turns the 2-D Array of the GameBoard into Objects.*/
    public static void createGameBoardObjects() {

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

                GAMEBOARD_OBJECTS[y][x] = newTile;
            }
        }
    }
}