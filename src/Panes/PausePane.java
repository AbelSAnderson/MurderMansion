package Panes;

import Scenes.MenuScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static Logic.Constants.*;
import static Logic.Main.mainStage;
import static Panes.GameBoardPanes.DialoguePane.pauseStage;

public class PausePane extends VBox {
	
	public PausePane() {
		HBox answersHBox = new HBox(50);
		
		Text askExitText = new Text("Are you sure you want to exit your current game session and go to the main menu?");
		askExitText.setFont(FellRegular(getClass(), 35));
		askExitText.setFill(Color.WHITE);
		
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		yesButton.setMinSize(120, 40);
		noButton.setMinSize(120, 40);
		
		yesButton.getStyleClass().add("selectButton");
		noButton.getStyleClass().add("selectButton");
		
		yesButton.setOnAction(e -> {
			mainStage.setScene(new MenuScene());
			resetGame();
		});
		
		noButton.setOnAction(e -> pauseStage.close());
		
		answersHBox.getChildren().addAll(yesButton, noButton);
		
		yesButton.setFont(BUTTON_FONT);
		noButton.setFont(BUTTON_FONT);
		answersHBox.setAlignment(Pos.CENTER);
		
		setBackground(BACKGROUND_DARK_TRANSPARENT);
		
		getChildren().addAll(askExitText, answersHBox);
		setSpacing(75);
		setAlignment(Pos.CENTER);
	}
}