package Panes.GameBoardPanes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static Logic.Constants.*;
import static Panes.EndGamePane.realNameInput;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Description - This file is for the user's score
 */

public class Score extends HBox{
	
	public static Text scoreNumber;
	
	public Score() {
	
		scoreNumber = new Text("1044");
		scoreNumber.setFont(NUMBER_FONT);
		scoreNumber.setFill(Color.WHITE);
		
		Text scoreText = new Text("Score: ");
		
		
		scoreText.setFont(FellCursive(getClass(), 50));
		scoreText.setFill(Color.BROWN);
		
		HBox.setMargin(scoreText, new Insets(0, 0, 0, 25));
		
		setAlignment(Pos.BASELINE_LEFT);
		getChildren().addAll(scoreText, scoreNumber);
	}

	/**Saves score to the text file*/
	public static void saveScore() {
		
		File file = new File("highScores.txt");
		
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			
			fileWriter.append(realNameInput.getText()).append(" ");
			fileWriter.append(String.valueOf(CURRENT_PLAYER().getScore())).append(" ");
			
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}