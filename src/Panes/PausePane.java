package Panes;

import Enums.BackgroundColors;
import Enums.Fonts;
import Objects.State;
import Scenes.MenuScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static Panes.GameBoardPanes.DialoguePane.pauseStage;

public class PausePane extends VBox {
	
	public PausePane(State state) {
		HBox answersHBox = new HBox(50);
		
		Text askExitText = new Text("Are you sure you want to exit your current game session and go to the main menu?");
		askExitText.setFont(Fonts.BLANK_FONT.FellRegular(getClass(), 35));
		askExitText.setFill(Color.WHITE);
		
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		yesButton.setMinSize(120, 40);
		noButton.setMinSize(120, 40);
		
		yesButton.getStyleClass().add("selectButton");
		noButton.getStyleClass().add("selectButton");
		
		yesButton.setOnAction(e -> {
			state.getMainStage().setScene(new MenuScene(state));
			//resetGame(state);
		});
		
		noButton.setOnAction(e -> pauseStage.close());
		
		answersHBox.getChildren().addAll(yesButton, noButton);
		
		yesButton.setFont(Fonts.BUTTON_FONT.getFont());
		noButton.setFont(Fonts.BUTTON_FONT.getFont());
		answersHBox.setAlignment(Pos.CENTER);
		
		setBackground(BackgroundColors.BACKGROUND_DARK_TRANSPARENT.getBackground());
		
		getChildren().addAll(askExitText, answersHBox);
		setSpacing(75);
		setAlignment(Pos.CENTER);
	}
}