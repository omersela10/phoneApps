package phoneApplications;

import java.util.ArrayList;
import java.util.Collections;

public class Diary 
{
	// Data Members
	private ArrayList<Event> diary;
		
		
	// Constructor
	public Diary() 
	{	
		this.diary = new ArrayList<Event>();
	}

	// Methods :
		
    // Add event
	public void addEvent(Event anyEvent, ContactList ourList)
	{
		
		if(anyEvent instanceof MeetingEvent)
		{
			MeetingEvent newMeetingEvent = new MeetingEvent((MeetingEvent)anyEvent);
			if(ourList.searchByName(newMeetingEvent.getContact().getName()))
				{
				this.diary.add(newMeetingEvent);
				System.out.println("Event added.\n");
				}
			else
			{
				System.out.println("Error: A meeting can only be with a contact.\n");
			}
			
		}
		else 
		{
			GeneralEvent newGeneralEvent = new GeneralEvent((GeneralEvent)anyEvent);
			this.diary.add(newGeneralEvent);
			System.out.println("Event added.\n");
		}
		
	}
	
	public void removeEvent(Event anyEvent)
	{
		boolean found = false;
		if(anyEvent instanceof MeetingEvent)
		{
			for(Event event : this.diary) 
			{
				if(event instanceof GeneralEvent)
					continue;
				if(event.ifEqualEvent(anyEvent) && (((MeetingEvent)anyEvent).getContact().equals(((MeetingEvent)event).getContact())))
				{
					// Found and remove
					found = true;
					this.diary.remove(event);
					break;
				}
			}
		}
		else 
		{
			for(Event event : this.diary) 
			{
				if(event instanceof MeetingEvent)
					continue;
				if(event.ifEqualEvent(anyEvent) && (((GeneralEvent)anyEvent).getEventDescription().equals(((GeneralEvent)event).getEventDescription())))
				{
					// Found and remove
					found = true;
					this.diary.remove(event);
					break;
				}
			}
		}
		
		if(found)
			System.out.println("The event has been deleted.");
		else
			System.out.println("The event doesn't exist in the diary and is therefore not deleted.");
		
	}
	
	public void printDiary()
	{
		System.out.println("Events in the diary:");
		int count=1;
		for(Event event : this.diary) 
		{
			System.out.println("#" + count +":");
			if(event instanceof MeetingEvent)
				System.out.println(((MeetingEvent)event).toString());
			else
				System.out.println(((GeneralEvent)event).toString());
			count++;
		}
	
	}
	
	public void sortByDate()
	{
		// Initialize the comparator 
		DateEventComparator sortByDate = new DateEventComparator();
				
		// Sort By date
		Collections.sort(this.diary, sortByDate);
				
		System.out.println("Diary sorted by name.");
	}

	public void printCertainDatesEvents(int year, int month, int day, Diary anyDiary)
	{
		sortByDate(anyDiary);
		for(Event event : this.diary) 
		{
			if(event.getDate().compareDay(year, month, day))
			{
				if(event instanceof MeetingEvent)
					System.out.println(((MeetingEvent)event).toString());
				else
					System.out.println(((GeneralEvent)event).toString());
			}
			
		}
	}
}
