package Enums;

import javafx.scene.media.Media;

import java.io.File;

public enum SoundEffects {

    THUNDER(new Media(new File("src/Resources/Audio/thunder.mp3").toURI().toString())),
    DOOR_CREAK(new Media (new File("src/Resources/Audio/doorCreak.mp3").toURI().toString())),
    DOOR_CLOSE(new Media (new File("src/Resources/Audio/doorClose.wav").toURI().toString()));

    private Media sound;

    SoundEffects(Media sound) {
        this.sound = sound;
    }

    public Media getSound() {
        return sound;
    }
}