package Panes;

import Enums.BackgroundColors;
import Enums.BackgroundMusic;
import Enums.Fonts;
import Objects.State;
import Scenes.MenuScene;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Author - Cordelle
 * Created - 04/04/2019
 * Note - Exit/closing animation created by Cordelle 02/03/2019
 * Description - Credits with scrolling animation
 * <p>
 * Changed - 06/04/2019 - Cordelle
 * Description - Updated code, animation, and credits
 * <p>
 * Changed - 13/04/2019 - Cordelle
 * Description - added AudioClip for credits background music; stopped intro music (if playing)
 **/

public class CreditsPane extends StackPane {

    private boolean isSkipped = false;

    public CreditsPane(State state) {

        setBackground(BackgroundColors.BACKGROUND_DARK.getBackground());

        HBox firstWordBox = new HBox(8);
        VBox secondWordBox = new VBox();

        firstWordBox.setAlignment(Pos.TOP_CENTER);
        secondWordBox.setAlignment(Pos.CENTER);

        firstWordBox.setBackground(BackgroundColors.BACKGROUND_DARK_TRANSPARENT.getBackground());
        firstWordBox.setMaxHeight(30);
        firstWordBox.setPadding(new Insets(50));

        //Create the Parallel Transition
        ParallelTransition wordFade = new ParallelTransition();

        //Arrays for text and duration values
        char[] titleChars = "MURDER MANSION".toCharArray();
        Text[] letters = new Text[titleChars.length];
        int[] durations = {1500, 3000, 2500, 1000, 1750, 1250, 0, 1550, 2500, 1800, 1000, 2250, 1600, 1500};

        //Create the transitions on each letter
        for (int i = 0; i < titleChars.length; i++) {
            letters[i] = new Text(titleChars[i] + "");

            FadeTransition letterFade = new FadeTransition(Duration.millis(durations[i]), letters[i]);
            letterFade.setFromValue(1);
            letterFade.setToValue(.4);
            letterFade.setCycleCount(6);
            letterFade.setAutoReverse(true);
            wordFade.getChildren().add(letterFade);
        }

        // Create transition for the 'U' in MURDER
        wordFade.setOnFinished(event1 -> {

            FadeTransition letterFade2b = new FadeTransition(Duration.millis(3000), letters[1]);
            letterFade2b.setFromValue(1);
            letterFade2b.setToValue(0);
            letterFade2b.setCycleCount(1);
            letterFade2b.play();

            letterFade2b.setOnFinished(event2 -> {

                letters[1].setFill(Color.RED);
                letters[1].setEffect(new Bloom(0.2));

                FadeTransition letterFade2c = new FadeTransition(Duration.millis(6000), letters[1]);
                letterFade2c.setFromValue(0);
                letterFade2c.setToValue(1);
                letterFade2c.setCycleCount(1);
                letterFade2c.play();

                if (!isSkipped)
                    letterFade2c.setOnFinished(event3 -> state.getMainStage().setScene(new MenuScene(state)));
            });
        });

        // Add letters to the HBox
        for (Text elementLetter : letters) {
            elementLetter.setFont(Fonts.TITLE_FONT.getFont());
            elementLetter.setFill(Color.WHITE);
            firstWordBox.getChildren().add(elementLetter);
        }

        // STEP TWO
        // Identify credits via ArrayList (index positions identified for white subtitles)
        Text[] creditText = {
                new Text("H.A.C Programming"), // 0
                new Text("Hasan Muslemani"),
                new Text("Abel Anderson"),
                new Text("Cordelle Neufeld\n"),

                new Text("St. Clair College in Windsor, Ontario"), // 4
                new Text("Mobile Applications Development\n"),

                new Text("Java 1 Programming"), // 6
                new Text("Câi Filiault\n"),

                new Text("Java 2 Programming"), // 8
                new Text("Nick Sylvestre\n"),

                new Text("Typography"), // 10
                new Text("IM Fell English & IM Fell English SC"),
                new Text("Igino Marini, Principal Design"),
                new Text("Google Fonts\n"),

                new Text("Image Assets\n"), // 14
                new Text("Flat People Vector Collection"), // 15
                new Text("vectorcharacters.net\n"),
                new Text("Vectors for Rooms & Weapons"), // 17
                new Text("vecteezy.com\n"),
                new Text("Checkbox & Checkmarks"), // 19
                new Text("freepik.com\n"),

                new Text("Graphic Design Tools"), // 21
                new Text("Adobe Illustrator CC 2018"),
                new Text("Adobe Photoshop CC 2018"),
                new Text("Microsoft Excel for Mac\n"),

                new Text("Animation Consultants"), //25
                new Text("Ali Dali"),
                new Text("Jimmy (Ndriçim) Strazimiri\n"),

                new Text("Audio Assets"), //28
                new Text("freesound.org\n"),

                new Text("BeeProductive"),
                new Text("bennstir"),
                new Text("BlueDelta"),
                new Text("csaszi"),
                new Text("Enma-Darei"),
                new Text("felix.blume"),
                new Text("f4kf4ce"),
                new Text("giddster"),
                new Text("Jagadamba"),
                new Text("Nomfundo_k"),
                new Text("producerdan"),
                new Text("PsychoPancake"),
                new Text("Setuniman"),
                new Text("soundslikewillem"),
                new Text("XHALE303\n"),

                new Text("Audio Tools"), // 45
                new Text("online-audio-converter.com"),
                new Text("by 123app.com\n"),

                new Text("Integrated Development Environment"), // 48
                new Text("Eclipse Oxygen.3a"),
                new Text("IntelliJ IDEA 2019.1\n"),

                new Text("Collaboration Hub"), // 51
                new Text("slack.com")

        };

        // Apply style to the credits
        // Font Selection by Cordelle Neufeld 2019-03-01
        // Second Font from Google Fonts: IM Fell English
        // https://fonts.google.com/specimen/IM+Fell+English

        //Create the style & transitions for each string

        int[] whiteLines = {0, 4, 6, 8, 10, 14, 15, 17, 19, 21, 25, 28, 45, 48, 51};

        for (Text text : creditText) {
            text.setFont(Fonts.BLANK_FONT.FellRegular(getClass(), 50));
            text.setFill(Color.GREY);

            TranslateTransition creditsAnimation = new TranslateTransition(Duration.millis(25000), text);
            creditsAnimation.setFromY(state.getScreenHeight() * 2 + 500);    // scroll up into the screen
            creditsAnimation.setByY(-state.getScreenHeight() * 5 - 100);  // scroll up offscreen
            creditsAnimation.setByZ(1);
            creditsAnimation.setCycleCount(1);
            creditsAnimation.setAutoReverse(false);
            creditsAnimation.play();
        }

        for (int whiteLine : whiteLines) creditText[whiteLine].setFill(Color.WHITE);

        // Build the secondWordBox
        secondWordBox.getChildren().addAll(creditText);

        setOnMouseClicked(event -> {
            isSkipped = true;
            state.getMainStage().setScene(new MenuScene(state));
        });

        // Play background theme music
        if (state.getBackgroundMusic().isPlaying()) state.getBackgroundMusic().stop();
        state.setBackgroundMusic(BackgroundMusic.THEME.getMusic());
        state.getBackgroundMusic().setCycleCount(1);
        state.getBackgroundMusic().play();

        // Set up the Pane
        getChildren().addAll(secondWordBox, firstWordBox); // in StackPane last child has priority

        //Start the Animations
        wordFade.play();
    }
}