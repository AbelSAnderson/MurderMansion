package Objects;

import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class GameState {

    private Stage mainStage;
    private AudioClip backgroundMusic;

    public GameState(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public AudioClip getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(AudioClip backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }
}