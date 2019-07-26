package Panes;

import Enums.BackgroundColors;
import Enums.Fonts;
import Scenes.MenuScene;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;

import static Logic.Constants.*;
import static Logic.Main.mainStage;

/**
 * Author - Cordelle
 * Created - 28/03/2019
 * Note - Introductory animation created by Cordelle 01/03/2019 and title minified by Abel 02/03/2019 merged into project
 * Description - Default intro
 * 
 * Changed - 29/03/2019 - Hasan
 * Description - Shortened the end of the animation to speed it up by about 10 seconds
 * 
 * Changed - 29/03/2019 - Cordelle
 * Description - Modified cardAnimate rotation to include card back image; added padding for letter spacing in title; changed GaussianBlur(3) to Bloom(0.2) on 'U'
 *
 * Changed - 29/03/2019 - Abel
 * Description - Minified file
 * 
 * Changed - 12/04/2019 - Cordelle
 * Description - Modified axe rotation and added candle flame animation
 * 
 * Changed - 13/04/2019 - Cordelle
 * Description - added AudioClip for intro background music; sourced by Cordelle
 * 
 */

public class IntroPane extends BorderPane {

	private static Media introThunder;

	// Global variables for cardAnimate rotation
	private ImageView[] cardImagesViews = {new ImageView(new Image("/Resources/Images/candlestick.gif")), new ImageView(new Image("/Resources/Images/Weapon-Arrow.jpg")), new ImageView(new Image("/Resources/Images/axe.png")), new ImageView(new Image("/Resources/Images/Weapon-Poison.jpg"))};
	private Image[] flipImages = {new Image("/Resources/Images/Weapon-Arrow.jpg"), new Image("/Resources/Images/CardBack.jpg")};
	private int indexCardRotationAngle = 0;
	private boolean isSkipped = false;

