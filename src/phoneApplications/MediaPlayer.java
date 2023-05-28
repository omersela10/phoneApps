package phoneApplications;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MediaPlayer {

	// Data Member
	private ArrayList<Media> mediaPlayer;
	
	// Constructor
	public MediaPlayer() {
		this.mediaPlayer = new ArrayList<Media>();
	}
	
	// Getter
	public ArrayList<Media> getMediaPlayer(){
		return this.mediaPlayer;
	}
	
	// Add new Media
	public void addMedia(Media newMedia) {
		
		this.mediaPlayer.add(newMedia);
	}
	
	// Help method that return the first media with the given name
	private Media returnMediaByName(String anyName) {
		
		// Iterate over the media list
		for(Media anyMedia: this.getMediaPlayer()) {
			// Search for this media with given name
			if(anyMedia.getMediaName().equals(anyName) == true) {
				return anyMedia;
			}
		}
		// Not found
		return null;
	}
	
	// Play media by name 
	public void playMediaByName(String givenName, JTextArea mediaTextArea) {
		
		Media theMedia = this.returnMediaByName(givenName);
		// Check if it's exist
		if(theMedia == null) {
			JOptionPane.showMessageDialog(null,"The " + givenName + " Not exist");
			return;
		}
		// Clear all text
		mediaTextArea.setText("");
		mediaTextArea.append(theMedia.toString());
	}
	

 
    // Playing all the media
    public void playingAllMedia(JTextArea mediaTextArea) {
    	
    	// Clear all text
    	mediaTextArea.setText("");
    	mediaTextArea.append("All the media: \n");
    	
    	// Print all the media.
    	for(Media anyMedia: this.getMediaPlayer()) {
 
    		mediaTextArea.append(anyMedia.toString());
    		
    	}
    	
    }
	
}
