package Panes;

import Enums.BackgroundColors;
import Enums.Fonts;
import Objects.State;
import Scenes.CharacterSelectionScene;
import Scenes.CreditsScene;
import Scenes.HighScoreScene;
import Scenes.InstructionsScene;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Author - Abel
 * Created - 01/03/2019
 * Note - Created File with Sample Buttons, Font, and Styling
 * Description - This file will is the default menu pane
 * 
 * Changed 12/03/2019 - Hasan
 * Description - Set so if the Play button is clicked, the main stage is the GameScene and if the highScoreButton is clicked, it goes to HighScoreScene
 * 
 * Changed 29/03/2019 - Cordelle
 * Description - Refactored settingsButton to creditsButton to prepare for the addition of the CreditsPane
 * 
 * Changed 06/04/2019 - Cordelle
 * Description - Added mouseClick event to creditsButton for the CreditsScene
 */

public class MenuPane extends VBox {

    public MenuPane(State state) {

        StackPane stackPane = new StackPane();
        VBox buttonContainer = new VBox(40);

        Button[] buttons = new Button[4];
        String[] buttonText = {"Play", "Instructions", "High Scores", "Credits"};

        setBackground(BackgroundColors.BACKGROUND_DARK.getBackground());

        for(int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(buttonText[i]);
            buttons[i].setFont(Fonts.BUTTON_FONT.getFont());
            buttons[i].setPrefWidth(260);
            buttons[i].setPrefHeight(80);

            buttons[i].getStyleClass().add("menuButtons");

            buttons[i].setOnMouseEntered(mouseOverEvent -> setCursor(Cursor.HAND));
            buttons[i].setOnMouseExited(mouseOverEvent -> setCursor(Cursor.CROSSHAIR));


            buttonContainer.getChildren().add(buttons[i]);
        }

        buttons[0].setOnAction(e -> state.getMainStage().setScene(new CharacterSelectionScene(state)));
        buttons[1].setOnAction(e -> state.getMainStage().setScene(new InstructionsScene(state)));
        buttons[2].setOnAction(e -> state.getMainStage().setScene(new HighScoreScene(state)));
        buttons[3].setOnAction(e -> state.getMainStage().setScene(new CreditsScene(state)));

        // add image to the StackPane
        stackPane.getChildren().addAll(new ImageView(new Image("/Resources/Images/menuBackground.png")), buttonContainer);

        buttonContainer.setAlignment(Pos.CENTER);

        stackPane.setAlignment(Pos.CENTER);

        setAlignment(Pos.CENTER);

        getChildren().add(stackPane);
    }
}