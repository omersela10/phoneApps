package smsApp;
import java.util.*;
import java.time.*;


public class Content {
	
	// Data Members
	private String text; 
	private LocalDateTime time;
	
	// Constructors
	public Content (String anytext) {
		
		this.setText(anytext);
		this.time = LocalDateTime.now();
	}
	
	public Content (Content otherContent) {
		
		this.setText(otherContent.getText());
		this.time = otherContent.getTime();
	}
	
	// Getters
	public String getText() {
		
		return text;
	}
	
	public LocalDateTime getTime() {
		
		return this.time;
	}
	
	// Setter
	public void setText(String text) {
		
		this.text = text;
	}
	
	
	@Override
	public String toString () {
		
		return this.getTime() + ": " + this.getText();
		
	}
	
}
