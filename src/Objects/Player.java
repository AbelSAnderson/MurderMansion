package Objects;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.shape.Circle;

/**
 * Author - Abel
 * Created - 01/03/2019
 * Note - Created as a blank file.
 * Description - This serves as an object for the Player.
 * 
 * Changed - 11/03/2019 - Hasan
 * Description - added the getters and setters along with a constructor for the Player class
 *
 * Changed - 15/03/2019 - Abel
 * Description - added isHuman property
 * 
 * Changed - 23/03/2019 - Cordelle
 * Description - added tokenSound property (creaking floor sound for player movement)
 */

public class Player {
	
	//Properties
	private int xPos;
	private int yPos;
	private Card[] cards;
	private Character character;
	private GuessSheet guessSheet;
	private Circle piece;
	private int rollsLeft;
	private Media tokenSound;
	private Room room;
	private int score;

	//Constructor
	public Player(Card[] cards, Character character) {
		this.score = 1044;
		this.guessSheet = new GuessSheet();
		this.cards = cards;
		this.character = character;
		this.piece = new Circle(10, this.getCharacter().getColor());
		this.getPiece().setTranslateX(4);
		this.rollsLeft = 0;
		this.tokenSound = new Media (new File("src/Resources/Audio/token.mp3").toURI().toString());
	}

	//Getters and Setters
	public Media getTokenSound() {
		return tokenSound;
	}

	public void setTokenSound(Media tokenSound) {
		this.tokenSound = tokenSound;
	}

	public int getRollsLeft() {
		return rollsLeft;
	}

	public void setRollsLeft(int rollsLeft) {
		this.rollsLeft = rollsLeft;
	}

	//Getters and Setters
	public int getXPos() {
		return xPos;
	}

	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public GuessSheet getGuessSheet() {
		return guessSheet;
	}

	public void setGuessSheet(GuessSheet guessSheet) {
		this.guessSheet = guessSheet;
	}

	public Circle getPiece() {
		return piece;
	}

	public void setPiece(Circle piece) {
		this.piece = piece;
	}

    public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}