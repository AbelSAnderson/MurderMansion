package Objects.Characters;

import Objects.Character;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Author - Hasan
 * Created - 11/03/2019
 * Note - Created as a blank file
 * Description - Object for the Red Character
 *
 * Changed - 12/03/2019 - Abel
 * Description - Added in the constructor
 */
public class Red extends Character {

    public Red() {
        super("Professor Scarwood", new Image("/Resources/Images/Character-Red.jpg"), 24, 20, Color.web("#c23434"), 5);
    }
}
