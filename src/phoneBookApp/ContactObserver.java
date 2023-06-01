package phoneBookApp;

public interface ContactObserver {

	void onContactRemoved(Contact anyContact);
	boolean isContactExists(Contact anyContact);
}
