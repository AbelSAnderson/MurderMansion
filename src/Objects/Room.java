package Objects;

import Enums.SoundEffects;
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
		
		this.entryDoorAudio = new Media[] {SoundEffects.DOOR_CREAK.getSound(), SoundEffects.THUNDER.getSound()};
		this.entryAudio = entryAudio;
		this.exitAudio = SoundEffects.DOOR_CLOSE.getSound();
	}

	public Media[] getEntryDoorAudio() {
		return entryDoorAudio;
	}

	public Media getEntryAudio() {
		return entryAudio;
	}

	public Media getExitAudio() {
		return exitAudio;
	}
}