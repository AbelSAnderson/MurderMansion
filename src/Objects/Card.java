package Objects;

import javafx.scene.image.Image;

/**
 * Author - Hasan
 * Created - 11/03/2019
 * Note - created as a blank file
 * Description - Object for the Cards
 *
 * Changed - 12/03/2019 - Abel
 * Description - Added in properties and a constructor
 */

public abstract class Card {
    //Properties
    private Image img;
    private String name;

    //Constructor
    public Card(String name, Image img) {
        this.name = name;
        this.img = img;
    }

    //Getters and Setters
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