	public IntroPane() {
		final int IMAGE_BOX_HEIGHT = 300;

		setBackground(BackgroundColors.BACKGROUND_DARK.getBackground());

		VBox centerBox = new VBox();
		HBox firstWordBox = new HBox(8);
		HBox secondWordBox = new HBox();
		HBox thirdWordBox = new HBox();
		HBox imageBox = new HBox();

		firstWordBox.setAlignment(Pos.CENTER);
		secondWordBox.setAlignment(Pos.CENTER);
		thirdWordBox.setAlignment(Pos.CENTER);

		secondWordBox.setPadding(new Insets(0, 0, 0, 0));
		thirdWordBox.setPadding(new Insets(-80, 0, 0, 0));

		//Create the Parallel Transition
		ParallelTransition wordFade = new ParallelTransition();

		//Arrays for text and duration values
		Text[] letters = {new Text("M"), new Text("U"), new Text("R"), new Text("D"), new Text("E"), new Text("R"), new Text(" "), new Text("M"), new Text("A"), new Text("N"), new Text("S"), new Text("I"), new Text("O"), new Text("N")};
		int[] durations = {1500, 2700, 2500, 1000, 1750, 1250, 0, 1550, 2500, 1800, 1000, 2250, 1600, 1500};

		//Create the transitions on each letter
		for (int i = 0; i < letters.length; i++) {
			if(i != 6) {
				FadeTransition letterFade = new FadeTransition(Duration.millis(durations[i]), letters[i]);
				letterFade.setFromValue(1);
				letterFade.setToValue(.4);
				letterFade.setCycleCount(4);
				letterFade.setAutoReverse(true);
				wordFade.getChildren().add(letterFade);
			}
		}

		// Create transition for the 'U' in MURDER
		wordFade.setOnFinished(event1 -> {

			FadeTransition letterFade2b = new FadeTransition(Duration.millis(3000), letters[1]);
			letterFade2b.setFromValue(1);
			letterFade2b.setToValue(0);
			letterFade2b.setCycleCount(1);
			letterFade2b.play();
			
			// audio set to the fade of U from white to red
			introThunder = new Media (new File("src/Resources/Audio/thunder.mp3").toURI().toString());
			MediaPlayer introPlayer = new MediaPlayer(introThunder);
			introPlayer.play();
			
			letterFade2b.setOnFinished(event2 -> {

				letters[1].setFill(Color.RED);
				letters[1].setEffect(new Bloom(0.2));

				FadeTransition letterFade2c = new FadeTransition(Duration.millis(3000), letters[1]);
				letterFade2c.setFromValue(0);
				letterFade2c.setToValue(1);
				letterFade2c.setCycleCount(1);
				letterFade2c.play();

				if(!isSkipped) {
					letterFade2c.setOnFinished(event3 -> mainStage.setScene(new MenuScene()));
				}

			});
		});

		// Add letters to the HBox
		for(Text elementLetter : letters) {
			elementLetter.setFont(Fonts.TITLE_FONT.getFont());
			elementLetter.setFill(Color.WHITE);
			firstWordBox.getChildren().add(elementLetter);
		}

		// Build the subtitles and animations
		Text[] subtitleText = {new Text("H.A.C Programming"), new Text("Hasan, Abel & Cordelle")};

		SequentialTransition subTitleFade = new SequentialTransition();

		for (Text subtitle: subtitleText) {
			subtitle.setFont(Fonts.BLANK_FONT.FellRegular(getClass(), 60));
			subtitle.setFill(Color.GREY);

			FadeTransition fadeSubTitle = new FadeTransition(Duration.millis(4000), subtitle);
			fadeSubTitle.setFromValue(0);
			fadeSubTitle.setToValue(1);
			fadeSubTitle.setCycleCount(2);
			fadeSubTitle.setAutoReverse(true);

			subTitleFade.getChildren().add(fadeSubTitle);
		}

		secondWordBox.getChildren().add(subtitleText[0]);
		thirdWordBox.getChildren().add(subtitleText[1]);

		// Animate the Cards
		for (int i = 0; i < cardImagesViews.length; i++) {
			if(i != 2) {
				cardImagesViews[i].setFitHeight(280);
				cardImagesViews[i].setFitWidth(200);
			} else {
				cardImagesViews[i].setFitHeight(150);
				cardImagesViews[i].setFitWidth(150);
			}
		}

		// Set Transition Animations for the cards and Axe
		int[] durationTimes = {10000, 12000, 12000, 15000};

		imageBox.setTranslateY((IMAGE_BOX_HEIGHT) - 100);

		for (int i = 0; i < cardImagesViews.length; i++) {
			TranslateTransition cardTranslate = new TranslateTransition(Duration.millis(durationTimes[i]), cardImagesViews[i]);
			switch(i) {
				case 0: // CandleStick
					cardTranslate.setFromX(-cardImagesViews[i].getFitWidth()*6);
					break;
				case 1: // Arrow
					cardTranslate.setFromX(-cardImagesViews[i].getFitWidth()*10);
					break;
				case 2: // Axe
					cardTranslate.setFromX(-cardImagesViews[i-1].getFitWidth()*12);
					break;
				case 3: // Poison
					cardTranslate.setFromX(-cardImagesViews[i].getFitWidth()*15);
			}
			if(i == 2) cardTranslate.setToX(SCREEN_WIDTH +  cardImagesViews[i-1].getFitWidth()*10); else cardTranslate.setToX(SCREEN_WIDTH +  cardImagesViews[i].getFitWidth()*2);
			cardTranslate.setCycleCount(1);
			cardTranslate.setAutoReverse(false);
			cardTranslate.play();

			imageBox.getChildren().add(cardImagesViews[i]);
			cardImagesViews[2].setTranslateY(cardImagesViews[2].getTranslateY() + 20);
		}

		// Additional rotation for the Arrow Card as it moves across the screen
		AnimationTimer cardAnimate = new AnimationTimer() {

			int counter = 0;
			int counterHalfTurn = 0;

			@Override
			public void handle(long now) {

				// add rotate
				Rotate cardRotate = new Rotate();
				cardRotate.setAxis(Rotate.Y_AXIS);
				cardRotate.setPivotX(100); // middle of card image 200/2
				cardRotate.setAngle(3);
				cardImagesViews[1].getTransforms().add(cardRotate);

				counter++; // add to count when each angle achieved (+3 per handle)

				// 360/3 = 120 handles = full rotation
				// 120/2 = 60 handles = half card rotation

				if (counter % (30  + indexCardRotationAngle) == 0) { // every 90 degrees (due to every 3rd angle)
					counterHalfTurn++;
					indexCardRotationAngle += 60; // every 180 degrees (due to every 3rd angle)
				}

				if (counterHalfTurn % 2 == 0) {
					cardImagesViews[1].setImage(flipImages[0]); // Arrow card
				} else {
					cardImagesViews[1].setImage(flipImages[1]); // Back of card
				}

			}
		};

		// Additional rotation of axe as it moves across the screen
		AnimationTimer axeAnimate = new AnimationTimer() {

			@Override
			public void handle(long now) {
				cardImagesViews[2].setRotate(cardImagesViews[2].getRotate() + 5);
			}
		};
		
		setOnMouseClicked(event -> {
			isSkipped = true;
			mainStage.setScene(new MenuScene());
		});

		// Play background theme music
		BACKGROUND_MUSIC = new AudioClip(new File("src/Resources/Audio/theme.wav").toURI().toString());
		BACKGROUND_MUSIC.setCycleCount(1);
		BACKGROUND_MUSIC.play();
		
		// Set up the Pane
		setMargin(centerBox, new Insets(150));

		centerBox.getChildren().addAll(firstWordBox, secondWordBox, thirdWordBox, imageBox);
		setCenter(centerBox);

		//Start the Animations
		wordFade.play();
		subTitleFade.play();
		cardAnimate.start();
		axeAnimate.start();
	}
}