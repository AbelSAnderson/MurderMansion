package Enums;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public enum BackgroundColors {

    BACKGROUND_WHITE(new Background(new BackgroundFill(Color.rgb(255, 255, 255), null, null))),
    BACKGROUND_WHITE_TRANSPARENT(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.8), null, null))),
    BACKGROUND_DARK(new Background(new BackgroundFill(Color.rgb(25, 25, 25), null, null))),
    BACKGROUND_DARK_TRANSPARENT(new Background(new BackgroundFill(Color.rgb(25, 25, 25, 0.9), null, null)));

    private Background background;

    BackgroundColors(Background background) {
        this.background = background;
    }

    public Background getBackground() {
        return background;
    }
}