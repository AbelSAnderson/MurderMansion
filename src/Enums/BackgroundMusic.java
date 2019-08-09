package Enums;

import javafx.scene.media.AudioClip;

import java.io.File;

public enum BackgroundMusic {

    BACKGROUND_MUSIC(new AudioClip(new File("src/Resources/Audio/background.wav").toURI().toString())),
    THEME(new AudioClip(new File("src/Resources/Audio/theme.wav").toURI().toString()));

    private AudioClip music;

    BackgroundMusic(AudioClip music) {
        this.music = music;
    }

    public AudioClip getMusic() {
        return music;
    }
}
