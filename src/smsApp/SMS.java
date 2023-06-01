package smsApp;

import java.util.ArrayList;

import phoneBookApp.Contact;

public class SMS {

	// Data Members
	private Contact contact;
	private ArrayList<Message> messageList;
	
	// Constructors
	public SMS (Contact contact) {
		
		this.setContact(contact);
		this.messageList = new ArrayList<Message>();
	}
	
	public SMS (Contact contact, String anyMessageText) {
		
		this.setContact(contact);
		this.messageList = new ArrayList<Message>();
		this.addMessage(anyMessageText);
	}
	

	// Getters:
	public Contact getContact() {
		
		return contact;
	}

	public ArrayList<Message> getMessageList() {
		return messageList;
	}
	
	// Setter
	public void setContact(Contact contact) {
		
		// SMS object keep reference to contact 
		this.contact = contact;
	}


	// Add message
	public void addMessage (String anyMessageText){
		
		messageList.add ( new Message(anyMessageText) );
	}
	
	@Override
	public String toString () {
		
		String str = "The messages with " + this.contact.toString();
		
		for (Message anyMessage: this.messageList) {
			
			str += (anyMessage.toString() + ".\n");
		}
		
		return  str;
	
	}

	
	
}
