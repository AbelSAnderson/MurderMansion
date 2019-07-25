package Objects.Characters;

import Objects.Character;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Author - Hasan
 * Created - 11/03/2019
 * Note - Created as a blank file
 * Description - Object for the Brown Character
 *
 * Changed - 12/03/2019 - Abel
 * Description - Added in the constructor
 */
public class Brown extends Character {

    public Brown() {
        super("Captain Plank", new Image("/Resources/Images/Character-Brown.jpg"), 1, 18, Color.web("#a05f21"), 1);
    }
}
