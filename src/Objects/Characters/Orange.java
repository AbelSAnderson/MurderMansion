package Objects.Characters;

import Objects.Character;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Author - Hasan
 * Created - 11/03/2019
 * Note - Created as a blank file
 * Description - Object for the Orange Character
 *
 * Changed - 12/03/2019 - Abel
 * Description - Added in the constructor
 */

public class Orange extends Character {

    public Orange() {
        super("Phoenix", new Image("/Resources/Images/Character-Orange.jpg"), 15, 1, Color.web("#f78630"), 3);
    }
}
