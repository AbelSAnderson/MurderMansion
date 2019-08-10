package Objects;

/**
 * Author - Hasan
 * Created - 11/03/2019
 * Description - Object for the Guess Sheet
 */
public class GuessSheet {

    //Properties
    private int[][] checkedBox = {new int[6], new int[6], new int[9]};

    //Constructor
    GuessSheet() {}

    //Getters and Setters
    public int[][] getCheckedBox() {
        return checkedBox;
    }

    public void setCheckedBox(int[][] checkedBox) {
        this.checkedBox = checkedBox;
    }
}