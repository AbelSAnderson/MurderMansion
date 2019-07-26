package Panes.GameBoardPanes;

import Enums.Fonts;
import Objects.GameState;
import Scenes.PauseScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Description - This file is for the dialogue box that will give live game-play updates
 */

public class DialoguePane extends VBox{
	
	public static TextArea dialogue;
	public static Stage pauseStage;
	
	public DialoguePane(GameState gameState) {
		Button pauseButton = new Button("Exit To Main Menu");
		
		pauseButton.setTranslateX(130);
		
		pauseButton.setOnAction(e -> {
			pauseStage = new Stage();
			pauseStage.initStyle(StageStyle.TRANSPARENT);
			pauseStage.isAlwaysOnTop();
			pauseStage.setScene(new PauseScene(gameState));
			pauseStage.show();
		});
				
		dialogue = new TextArea();
		
		dialogue.setFont(Fonts.BLANK_FONT.FellRegular(getClass(), 16));
		
		dialogue.setEditable(false);
		
		dialogue.setMaxWidth(480);
		dialogue.setMinHeight(250);
		dialogue.setMaxHeight(250);
		
		setSpacing(30);
		setAlignment(Pos.CENTER);
		VBox.setMargin(dialogue, new Insets(0, 25, 0, 25));
		VBox.setMargin(pauseButton, new Insets(30, 0, 20, 0));
		
		getChildren().addAll(pauseButton, dialogue);
	}
}