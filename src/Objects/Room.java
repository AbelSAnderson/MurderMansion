package Objects;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

/**
 * Author - Cordelle
 * Created - 23/03/2019
 * Note - created for audio on the entry (array necessary for multiple audio clips) and exit of rooms by players; audio sourced by Cordelle
 * Description - Object for the Rooms
 */

public class Room extends Card {
	
	// Properties 
	private Media[] entryDoorAudio;		// standard for each room (2 sounds)
	private Media entryAudio;			// room specific
	private Media exitAudio;			// standard for each room (1 sound)
	
	// Constructor
	public Room(String name, Image img, Media entryAudio) {
		super(name, img);
		
		this.entryDoorAudio = new Media[] {new Media (new File("src/Resources/Audio/doorcreak.mp3").toURI().toString()), new Media (new File("src/Resources/Audio/thunder.mp3").toURI().toString())};
		this.entryAudio = entryAudio;
		this.exitAudio = new Media (new File("src/Resources/Audio/doorclose.wav").toURI().toString());
	}

	public Media[] getEntryDoorAudio() {
		return entryDoorAudio;
	}

	public void setEntryDoorAudio(Media[] entryDoorAudio) {
		this.entryDoorAudio = entryDoorAudio;
	}
	
	public Media getEntryAudio() {
		return entryAudio;
	}

	public void setEntryAudio(Media entryAudio) {
		this.entryAudio = entryAudio;
	}

	public Media getExitAudio() {
		return exitAudio;
	}

	public void setExitAudio(Media exitAudio) {
		this.exitAudio = exitAudio;
	}

}