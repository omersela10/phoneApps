package phoneApplications;

public interface ContactObservable {

	void onContactRemoved(Contact anyContact);
	boolean isContactExists(Contact anyContact);
}