package Objects.Characters;

import Objects.Character;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Author - Hasan
 * Created - 11/03/2019
 * Note - Created as a blank file
 * Description - Object for the Black Character
 *
 * Changed - 12/03/2019 - Abel
 * Description - Added in the constructor
 */
public class Black extends Character{

    public Black() {
        super("Pearl", new Image("/Resources/Images/Character-Black.jpg"), 10, 1, Color.web("#3c3c3c"), 2);
    }
}
