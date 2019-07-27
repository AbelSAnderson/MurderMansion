package Panes;

import Enums.BackgroundColors;
import Enums.Fonts;
import Objects.State;
import Scenes.MenuScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Author - Hasan
 * Created - 14/03/2019
 * Note - This file uses a GridPane but we're looking into a TableView
 * Description - This file holds all the High Scores of the user
 *
 * Changed - 16/03/2019 - Abel
 * Description - Shortened Code
 */

public class HighScorePane extends VBox {

	public HighScorePane(State state) {
		
		File highScoreFile = new File("highScores.txt");

		GridPane gridPane = new GridPane();

		//Arrays for the Scores
		Text[] scorePos = new Text[10];
		Text[] scoreNames = new Text[10];
		Text[] scoreText = new Text[10];
		//Create a hash map for the high score's name and score pairs
		HashMap<String, Integer> hashScore = new HashMap<>();

		Font scoreFont = Fonts.BLANK_FONT.FellRegular(getClass(), 40);

		//Fill Arrays with default Values and Add to the GridPane
		for (int i = 0; i < scorePos.length; i++) {
			scorePos[i] = new Text((i + 1) + "");
			scorePos[i].setFont(scoreFont);
			
			scoreNames[i] = new Text("Player");
			scoreNames[i].setFont(scoreFont);
			
			scoreText[i] = new Text("0");
			scoreText[i].setFont(scoreFont);

			gridPane.add(scorePos[i], 0, i);
			gridPane.add(scoreNames[i], 1, i);
			gridPane.add(scoreText[i], 2, i);
		}
		
		scoreText[0].setFill(Color.RED);
		
		try {
			Scanner fileScan = new Scanner(highScoreFile);

			while(fileScan.hasNext()) {
				hashScore.put(fileScan.next(), fileScan.nextInt());
			}
			
			//sort the hash map into descending order using this method
			HashMap<String, Integer> newHashedMap = sortScores(hashScore);
			
			int j = 0;
			
			for (HashMap.Entry<String, Integer> hashList : newHashedMap.entrySet()) { 
				if(j < scorePos.length) {
					scoreNames[j].setText(hashList.getKey().substring(0, Math.min(hashList.getKey().length(), 16)));
					scoreText[j].setText(hashList.getValue()+ "");
				}
				j++;
	        }

			fileScan.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Text highScoreText = new Text("High Scores");
		
		highScoreText.setFont(Fonts.BLANK_FONT.FellCursive(getClass(), 60));
		highScoreText.setFill(Color.WHITE);
				
		Button backButton = new Button ("Back to Menu");
		backButton.getStyleClass().add("selectButton");
		
		backButton.setOnAction(e -> state.getMainStage().setScene(new MenuScene(state)));
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(5);
		gridPane.setHgap(75);
		gridPane.setMaxWidth(200);
		gridPane.setPadding(new Insets(30));
		gridPane.setBackground(BackgroundColors.BACKGROUND_WHITE.getBackground());
		
		setBackground(BackgroundColors.BACKGROUND_DARK.getBackground());
		getChildren().addAll(highScoreText, gridPane, backButton);
		setAlignment(Pos.CENTER);
		setSpacing(30);
	}

	/**
	 * Sorts a HashMap of high scores highest to lowest.
	 * @param hashScore HashMap with the unsorted Scores.
	 * @return HashMap with the sorted High Scores.
	 */
	private static HashMap<String, Integer> sortScores(HashMap<String, Integer> hashScore) {
        //Create the hash map from the list
        List<Map.Entry<String, Integer> > list = new LinkedList<>(hashScore.entrySet());
  
        //sort the list in descending order
        list.sort((value1, value2) -> (value2.getValue()).compareTo(value1.getValue()));
          
        //hash the data together from the list
        HashMap<String, Integer> hashMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> hashList : list) { 
            hashMap.put(hashList.getKey(), hashList.getValue());
        } 
        return hashMap;
    }
}