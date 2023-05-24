package phoneApplications;

import java.util.Comparator;

public class DateEventComparator implements Comparator<Date>
{
	public int compare(Date d1, Date d2) 
	{
		 
		if (d1.getYear() == d2.getYear()) 
		{	
			if (d1.getMonth() == d2.getMonth())
			{
				if (d1.getDay() == d2.getDay()) 
				{
					if (d1.getHour() == d2.getHour()) 
					{
						//2 dates cannot be exactly the same date
							return d1.getHour() - d2.getHour();
					}
					else
					{
						return d1.getHour() - d2.getHour();
					}
				}
				else
				{
					return d1.getDay() - d2.getDay();
				}
			}
			
			else
			{
				return d1.getMonth() - d2.getMonth();
			}
		}
		else 
		{
			return d1.getYear() - d2.getYear();
		}
	}
}
