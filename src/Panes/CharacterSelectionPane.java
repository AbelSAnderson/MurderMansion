package Panes;

import Enums.BackgroundColors;
import Main.CreatePlayers;
import Objects.Character;
import Objects.GameState;
import Scenes.GameScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Arrays;

import static Main.Turn.displayTransition;

/**
 * Author - Hasan
 * Created - 14/03/2019
 * Note - This file uses a ListView of the characters with a change listener
 * Description - This file is for the character selection
 *
 * Changed - 14/03/2019 - Abel
 * Description - Shortened Code and modified it to make it more responsive
 */

public class CharacterSelectionPane extends HBox{
	
	private int playerNum = 1;	
	
	public CharacterSelectionPane(GameState gameState) {
				
		Character[] playerChar = new Character[3];
		
		//VBox and HBox to hold the Characters ListView, Select Button, and the Image
		VBox vbox = new VBox(30);
		HBox imgHBox = new HBox();
				
		ArrayList<Character> characters = new ArrayList<>(Arrays.asList(gameState.getCharacters()));

		//Create a ListView, add the characters to it, and select a default value
		ListView<String> characterList = new ListView<>();

		characterList.setMinWidth(300);
		characterList.setMinHeight(284);

		for (Character character: characters) {
			characterList.getItems().add(character.getName());
		}
		characterList.getSelectionModel().selectFirst();

		//Create Button and Image View and Player Number Text
		Text playerNumText = new Text("Player " + playerNum);
		playerNumText.setId("playerText");

		Button selectButton = new Button("Select");
		selectButton.getStyleClass().add("selectButton");
		selectButton.getStyleClass().remove("button");
		ImageView characterImg = new ImageView(characters.get(characterList.getSelectionModel().getSelectedIndex()).getImg());

		//Create an OnChange listener that will change the Image according to the Selected List Item
		characterList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> characterImg.setImage(characters.get(characterList.getSelectionModel().getSelectedIndex()).getImg()));

		//Move to the GameScene on Button Click
		selectButton.setOnAction(e -> { 
			playerNum++;
			playerChar[playerNum - 2] = characters.get(characterList.getSelectionModel().getSelectedIndex());
			
			characters.remove(characterList.getSelectionModel().getSelectedIndex());
			characterList.getItems().remove(characterList.getSelectionModel().getSelectedIndex());
							
			if(playerNum > 3) {
				//Call for the CreatePlayers method and give it the value for the players selected character
				new CreatePlayers(gameState, playerChar);
				
				gameState.getMainStage().setScene(new GameScene(gameState));

				displayTransition(gameState);
			}
			characterList.getSelectionModel().selectFirst();
			characterImg.setImage(characters.get(characterList.getSelectionModel().getSelectedIndex()).getImg());
			playerNumText.setText("Player " + playerNum);
		});

		// Style the background
		setBackground(BackgroundColors.BACKGROUND_DARK.getBackground());

		//Style the selectButton
		selectButton.setPrefHeight(75);
		selectButton.setPrefWidth(200);
		
		playerNumText.setTextAlignment(TextAlignment.CENTER);

		characterList.setMaxHeight((characters.size() * 30) - 4);
		
		//Align all the boxes
		imgHBox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		setAlignment(Pos.CENTER);
		setSpacing(30);
		
		//Add all the nodes to the containers
		imgHBox.getChildren().add(characterImg);
		vbox.getChildren().addAll(playerNumText, characterList, selectButton);
		getChildren().addAll(vbox, imgHBox);
	}
}