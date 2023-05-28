package phoneApplications;

import java.util.Scanner;

// implements ContactObserver interface to listening of removedContacts in the phone book app
public class SMSManager implements ContactObserver {

	// Data Member
	private SMSList sms;
	
	// Constructor
	public SMSManager () {
		
		this.sms = new SMSList();
	}
	
	// Getter
	public SMSList getSMSList() {
		
		return this.sms;
	}
	
	@Override
	public void onContactRemoved(Contact anyContact) {
		
		this.sms.deleteAllContentsWithContact(anyContact);
	}

	@Override
	public boolean isContactExists(Contact anyContact) {
		
		return PhoneBookManager.isContactExist(anyContact);
	}

	

}
