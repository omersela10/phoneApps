package phoneApplications;

import java.util.ArrayList;
import java.util.Scanner;

public class DiaryManager implements ContactObserver{
	
	// Data Member
	private Diary diary;
	
	// Constructor
	public DiaryManager() {
		
		this.diary = new Diary();
	}
	
	// Getter
	public Diary getDiary() {
		return this.diary;
	}
	
	// Implement the On contact remove method - Observer Design Pattern
	@Override
	public void onContactRemoved(Contact anyContact) {
		
		// Get all events of the given contact
		ArrayList<Event> allEventsOfGivenContact = this.diary.allEventsWithGivenContact(anyContact);
		
		// Remove all events that related to this contact
		for(Event event : allEventsOfGivenContact) {
			this.diary.removeEvent(event);
		}
	}
	
	// Check if Contact exists in the phone book - Observer Design Pattern
	@Override
	public boolean isContactExists(Contact anyContact) {
		
		return PhoneBookManager.isContactExist(anyContact);
	}
	


}
