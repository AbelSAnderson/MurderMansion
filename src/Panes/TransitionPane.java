package Panes;

import Enums.BackgroundColors;
import Enums.Fonts;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static Logic.Constants.*;
import static Logic.Turn.startTurn;

public class TransitionPane extends HBox {

    public TransitionPane() {
    	
    	ImageView characterImg = new ImageView(CURRENT_PLAYER().getCharacter().getImg());
    	characterImg.setFitWidth(178);
    	characterImg.setFitHeight(250);
    	
    	VBox vbox = new VBox(40);

        Text transitionMessage = new Text(CURRENT_PLAYER().getCharacter().getName() + "'s Turn"); 
        
        Button transitionButton = new Button("Start Turn");
        transitionButton.getStyleClass().add("selectButton");
        
        transitionMessage.setFont(Fonts.BLANK_FONT.FellRegular(getClass(), 35));
        transitionButton.setFont(Fonts.BUTTON_FONT.getFont());

        transitionButton.setOnMouseClicked(e -> startTurn());
        
        vbox.getChildren().addAll(transitionMessage, transitionButton);
        vbox.setAlignment(Pos.CENTER);
        
        setBackground(BackgroundColors.BACKGROUND_WHITE_TRANSPARENT.getBackground());

        getChildren().addAll(characterImg, vbox);
        setAlignment(Pos.CENTER);
        setSpacing(60);
    }
}