package Objects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Author - Abel
 * Created - 12/03/2019
 * Note - Created properties and constructor
 * Description - Object for the Character
 *
 * Changed - 15/03/2019 - Abel
 * Description - Added turnOrder property
 */

public abstract class Character extends Card{

    //Properties
    private int startX;
    private int startY;
    private Color color;
    private int turnOrder;

    //Constructor
    public Character(String name, Image img, int startX, int startY, Color color, int turnOrder) {
        super(name, img);
        this.startX = startX;
        this.startY = startY;
        this.color = color;
        this.turnOrder = turnOrder;
    }

    //Getters and Setters
    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }
}
