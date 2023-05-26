package phoneApplications;

public interface ContactObserver {

	void onContactRemoved(Contact anyContact);
	boolean isContactExists(Contact anyContact);
}
