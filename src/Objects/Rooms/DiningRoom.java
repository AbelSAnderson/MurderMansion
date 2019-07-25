package Objects.Rooms;

import java.io.File;

import Objects.Room;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

/**
 * Author - Abel
 * Created - 14/03/2019
 * Note - Created Constructor
 * Description - Dining Room Object
 *
 * Changed - 22/03/2019 - Abel
 * Description - Object extends Card
 * 
 * Changed - 23/03/2019 - Cordelle
 * Description - Object extends Room in order to incorporate room specific media that is in addition to standard entryAudio & exitAudio (as properties of Room); audio sourced by Cordelle
 */

public class DiningRoom extends Room {

    public DiningRoom() {
        super("Dining Room", new Image("/Resources/Images/Room-DiningRoom.jpg"), new Media (new File("src/Resources/Audio/wine.mp3").toURI().toString()));
    }
}
