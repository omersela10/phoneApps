package phoneApplications;

public class SMSManager implements AppHandler, ContactObserver {

	private SMSList sms;
	
	public SMSManager () {
		
		this.sms = new SMSList();
	}
	
	@Override
	public void onContactRemoved(Contact anyContact) {
		
		this.sms.deleteAllContentsWithContact(anyContact);
	}

	@Override
	public boolean isContactExists(Contact anyContact) {
		
		return PhoneBookManager.isContactExist(anyContact);
	}

	@Override
	public void startApp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printOptions() {
		// TODO Auto-generated method stub
		
	}

}
