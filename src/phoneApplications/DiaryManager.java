package phoneApplications;

import java.util.ArrayList;

public class DiaryManager implements ContactObserver, AppHandler{
	
	// Data Member
	private Diary diary;
	
	// Constructor
	public DiaryManager() {
		
		this.diary = new Diary();
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
	
	@Override
	public void startApp() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void printOptions() {
		// TODO Auto-generated method stub
		
	}
	


}
