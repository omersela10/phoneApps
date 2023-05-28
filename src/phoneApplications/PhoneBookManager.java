package phoneApplications;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PhoneBookManager extends JFrame{

	private static ContactList phoneBook;
	private static ArrayList<ContactObservable> observers;
	

	
	public ContactList getPhoneBook() {
		return this.phoneBook;
	}
	public static boolean isContactExist(Contact anyContact) {
		return phoneBook.searchByName(anyContact.getName());
	}
	
	public static boolean isContactExist(String anyContactName) {
		return phoneBook.searchByName(anyContactName);
	}
	
	public static Contact ContactByName (String anyContactName) {
		return phoneBook.searchContactByName(anyContactName);
	}
	
	public PhoneBookManager() {

		phoneBook = new ContactList();
		this.observers = new ArrayList<ContactObservable>();
	}
	
	// Register Observer - Observer Design Pattern
	public static void registerObserver(ContactObservable observer) {
		
        observers.add(observer);
    }

	// Unregister Observer - Observer Design Pattern
    public static void unregisterObserver(ContactObservable observer) {
    	
        observers.remove(observer);
    }
    
    // Notify Observers - Observer Design Pattern
    public static void notifyObservers(Contact contact) {
    	
        for (ContactObservable observer : observers) {
            observer.onContactRemoved(contact);
        }
    }
	
}
