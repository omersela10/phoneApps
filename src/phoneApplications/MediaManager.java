package phoneApplications;

public class MediaManager {

	// Data Member
	private MediaPlayer mediaPlayer;
	
	// Constructor
	public MediaManager() {
		this.mediaPlayer = new MediaPlayer();
	}
	
	// Getter
	public MediaPlayer getMedaiaPlayer() {
		return this.mediaPlayer;
	}
}
