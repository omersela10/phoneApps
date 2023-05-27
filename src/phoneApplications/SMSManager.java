package phoneApplications;

import java.util.Scanner;

public class SMSManager implements ContactObserver {

	private SMSList sms;
	
	public SMSManager () {
		
		this.sms = new SMSList();
	}
	
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
