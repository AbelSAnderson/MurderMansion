package Panes;

import Enums.BackgroundColors;
import Enums.Fonts;
import Scenes.MenuScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static Logic.Main.mainStage;

/**
 * Author - Hasan
 * Created - 12/04/2019
 * Note - Created pane for instructions accessible from the the MenuPane
 * 
 * Changed 12/04/2019 - Cordelle
 * Description - Added instructions modified from group-work with Hasan and Abel on 10/04/2019 during lab (power outage)
 * 
 */

public class InstructionsPane extends VBox {

    public InstructionsPane() {
    	
    	Text instructionsLabel = new Text("Instructions");
    	instructionsLabel.setFill(Color.WHITE);
		instructionsLabel.setFont(Fonts.BLANK_FONT.FellRegular(getClass(), 60));
    	
    	VBox instructionsVBox = new VBox();
    	HBox containerHBox = new HBox();
    	
    	instructionsVBox.setMaxSize(700, 950);
    	instructionsVBox.setMinSize(700, 950);
    	
    	Button menuButton = new Button("Back to Main Menu");
    	menuButton.getStyleClass().add("selectButton");
    	
    	menuButton.setOnAction(e -> mainStage.setScene(new MenuScene()));
    	
    	Text[] instructionsText = {
    			new Text("It's a dark and stormy night."),
    			new Text("The butler has gone missing and is presumed murdered."),
    			new Text("Perhaps he saw something he shouldn't have.\n"),
    			
    			new Text("There are 6 suspects, including you."),
    			new Text("The goal of the game is to make a correct accusation"),
    			new Text("that names the suspect, weapon, and room.\n"),
    			
    			new Text("Start Game"), // index 6
    			new Text("Each player selects a character card."),
    			new Text("After 3 players have selected a character card,"),
    			new Text("a prompt will let you know each player's turn."),
    			new Text("Remember to hide your screen from the other players.\n"),
    			
    			new Text("Play Game"), // index 11
    			new Text("Look at the 6 cards you have been dealt."),
    			new Text("You can use the checklist above your cards to keep track of the cards you know."),
    			new Text("Roll dice. The number you roll is the number of moves you can make."),
    			new Text("Find the circle that is your character's color on the gameboard."),
    			new Text("Move your character using the direction buttons on the bottom right."),
    			new Text("Enter a room in which you think the murder occurred.\n"),
    			
    			new Text("When you enter a room, 3 lists above the direction buttons turn white."),
    			new Text("Do you think you know who done it?"), // index 19
    			new Text("Select a suspect, a weapon, and the room you are currently in."),
    			new Text("Then, make a suggestion or an accusation."),
    			new Text("There are key differences between a suggestion and an accusation."),
    			new Text("A suggestion will return 1 card that the other players have in their hands."),
    			new Text("If no cards are returned, then no other players hold those cards."),
    			new Text("36 points are deducted from your score each time you make a suggestion."),
    			new Text("An accusation will not return a card."),
    			new Text("If you are correct, you win. If you are not correct, you will lose 144 points."),
    			new Text("Your score is shown at the top left. You start with 1,044 points."),
    			new Text("10-44 is the code for murder used by the Ontario Provincial Police.\n"),
    			
    			new Text("Gameplay continues after your suggestion or wrong accusation."),
    			new Text("18 points are deducted from your score for each turn.\n"),
    			
    			new Text("Click the end turn button."),
    	};

    	int[] redLines = {6, 11, 19};

		for (Text text : instructionsText) {
			text.setFont(Fonts.BLANK_FONT.FellRegular(getClass(), 18));
			text.setFill(Color.BLACK);

			instructionsVBox.getChildren().add(text);
		}

    	for(int redLine : redLines) instructionsText[redLine].setFill(Color.RED);
    	
    	Image leftImage = new Image("/Resources/Images/instructionPoof.png");
    	ImageView leftImageView = new ImageView(leftImage);
    	
    	Image rightImage = new Image("/Resources/Images/instructionCharacters.png");
    	ImageView rightImageView = new ImageView(rightImage);
    	
    	instructionsVBox.setPadding(new Insets(25, 50, 25, 50));
    	setPadding(new Insets(25, 0, 0 ,0));
    	
    	instructionsVBox.setBackground(BackgroundColors.BACKGROUND_WHITE.getBackground());

    	containerHBox.getChildren().addAll(leftImageView, instructionsVBox, rightImageView);
    	containerHBox.setAlignment(Pos.TOP_CENTER);
    	
    	getChildren().addAll(instructionsLabel, containerHBox, menuButton);
    	VBox.setMargin(menuButton, new Insets(15));
		setBackground(BackgroundColors.BACKGROUND_DARK.getBackground());
    	setAlignment(Pos.TOP_CENTER);
    }
}