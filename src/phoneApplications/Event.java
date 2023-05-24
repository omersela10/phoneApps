package phoneApplications;
import java.util.*;

public abstract class Event 
{
	// Data members
	protected Date date;
	protected int duration;
	
	// Constructors
	public Event(Date anyDate, int anyDuration)
	{
		Date newDate = new Date(anyDate);
		this.date = newDate;
		this.duration = anyDuration;
	}
	
	public Event(Event anyEvent)
	{
		Date newDate = new Date(anyEvent.getDate());
		this.date = newDate;
		this.duration = anyEvent.getDuration();
	}
	
	// Getters
	public Date getDate() 
	{
		return this.date;
	}
	
	public int getDuration() 
	{
		return this.duration;
	}
	
	//Setters
	public void setDate(Date otherDate)
	{
		Date newDate = new Date(otherDate);
		this.date = newDate;
	}
	
	public void setDuration(int otherDuration)
	{
		this.duration = otherDuration;
	}
	
	public String toString () 
	{	
		return "Date:" + this.getDate().toString() + "Duration: " + this.getDuration() + "\n";
	}
	
	public boolean ifEqualEvent(Event otherEvent)
	{
		if(!this.getDate().ifEqualDate(otherEvent.getDate()))
			return false;
		if (this.getDuration() != otherEvent.getDuration())
			return false;
		return true;
	}

	
}
