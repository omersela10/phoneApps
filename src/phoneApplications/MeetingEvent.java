package phoneApplications;
import java.util.*;

public class MeetingEvent extends Event{
	// Data members
	//private Event event;
	private Contact contact;
	
	// Constructors
	public MeetingEvent(Date anyDate, int anyDuration, Contact anyContact)
	{
		super(anyDate, anyDuration);
		//Event newEvent = new Event(anyDate, anyDuration);
		//this.event = newEvent;
		Contact newContact = new Contact(anyContact);
		this.contact = newContact;
	}
	
	public MeetingEvent(Event anyEvent, Contact anyContact)
	{
		super(anyEvent);
		//Event newEvent = new Event(anyEvent);
		//this.event = newEvent;
		Contact newContact = new Contact(anyContact);
		this.contact = newContact;
	}
	
	public MeetingEvent(MeetingEvent anyMeetingEvent)
	{
		super(anyMeetingEvent.getDate(), anyMeetingEvent.getDuration());
		//Event newEvent = new Event(anyMeetingEvent.getEvent());
		//this.event = newEvent;
		Contact newContact = new Contact(anyMeetingEvent.getContact());
		this.contact = newContact;
	}
	
	// Getters
	/*public Event getEvent() 
	{
		return this.event;
	}*/
	
	public Contact getContact() 
	{
		return this.contact;
	}
	
	//Setters
	/*public void setEvent(Event otherEvent)
	{
		Event newEvent = new Event(otherEvent);
		this.event = newEvent;
	}*/
	
	public void setContact(Contact otherContact)
	{
		Contact newContact = new Contact(otherContact);
		this.contact = newContact;
	}
	
	public String toString () 
	{	
		return "Event Details:\n" + super.toString() +"Contact: " +this.getContact().toString() + "\n";
	}

}
