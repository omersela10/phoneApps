package smsApp;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Message {
	
	// Data Members
	private String text; 
	private LocalDateTime time;
	
	// Constructors
	public Message (String anytext) {
		
		this.setText(anytext);
		this.time = LocalDateTime.now();
	}
	
	public Message (Message otherMessage) {
		
		this.setText(otherMessage.getText());
		this.time = otherMessage.getTime();
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
	
	private String getTimeInFormat() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - MM - yyyy HH:mm");
		return this.getTime().format(formatter);
	}
	
	@Override
	public String toString () {
		
		return this.getTimeInFormat() + ": " + this.getText();
		
	}
	
}
