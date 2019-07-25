package Objects;

/**
 * Author - Abel
 * Created - 21/03/2019
 * Note - Created Object Properties and Constructors
 * Description - Object for each tile on the Game Board
 */

public class Tile {

    //Properties
    private int roomNum;
    private boolean isTraversable;
    private int moveCharacterX;
    private int moveCharacterY;
    private boolean hasPlayer;

	//Constructors
    public Tile(boolean isTraversable) {
        this.isTraversable = isTraversable;
        this.roomNum = -1;
    }

    public Tile(int moveCharacterX, int moveCharacterY, boolean isTraversable, int roomNum) {
        this.roomNum = roomNum;
        this.isTraversable = isTraversable;
        this.moveCharacterX = moveCharacterX;
        this.moveCharacterY = moveCharacterY;
    }

    //Getters & Setters
    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public boolean isTraversable() {
        return isTraversable;
    }

    public void setTraversable(boolean traversable) {
        isTraversable = traversable;
    }

    public int getMoveCharacterX() {
        return moveCharacterX;
    }

    public void setMoveCharacterX(int moveCharacterX) {
        this.moveCharacterX = moveCharacterX;
    }

    public int getMoveCharacterY() {
        return moveCharacterY;
    }

    public void setMoveCharacterY(int moveCharacterY) {
        this.moveCharacterY = moveCharacterY;
    }

	public boolean hasPlayer() {
		return hasPlayer;
	}

	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}
}
