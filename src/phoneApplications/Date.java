package phoneApplications;

public class Date {
	// Data members
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minutes;
	
	// Constructors
	 public Date(int year, int month, int day) 
	 {
		 this.year = year;
		 this.month = month;
		 this.day = day;
		 this.hour = 0;
		 this.minutes = 0;
	 }
	 
	 public Date(int year, int month, int day, int hour, int minutes) 
	 {
		 this.year = year;
		 this.month = month;
		 this.day = day;
		 this.hour = hour;
		 this.minutes = minutes;
	 }
	 
	 public Date(Date anyDate) 
	 {
		 this.year = anyDate.getYear();
		 this.month = anyDate.getMonth();
		 this.day = anyDate.getDay();
		 this.hour = anyDate.getHour();
		 this.minutes = anyDate.getMinutes();
	 }
	 
	// Getters
	 public int getYear() 
	 {
		 return this.year;
	 }
	 
	 public int getMonth() 
	 {
		 return this.month;
	 }
	 
	 public int getDay() 
	 {
		 return this.day;
	 }
	 
	 public int getHour() 
	 {
		 return this.hour;
	 }
	 
	 public int getMinutes() 
	 {
		 return this.minutes;
	 }
	 
	//Setters
	public void setYear(int otherYear)
	{
		this.year = otherYear;
	}
	
	public void setMonth(int otherMonth)
	{
		this.month = otherMonth;
	}
	
	public void setDay(int otherDay)
	{
		this.day = otherDay;
	}
	
	public void setHour(int otherHour)
	{
		this.hour = otherHour;
	}
	
	public void setMinutes(int otherMinutes)
	{
		this.minutes = otherMinutes;
	}
	
	public String toString () 
	{	
		return "" + this.getDay() + "/" + this.getMonth() +"/" + this.getYear() + ", " + this.getHour() + ":" + this.getMinutes() + "\n";
	}
	
	public boolean ifEqualDate(Date otherDate)
	{
		if(this.getMinutes() != otherDate.getMinutes())
			return false;
		
		if(this.getHour() != otherDate.getHour())
			return false;
		
		if(this.getDay() != otherDate.getDay())
			return false;
		
		if(this.getMonth() != otherDate.getMonth())
			return false;
		
		if(this.getYear() != otherDate.getYear())
			return false;
		
		return true;
		
	}
	
	public boolean compareDay(int year, int month, int day)
	{
		if(this.getDay() != day)
			return false;
		
		if(this.getMonth() != month)
			return false;
		
		if(this.getYear() != year)
			return false;
		
		return true;
	}
}
