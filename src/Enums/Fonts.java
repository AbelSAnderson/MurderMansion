package Enums;

import Panes.IntroPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public enum Fonts {

    BUTTON_FONT (javafx.scene.text.Font.font("times new roman", FontWeight.BOLD, 25)),
    NUMBER_FONT (javafx.scene.text.Font.font("Arial", 40)),

    // Font Selection by Cordelle Neufeld 2019-03-01
    // First Font from Google Fonts: IM Fell English SC
    // https://fonts.google.com/specimen/IM+Fell+English+SC
    TITLE_FONT (javafx.scene.text.Font.loadFont(IntroPane.class.getResource("/Resources/Fonts/fontFellEnglishTitle.ttf").toExternalForm(), 80));

    private Font font;

    Fonts (Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}