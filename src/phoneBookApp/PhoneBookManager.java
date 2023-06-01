package phoneBookApp;

import java.util.*;

public class PhoneBookManager{

	// Static members for one instance while running
	private static ContactList phoneBook;
	private static ArrayList<ContactObserver> observers;
	

	// Constructor
	public PhoneBookManager() {

		this.phoneBook = new ContactList();
		this.observers = new ArrayList<ContactObserver>();
	}
	
	// Getter
	public ContactList getPhoneBook() {
		return this.phoneBook;
	}
	
	// Various ways for check if contact exist (overloading)
	public static boolean isContactExist(Contact anyContact) {
		return phoneBook.searchByName(anyContact.getName());
	}
	
	public static  boolean isContactExist(String anyContactName) {
		return phoneBook.searchByName(anyContactName);
	}
	
	public static Contact ContactByName (String anyContactName) {
		return phoneBook.searchContactByName(anyContactName);
	}
	
	
	
	// Register Observer - Observer Design Pattern
	public static void registerObserver(ContactObserver observer) {
		
        observers.add(observer);
    }
	
	// Unregister Observer - Observer Design Pattern
    public static void unregisterObserver(ContactObserver observer) {
    	
        observers.remove(observer);
    }
 
    // Notify Observers - Observer Design Pattern
    public static void notifyObservers(Contact contact) {
    	
        for (ContactObserver observer : observers) {
            observer.onContactRemoved(contact);
        }
    }
	
	
}
