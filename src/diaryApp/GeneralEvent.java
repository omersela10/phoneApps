package diaryApp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class GeneralEvent extends Event{
	
	// Data members
	private String eventDescription;
		
	// Constructors
	public GeneralEvent(LocalDateTime anyDateTime, Duration anyDuration, String anyEventDescription) {
		super(anyDateTime, anyDuration);
		this.eventDescription = anyEventDescription;
	}
	
	public GeneralEvent(Event anyEvent, String anyEventDescription) {
		super(anyEvent);
		this.eventDescription = anyEventDescription;
	}

	public GeneralEvent(GeneralEvent anyGeneralEvent) {
		super(anyGeneralEvent.getDateTime(), anyGeneralEvent.getMeetingDuration());
		this.eventDescription = anyGeneralEvent.getEventDescription();
	}
	
	// Getters
	public String getEventDescription() {
		return this.eventDescription;
	}
	
	// Setters

	public void setEventDescription(String GeneralEventDescription) {
		this.eventDescription = GeneralEventDescription;
	}
	
	@Override
	public String toString () {
		return "Event Details:\n" + super.toString() + "The event description: " + this.getEventDescription() + "\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		// Check if obj is instance of GeneralEvent
		if(obj instanceof GeneralEvent) {
			// Casting
			GeneralEvent anyGeneralEvent = (GeneralEvent)obj;
			return super.equals(anyGeneralEvent) && this.getEventDescription().equals(anyGeneralEvent.getEventDescription());
		}
		
		return false;
	}

}
