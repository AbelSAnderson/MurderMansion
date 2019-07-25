package Objects.Characters;

import Objects.Character;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Author - Hasan
 * Created - 11/03/2019
 * Note - Created as a blank file
 * Description - Object for the Yellow Character
 *
 * Changed - 12/03/2019 - Abel
 * Description - Added in the constructor
*/
public class Yellow extends Character {

    public Yellow() {
        super("Poof", new Image("/Resources/Images/Character-Yellow.jpg"), 8, 25, Color.web("#ffc816"), 0);
    }
}
