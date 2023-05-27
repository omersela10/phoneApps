package phoneApplications;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PhoneBookManager extends JFrame{

	private static ContactList phoneBook;
	private static ArrayList<ContactObserver> observers;
	

	
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
		this.observers = new ArrayList<ContactObserver>();

		
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
