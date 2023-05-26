package phoneApplications;
import java.time.LocalDateTime;
import java.util.Comparator;

public class EventComparator implements Comparator<Event>
{
	public int compare(Event e1, Event e2) {
		return e1.getDateTime().compareTo(e2.getDateTime());
	}
}