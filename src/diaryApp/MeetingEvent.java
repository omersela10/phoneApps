package diaryApp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import phoneBookApp.Contact;

public class MeetingEvent extends Event{
	
	// Data members
	private Contact contact;
	
	// Constructors
	public MeetingEvent(LocalDateTime anyDateTime, Duration anyDuration, Contact anyContact) {
		
		super(anyDateTime, anyDuration);
		// MeetingEvent keeps reference to the Contact
		this.contact = anyContact;
	}
	
	public MeetingEvent(Event anyEvent, Contact anyContact) {
		
		super(anyEvent);
		// MeetingEvent keeps reference to the Contact
		this.contact = anyContact;
	}
	
	public MeetingEvent(MeetingEvent anyMeetingEvent)
	{
		super(anyMeetingEvent.getDateTime(), anyMeetingEvent.getMeetingDuration());
		// MeetingEvent keeps reference to the Contact
		this.contact = anyMeetingEvent.getContact();
	}
	
	// Getter
	public Contact getContact() {
		return this.contact;
	}

	@Override
	public String toString () 
	{	
		return "Event Details:\n" + super.toString() + "Meeting with Contact: " + this.getContact();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		// Check if obj is instance of GeneralEvent
		if(obj instanceof MeetingEvent) {
			// Casting
			MeetingEvent anyMeetingEvent = (MeetingEvent)obj;
			return super.equals(anyMeetingEvent) && this.getContact().equals(anyMeetingEvent.getContact());
		}
		
		return false;
	}

}
