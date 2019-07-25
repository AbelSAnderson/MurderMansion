package Objects.Characters;

import Objects.Character;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Author - Hasan
 * Created - 11/03/2019
 * Note - Created as a blank file
 * Description - Object for the Blue Character
 *
 * Changed - 12/03/2019 - Abel
 * Description - Added in the constructor
 */
public class Blue extends Character {

    public Blue() {
        super("Lieutenant Blue", new Image("/Resources/Images/Character-Blue.jpg"), 24, 7, Color.web("#4d5c91"), 4);
    }
}
