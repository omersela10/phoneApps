package phoneApplications;

import java.util.*;
import java.util.Map.Entry;
import java.time.*;

public class DiaryManager
{
	// Data Member
	// Diary Holds Sorted Dates mapped to list of Events in this Date.
	private TreeMap<LocalDate, ArrayList<Event>> diary;
		
	// Constructor
	public DiaryManager() {
		this.diary = new TreeMap<LocalDate, ArrayList<Event>>();
	}

	// Methods :
		
    // Add event
	public void addEvent(Event newEvent) {
		
		// TODO : Add validate
		// Retrieve the Date from the event
		LocalDate newDate = newEvent.getDateTime().toLocalDate();
		// Check if there is any event in this date
		if(this.diary.containsKey(newDate) == true) {
			this.diary.get(newDate).add(newEvent);
		}
		else {
			// Add new Date to the Diary and add this newEvent to this date.
			ArrayList<Event> newEventList = new ArrayList<Event>();
			newEventList.add(newEvent);
			this.diary.put(newDate, newEventList);
		}
		
	}
	
	// Remove event from diary
	public void removeEvent(Event anyEvent) {
		
		// Retrieve the Date from the event
		LocalDate dateOfEvent = anyEvent.getDateTime().toLocalDate();
		
		// Check if there if the Date exist in the Diary
		if(this.diary.containsKey(dateOfEvent) == true) {
			
			// The Date exist, check if the event exist
			for(Event event : this.diary.get(dateOfEvent)) {
				
				if(event.equals(anyEvent) == true) {
					
					// Found, so remove and notify
					this.diary.get(dateOfEvent).remove(anyEvent);
					System.out.println("The event has been deleted.");
					return;
				}
			}
		}
		// Not found. so notify.
		System.out.println("The event doesn't exist in the diary and is therefore not deleted.");
	}
	
	// Help method to print the events in given date.
	private void printEventInDate(LocalDate anyDate) {
		
		// Check that there is anyEvent in this Date
		if(this.diary.containsKey(anyDate) == false) {
			System.out.println("There is no events in this date");
			return;
		}
		// Print all events on this date	
		for(Event event : this.diary.get(anyDate)) {
			
			System.out.println(event);
		}
		
	}
	
	// Help method to print diary
	private void printDiary() {
		
		// Initialize the Iterator
		Iterator<Entry<LocalDate, ArrayList<Event>>> iterator = this.diary.entrySet().iterator();
		
		while(iterator.hasNext()) {
			
			// Print all events on this date
			printEventInDate(iterator.next().getKey());
		}

	}
	
	// Sort by given date
	private void sortByEvent(LocalDate anyDate) {
		
		// Check there is any event on this date
		if(this.diary.containsKey(anyDate) == false) {
			return;
		}
		
		// Initialize Comparator and Sort the Event list in the given Date 
		EventComparator sortEvents = new EventComparator();
		Collections.sort(this.diary.get(anyDate), sortEvents);
	}
	
	// Sort the whole diary
	private void sortDiary() {
		
		// Initialize the comparator and Iterator
		Iterator<Entry<LocalDate, ArrayList<Event>>> iterator = this.diary.entrySet().iterator();
		
		while(iterator.hasNext()) {
			
			// Sort The Events of any Date
			sortByEvent(iterator.next().getKey());
		}
				
	}
	
	// Print specific date sorted
	public void printCertainDatesEvents(LocalDate anyDate) {
		
		// Sort the events in the given date
		this.sortByEvent(anyDate);
		
		// Print all events on this date
		this.printEventInDate(anyDate);
	}
	
	// Print the diary sorted.
	public void printAllEvents() {
		
		// Sort the events in the given date
		this.sortDiary();
		
		// Print all events on this date
		this.printDiary();
	}
	
	// Help method that return the all events that some contact have in specific date.
	private ArrayList<Event> allEventsOfContactInDate(LocalDate anyDate, Contact anyContact){
		
		// Check there is any event on this date
		if(this.diary.containsKey(anyDate) == false) {
			return null;
		}
		ArrayList<Event> allEventsOfContact = new ArrayList<Event>();
		// Search all the events of the given Contact
		for(Event event : this.diary.get(anyDate)) {
			
			if(event instanceof MeetingEvent && ((MeetingEvent) event).getContact().equals(anyContact)) {
				allEventsOfContact.add(event);
			}
		}
		
		return allEventsOfContact;
	}
	
	// Help method retrieve all events with some contact.
	private ArrayList<Event> allEventsWithGivenContact(Contact anyContact) {
		
		// Initialize the Iterator
		Iterator<Entry<LocalDate, ArrayList<Event>>> iterator = this.diary.entrySet().iterator();
		
		// Initialize the Array list that will contain the events with the given Contact
		ArrayList<Event> allEventsOfSomeContact = new ArrayList<Event>();
		
		while(iterator.hasNext()) {
			
			// Add the events that has to the Contact in specific date. (concatenate).
			allEventsOfSomeContact.addAll(allEventsOfContactInDate(iterator.next().getKey(), anyContact));
		}
		
		// Initialize Comparator and sort
		EventComparator sortEvents = new EventComparator();
		Collections.sort(allEventsOfSomeContact,sortEvents);
		
		return allEventsOfSomeContact;
		
	}
	
	// Print all events with some contact.
	public void printAllEventsWithGivenContact(Contact anyContact) {
		ArrayList<Event> allEventsOfSomeContact = allEventsWithGivenContact(anyContact);
		
		for(Event event : allEventsOfSomeContact) {
			System.out.println(event);
		}
	}
	
	// Detect collision and remove the last one.
	public void detectCollisionAndRemoveLast() {
		
		// Initialize the Iterator
		Iterator<Entry<LocalDate, ArrayList<Event>>> iterator = this.diary.entrySet().iterator();
				
		// Find Collision in any dates that are in the diary.
		while(iterator.hasNext()) {
			
			detectCollisionAndRemoveLastInGivenDate(iterator.next().getValue());
		}
		
		
	}
	// Help method that get an list of Events in same date and delete collision if exists.
	private void detectCollisionAndRemoveLastInGivenDate(ArrayList<Event> anyDateList) {
		
		// If there is no more than 1, return
		if(anyDateList.size() <= 1) {
			return;
		}
		
		// Initialize Comparator and sort
		EventComparator sortEvents = new EventComparator();
		Collections.sort(anyDateList,sortEvents);
		
		// Initialize the Iterator and prev, there is at least 2 elements
		Iterator<Event> current = anyDateList.iterator();
	 	Event prev = current.next();
	 	
	 	while(current.hasNext() == true) {
	 		
	 		// Retrieve the previous date time and duration
		 	LocalDateTime previousDateTime = prev.getDateTime();
		    Duration previousDuration = prev.getMeetingDuration();
		    // Adding the duration to the date time
		    LocalDateTime totalDateTimeOfPrevious = previousDateTime.plus(previousDuration);
		    
		    // Retrieve the current Event and update current iterator to next Event
		    Event currentEvent = current.next();
		    // Retrieve the date time of the current event
		    LocalDateTime currentEventDateTime = currentEvent.getDateTime();
		    
		    // Compare the date time of current to the total time of previous
		    if (currentEventDateTime.compareTo(totalDateTimeOfPrevious) < 0) {
		        // Collision found, delete the last one (current)
		    	this.removeEvent(currentEvent);
		    }
		    else {
		    	// No collision found, update previous.
		    	prev = currentEvent;
		    }
		    
		 
	 	}
	    
	}
}
