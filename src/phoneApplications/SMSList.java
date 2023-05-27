package phoneApplications;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SMSList {

	private ArrayList<SMS> smsList;

	// Constructor:
	public SMSList() {
		
		this.smsList = new ArrayList<SMS>();
	}
	
	public ArrayList<SMS> getSMSList() {
		
		return smsList;
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
		
		SMS SMSContact = this.containContact (anyContact);
		
		// If there is a open SMS with the contact person
		if (SMSContact != null) {
			SMSContact.addContent(contentText);
			JOptionPane.showMessageDialog(null, anyContact.getName() + " added.");
		}
		// if this is the first content with anyContact
		else {
			
			this.getSMSList().add(new SMS(anyContact, contentText));
			JOptionPane.showMessageDialog(null, anyContact.getName() + " added.");
		}
		
		JOptionPane.showMessageDialog(null," content added.");
	}
	
	public void deleteAllContentsWithContact (Contact anyContact) {
		
		if (anyContact == null) {
			return;
		}
		SMS SMSContact = this.containContact (anyContact);
		
		if (SMSContact != null ) {
			this.getSMSList().remove(SMSContact);
			JOptionPane.showMessageDialog(null, "All the masseges of " + anyContact.getName() + "removed");
			return;
		}
		
		// else: not found anyContact in SMS list.
		JOptionPane.showMessageDialog(null, "There are no any messages from" + anyContact.getName());
	}
	
	public void printAllContentsWithContact (Contact anyContact, JTextArea SMSTextArea) {
		
		SMS SMSContact = this.containContact (anyContact);
		
		if (SMSContact != null) {
			// found anyContact in SMS list
			SMSTextArea.append(SMSContact.toString());
			return;
		}
		
		// else: not found anyContact in SMS list
		SMSTextArea.append("There are no any messages from" + anyContact.getName());
		
	}
	
	public void printAllSMSList (JTextArea SMSTextArea) {
		
		SMSTextArea.append("SMS:");
		
		for (SMS it:this.getSMSList()) {
			
			SMSTextArea.append(it.toString());
		}
	}
	
	public void printAllContactContainDesireSentence (String sentence, JTextArea SMSTextArea) {
		
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
			
			SMSTextArea.append("There are no messages with the sentence: " + sentence);
		}
		
		else {
			
			SMSTextArea.append("List of contacts that their SMS contain the sentence:");
			
			//print all contact that there SMS contain the sentence
			for (Contact it:containSentenceContacts) {
				
				SMSTextArea.append(it.toString());
			}
		}
	}

}
