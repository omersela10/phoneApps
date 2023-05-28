package phoneApplications;

import java.util.ArrayList;

public class SMS {

	// Data Members
	private Contact contact;
	private ArrayList<Content> contentList;
	
	// Constructors
	public SMS (Contact contact) {
		
		this.setContact(contact);
		this.contentList = new ArrayList<Content>();
	}
	
	public SMS (Contact contact, String anyContentText) {
		
		this.setContact(contact);
		this.contentList = new ArrayList<Content>();
		this.addContent(anyContentText);
	}
	

	// Getters:
	public Contact getContact() {
		
		return contact;
	}

	public ArrayList<Content> getContentList() {
		return contentList;
	}
	
	// Setter
	public void setContact(Contact contact) {
		
		// SMS object keep reference to contact 
		this.contact = contact;
	}


	// Add content
	public void addContent (String anyContentText){
		
		contentList.add ( new Content(anyContentText) );
	}
	
	@Override
	public String toString () {
		
		String str = "The content with " + this.contact.toString();
		
		for (Content content: this.contentList) {
			
			str += (content.toString() + ".\n");
		}
		
		return  str;
		
		
	}

	
	
}
