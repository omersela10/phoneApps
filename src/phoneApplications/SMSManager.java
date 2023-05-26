package phoneApplications;

import java.util.ArrayList;

public class SMSManager {

	private ArrayList<SMS> smsList;

	// Constructor:
	public SMSManager() {
		
		this.smsList = new ArrayList<SMS>();
	}
	
	public ArrayList<SMS> getSMSList() {
		
		return smsList;
	}
	
	// Check if the contact is in the contact list 
	private boolean isValidContact (Contact anyContact) {
		
		return true;
	}
	
	private SMS containContact (Contact anyContact) {
		
		for (SMS it:this.getSMSList()) {
			
			if (it.getContact().equals(anyContact) == true) {
			
				return it;
			}
		}
		
		return null;
	}
	
	public void addContentToContact (Contact anyContact , String contentText) {
		
		if (isValidContact(anyContact) == false ) {
			System.out.println("This contact not in the contact list");
			return;
		}
		
		//else: isValidContact(anyContact) == true
		SMS SMSContact = this.containContact (anyContact);
		
		if (SMSContact != null) {
			SMSContact.addContent(contentText);
		}
		else {
			// if this is the first content with anyContact
			this.getSMSList().add(new SMS(anyContact, contentText));
		}
		
		System.out.println("content added");
	}
	
	public void deleteAllContentsWithContact (Contact anyContact) {
		
		SMS SMSContact = this.containContact (anyContact);
		
		if (SMSContact != null ) {
			this.getSMSList().remove(SMSContact);
			System.out.println("All the masseges of " + anyContact.getName() + "removed");
			return;
		}
		
		// else: not found anyContact in SMS list
		System.out.println("There are no any messages from" + anyContact.getName());
	}
	
	public void printAllContentsWithContact (Contact anyContact) {
		
		SMS SMSContact = this.containContact (anyContact);
		
		if (SMSContact != null) {
			// found anyContact in SMS list
			System.out.println(SMSContact.toString());
			return;
		}
		
		// else: not found anyContact in SMS list
		System.out.println("There are no any messages from" + anyContact.getName());
		
	}
	
	public void printAllSMSList () {
		
		System.out.println("SMS:");
		
		for (SMS it:this.getSMSList()) {
			
			System.out.print(it.toString());
		}
	}
	
	public void printAllContactContainDesireSentence (String sentence) {
		
		ArrayList<Contact> containSentenceContacts = new ArrayList<Contact>();
		
		for (SMS it :this.getSMSList()) {
			
			for (Content iterator:it.getContentList()) {
				
				if (iterator.getText().contains(sentence) == true) {
					
					containSentenceContacts.add(it.getContact());
					break;
				}
			}
		}
		
		if (containSentenceContacts.size() == 0) {
			
			System.out.println("There are no messages with the sentence: " + sentence);
		}
		
		else {
			
			System.out.println("List of contacts that their SMS contain the sentence:");
			
			//print all contact that there SMS contain the sentence
			for (Contact it:containSentenceContacts) {
				
				System.out.print(it.toString());
			}
		}
	}

}
