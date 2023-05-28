package phoneApplications;

import java.util.*;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.time.*;

public class Diary
{
	// Data Member
	// Diary Holds Sorted Dates mapped to list of Events in this Date.
	private TreeMap<LocalDate, ArrayList<Event>> diary;
		
	// Constructor
	public Diary() {
		this.diary = new TreeMap<LocalDate, ArrayList<Event>>();
	}

	// Methods :
		
    // Add event
	public void addEvent(Event newEvent) {
		
		// Retrieve the Date from the event
		LocalDate newEventDate = newEvent.getDateTime().toLocalDate();
		
		// Check if there is any event in this date
		if(this.diary.containsKey(newEventDate) == true) {
			this.diary.get(newEventDate).add(newEvent);
		}
		else {
			// Add new Date to the Diary and add this newEvent to this date.
			ArrayList<Event> newEventList = new ArrayList<Event>();
			newEventList.add(newEvent);
			this.diary.put(newEventDate, newEventList);
		}
		// Notify insertion.
		JOptionPane.showMessageDialog(null,newEvent + " Added Successfully");
	}
	
	// Remove event from diary
	public void removeEvent(Event anyEvent) {
		
		// Retrieve the Date from the event
		LocalDate dateOfEvent = anyEvent.getDateTime().toLocalDate();
		
		// Check if there if the Date exist in the Diary
		if(this.diary.containsKey(dateOfEvent) == true) {
			
			// The Date exist, check if the event exist in this date
			for(Event event : this.diary.get(dateOfEvent)) {
				
				if(event.equals(anyEvent) == true) {
					
					// Found, so remove and notify
					this.diary.get(dateOfEvent).remove(anyEvent);
					JOptionPane.showMessageDialog(null, "The event has been deleted.");
					return;
				}
			}
		}
		// Not found. so notify.
		JOptionPane.showMessageDialog(null,"The event doesn't exist in the diary and is therefore not deleted.");
	}
	
	// Help method to print the events in given date.
	private void printEventInDate(LocalDate anyDate, JTextArea diaryTextArea) {
		
		// Check that there is anyEvent in this Date
		if(this.diary.containsKey(anyDate) == false) {
			diaryTextArea.append("There is no events in this date");
			return;
		}
		// Print all events on this date	
		for(Event event : this.diary.get(anyDate)) {
			
			diaryTextArea.append(event.toString());
		}
		
	}
	
	// Help method to print diary
	private void printDiary(JTextArea diaryTextArea) {
		
		diaryTextArea.append("The Diary events: \n");
		// Initialize the Iterator
		Iterator<Entry<LocalDate, ArrayList<Event>>> iterator = this.diary.entrySet().iterator();
		
		while(iterator.hasNext() == true) {
			
			// Print all events on this date
			printEventInDate(iterator.next().getKey(), diaryTextArea);
		}

	}
	
	// Sort by given date
	private void sortEventInDate(LocalDate anyDate) {
		
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
		
		while(iterator.hasNext() == true) {
			
			// Sort The Events of any Date
			sortEventInDate(iterator.next().getKey());
		}
				
	}
	
	// Print specific date sorted
	public void printCertainDatesEvents(LocalDate anyDate, JTextArea diaryTextArea) {
		
		// Sort the events in the given date
		this.sortEventInDate(anyDate);
		
		// Print all events on this date
		this.printEventInDate(anyDate, diaryTextArea);
	}
	
	// Print the diary sorted.
	public void printAllEvents(JTextArea diaryTextArea) {
		
		// Sort the events in the given date
		this.sortDiary();
		
		// Print all events on this date
		this.printDiary(diaryTextArea);
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
	public ArrayList<Event> allEventsWithGivenContact(Contact anyContact) {
		
		boolean anyEvent = false;
		// Initialize the Iterator
		Iterator<Entry<LocalDate, ArrayList<Event>>> iterator = this.diary.entrySet().iterator();
		
		// Initialize the Array list that will contain the events with the given Contact
		ArrayList<Event> allEventsOfSomeContact = new ArrayList<Event>();
		
		while(iterator.hasNext() == true) {
			
			ArrayList<Event> allEventsOfSomeContactOfCurrentDate = allEventsOfContactInDate(iterator.next().getKey(), anyContact);
			// Check if there is any event of this date that related to this contact
			if(allEventsOfSomeContactOfCurrentDate != null) {
				// Add the events that has to the Contact in specific date. (concatenate).
				allEventsOfSomeContact.addAll(allEventsOfSomeContactOfCurrentDate);
				anyEvent = true;
			}
		}
		
		if(anyEvent == true) {
			// Initialize Comparator and sort
			EventComparator sortEvents = new EventComparator();
			Collections.sort(allEventsOfSomeContact,sortEvents);
		}
		
		return allEventsOfSomeContact;
		
	}
	
	// Print all events with some contact.
	public void printAllEventsWithGivenContact(Contact anyContact, JTextArea diaryTextArea) {
		
		// Get the all events of given contact
		ArrayList<Event> allEventsOfSomeContact = allEventsWithGivenContact(anyContact);
		
		// Print this events.
		for(Event event : allEventsOfSomeContact) {
			diaryTextArea.append(event.toString());
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
