package Panes;

import Logic.Main;
import Objects.Card;
import Scenes.HighScoreScene;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static Logic.Constants.*;
import static Panes.GameBoardPanes.Score.saveScore;

public class EndGamePane extends BorderPane {
	
	public static TextField realNameInput;
	
	public EndGamePane() {
		
		VBox textsVBox = new VBox(20);
        HBox centerHBox = new HBox(50);
		HBox winningCards = new HBox(20);
				
		Text playerName = new Text(CURRENT_PLAYER().getCharacter().getName());
		playerName.setFont(FellCursive(getClass(), 65));
		playerName.setFill(Color.WHITE);
		
		ImageView playerImg = new ImageView(CURRENT_PLAYER().getCharacter().getImg());
		playerImg.setFitWidth(250);
		playerImg.setFitHeight(350);
		
		Text winningText = new Text("You're a rookie detective!");
		winningText.setFont(FellCursive(getClass(), 40));
		winningText.setFill(Color.WHITE);
		
		if(CASE_FILE[0].getName().equals(CURRENT_PLAYER().getCharacter().getName())) winningText.setText("You got away with it!");

		realNameInput = new TextField("Please enter your name");
		realNameInput.setAlignment(Pos.CENTER);
		realNameInput.setPrefSize(300, 65);
		realNameInput.setFont(FellRegular(getClass(), 24));
		
		Button submitButton = new Button("Submit");
		submitButton.getStyleClass().add("selectButton");
		
		submitButton.setOnAction(e -> {
			saveScore();
			Main.mainStage.setScene(new HighScoreScene());
			resetGame();
		});
		
		setBackground(BACKGROUND_DARK);

		//Create title: Congratulations
        HBox firstWordBox = new HBox(8);
        firstWordBox.setAlignment(Pos.CENTER);

        //Create the Parallel Transition
        ParallelTransition wordFade = new ParallelTransition();

        //Arrays for text and duration values
        Text[] letters = {new Text("C"), new Text("O"), new Text("N"), new Text("G"), new Text("R"), new Text("A"), new Text("T"), new Text("U"), new Text("L"), new Text("A"), new Text("T"), new Text("I"), new Text("O"), new Text("N"), new Text("S")};
        int[] durations = {1500, 2000, 2500, 1000, 1750, 1250, 1550, 2700, 2500, 1800, 1000, 2250, 1600, 1500, 2000};

        //Create the transitions on each letter
        for (int i = 0; i < letters.length; i++) {
                FadeTransition letterFade = new FadeTransition(Duration.millis(durations[i]), letters[i]);
                letterFade.setFromValue(1);
                letterFade.setToValue(.4);
                letterFade.setCycleCount(2);
                letterFade.setAutoReverse(true);
                wordFade.getChildren().add(letterFade);
        }
        
        wordFade.play();

        // Create transition for the 'U' in CONGRATULATIONS
        wordFade.setOnFinished(event1 -> {

            FadeTransition letterFade2b = new FadeTransition(Duration.millis(3000), letters[7]);
            letterFade2b.setFromValue(1);
            letterFade2b.setToValue(0);
            letterFade2b.setCycleCount(1);
            letterFade2b.play();

            letterFade2b.setOnFinished(event2 -> {

                letters[7].setFill(Color.RED);
                letters[7].setEffect(new Bloom(0.2));

                FadeTransition letterFade2c = new FadeTransition(Duration.millis(3000), letters[7]);
                letterFade2c.setFromValue(0);
                letterFade2c.setToValue(1);
                letterFade2c.setCycleCount(1);
                letterFade2c.play();
            });
        });

        // Add letters to the HBox
        for(Text elementLetter : letters) {
            elementLetter.setFont(TITLE_FONT);
            elementLetter.setFill(Color.WHITE);
            firstWordBox.getChildren().add(elementLetter);
        }

        for (Card card : CASE_FILE) {
            ImageView fileCard = new ImageView(card.getImg());

            fileCard.setFitHeight(168 * 1.2);
            fileCard.setFitWidth(120 * 1.2);
            winningCards.getChildren().add(fileCard);
        }
        		
        textsVBox.setAlignment(Pos.CENTER);
        winningCards.setAlignment(Pos.CENTER);
        centerHBox.setAlignment(Pos.CENTER);

		textsVBox.getChildren().addAll(playerName, winningCards, winningText, realNameInput, submitButton);
		centerHBox.getChildren().addAll(playerImg, textsVBox);
		
		setTop(firstWordBox);
		setCenter(centerHBox);
    }
}