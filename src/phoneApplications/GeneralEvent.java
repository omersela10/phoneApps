package phoneApplications;
import java.util.*;

public class GeneralEvent extends Event{
	// Data members
		//private Event event;
		private String eventDescription;
		
	// Constructors
	public GeneralEvent(Date anyDate, int anyDuration, String anyEventDescription)
	{
		super(anyDate, anyDuration);
		//Event newEvent = new Event(anyDate, anyDuration);
		//this.event = newEvent;
		this.eventDescription = anyEventDescription;
	}
	
	public GeneralEvent(Event anyEvent, String anyEventDescription)
	{
		super(anyEvent);
		//Event newEvent = new Event(anyEvent);
		//this.event = newEvent;
		this.eventDescription = anyEventDescription;
	}
	
	public GeneralEvent(GeneralEvent anyGeneralEvent)
	{
		super(anyGeneralEvent.getDate(), anyGeneralEvent.getDuration());
		//Event newEvent = new Event(anyGeneralEvent.getEvent());
		//this.event = newEvent;
		this.eventDescription = anyGeneralEvent.getEventDescription();
	}
	
	// Getters
	/*public Event getEvent() 
	{
		return this.event;
	}*/
	
	public String getEventDescription() 
	{
		return this.eventDescription;
	}
	
	//Setters
	/*public void setEvent(Event GeneralEvent)
	{
		Event newEvent = new Event(GeneralEvent);
		this.event = newEvent;
	}*/
	
	public void setEventDescription(String GeneralEventDescription)
	{
		this.eventDescription = GeneralEventDescription;
	}
		
	public String toString () 
	{	
		return "Event Details:\n" + super.toString() + "The event description: " +this.getEventDescription() + "\n";
	}

}
