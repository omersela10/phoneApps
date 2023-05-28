package phoneApplications;

import java.time.Duration;

public class Music extends Media {

	// Constructor
	public Music(String newMediaName, Duration newMediaDuration) {
		super(newMediaName, newMediaDuration);
		
	}
	
	@Override 
	public String toString() {
		
		return "The Music : " + super.toString() + " is playing now";
	}

}
