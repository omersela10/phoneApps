package phoneApplications;

import java.time.Duration;

public abstract class Media {

	// Data Members
	protected String mediaName;
	protected Duration mediaDuration;
	
	// Constructor
	public Media(String newMediaName, Duration newMediaDuration) {
		this.mediaName = newMediaName;
		this.mediaDuration = newMediaDuration;
	}
	
	// Getters
	public String getMediaName() {
		return mediaName;
	}
	
	public Duration getMediaDuration() {
		return mediaDuration;
	}

	// Setters
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public void setMediaDuration(Duration mediaDuration) {
		this.mediaDuration = mediaDuration;
	}

	@Override 
	public String toString() {
		
		return "Name: " + this.getMediaName() + "Duration: " + this.getMediaDuration();
	}
}
