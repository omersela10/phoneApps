package phoneApplications;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SMSList {

	// Data Member
	private ArrayList<SMS> smsList;

	// Constructor
	public SMSList() {
		
		this.smsList = new ArrayList<SMS>();
	}
	
	// Getter
	public ArrayList<SMS> getSMSList() {
		
		return smsList;
	}
	
	// Check if contact exist in the SMSList
	private SMS containContact (Contact anyContact) {
		
		for (SMS iterator : this.getSMSList()) {
			
			if (iterator.getContact().equals(anyContact) == true) {
			
				return iterator;
			}
		}
		
		return null;
	}
	
	// Add message with contact
	public void addContentToContact (Contact anyContact , String contentText) {
		
		SMS SMSContact = this.containContact (anyContact);
		
		// If there is a open SMS with the contact person
		if (SMSContact != null) {
			SMSContact.addContent(contentText);
			JOptionPane.showMessageDialog(null, " added message from " + anyContact.getName() );
		}
		// else, this is the first content with anyContact
		else {
			
			this.getSMSList().add(new SMS(anyContact, contentText));
			JOptionPane.showMessageDialog(null, " added messages from " + anyContact.getName() );
		}
		
	}
	
	// Remove messages with some contact
	public String deleteAllContentsWithContact (Contact anyContact) {
		
		// If it is null
		if (anyContact == null) {
			return "";
		}
		// Check this contact in list
		SMS SMSContact = this.containContact (anyContact);
		
		if (SMSContact != null ) {
			this.getSMSList().remove(SMSContact);
			return "All the messages of " + anyContact.getName() + " removed";
		}
		
		// else: not found anyContact in SMS list.
		return "There are no any messages from " + anyContact.getName();
	}
	
	// Print the all messages with some contact
	public void printAllContentsWithContact (Contact anyContact, JTextArea SMSTextArea) {
		
		SMS SMSContact = this.containContact (anyContact);
		
		// Check this contact in list
		if (SMSContact != null) {
			// Found , So print.
			SMSTextArea.append(SMSContact.toString());
			return;
		}
		
		// else: not found anyContact in SMS list
		SMSTextArea.append("There are no any messages from" + anyContact.getName());
		
	}
	
	// Print all messages with all contacts
	public void printAllSMSList (JTextArea SMSTextArea) {
		
		SMSTextArea.append("SMS:");
		
		for (SMS iterator : this.getSMSList()) {
			
			SMSTextArea.append(iterator.toString());
		}
	}
	
	// Print all the contacts that their messages contain given sentence 
	public void printAllContactContainDesireSentence (String sentence, JTextArea SMSTextArea) {
		
		ArrayList<Contact> containSentenceContacts = new ArrayList<Contact>();
		
		// Scan all list
		for (SMS listIterator :this.getSMSList()) {
			
			// scan all messages with current Contact
			for (Content messagesIterator:listIterator.getContentList()) {
				// Check if any of this messages contain the sentence
				if (messagesIterator.getText().contains(sentence) == true) {
					// Add it to print after
					containSentenceContacts.add(listIterator.getContact());
					break;
				}
			}
		}
		
		if (containSentenceContacts.size() == 0) {
			
			SMSTextArea.append("There are no messages with the sentence: " + sentence +"\n");
		}
		
		else {
			
			SMSTextArea.append("List of contacts that their SMS contain the sentence:" + "\n");
			
			//print all contact that there SMS contain the sentence
			for (Contact it:containSentenceContacts) {
				
				SMSTextArea.append(it.toString());
			}
		}
	}

}
