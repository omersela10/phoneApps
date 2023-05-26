package phoneApplications;
import java.util.*;
import java.time.*;


public class Content {
	
	private String text; 
	private LocalDateTime time;
	
	// Constructor:
	public Content (String anytext) {
		
		this.setText(anytext);
		this.time = LocalDateTime.now();
	}
	
	// Constructor:
	public Content (Content otherContent) {
		
		this.setText(otherContent.getText());
		this.time = otherContent.getTime();
	}
	
	public String getText() {
		
		return text;
	}
	
	public void setText(String text) {
		
		this.text = text;
	}
	public LocalDateTime getTime() {
		
		return this.time;
	}
	
	@Override
	public String toString () {
		
		return this.getTime() + ": " + this.getText();
		
	}
	
}
