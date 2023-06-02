package phoneBookApp;
import java.util.*;

// Implements the interface comparator by Strategy Design Pattern
public class ContactPhoneComparator implements Comparator<Contact> {

	// Override compare to compare between two Contact by their phone number 
	@Override
	public int compare(Contact c1, Contact c2) {
		 
		if (c1.getPhoneNumber().equals(c2.getPhoneNumber()) == true) {
			// If they have the same phone number, Compare by Alphabetic name order
			return c1.getName().compareToIgnoreCase(c2.getName());	
		}
		else {
			// Else - compare by Bigger numeric Phone number
			Long c1PhoneInt = Long.parseLong(c1.getPhoneNumber());
			Long c2PhoneInt = Long.parseLong(c2.getPhoneNumber());
			return c2PhoneInt.compareTo(c1PhoneInt);
		}
	}

}


