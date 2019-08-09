package Panes.GameBoardPanes;

import Enums.Fonts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Author - Hasan
 * Created - 12/03/2019
 * Description - This file is for the user's score
 */

public class ScorePane extends HBox {

    public Text scoreNumber;

    public ScorePane() {
        scoreNumber = new Text("1044");
        scoreNumber.setFont(Fonts.NUMBER_FONT.getFont());
        scoreNumber.setFill(Color.WHITE);

        Text scoreText = new Text("Score: ");


        scoreText.setFont(Fonts.BLANK_FONT.FellCursive(getClass(), 50));
        scoreText.setFill(Color.BROWN);

        HBox.setMargin(scoreText, new Insets(0, 0, 0, 25));

        setAlignment(Pos.BASELINE_LEFT);
        getChildren().addAll(scoreText, scoreNumber);
    }
}