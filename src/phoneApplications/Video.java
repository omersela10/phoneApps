package phoneApplications;

import java.time.Duration;

public class Video extends Media {

	// Constructor
	public Video(String newMediaName, Duration newMediaDuration) {
		super(newMediaName, newMediaDuration);
			
	}
		
	@Override 
	public String toString() {
			
		return "The Video : " + super.toString() + " is streaming now";
	}

}
