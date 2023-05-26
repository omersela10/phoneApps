package phoneApplications;

import java.util.*;

public class ContactManager implements AppHandler{

	private static ContactList ourList;
	private ArrayList<ContactObserver> observers;

	public static boolean IsContactExist(Contact anyContact) {
		return ourList.searchByName(anyContact.getName());
	}
	
	public ContactManager() {
		ourList = new ContactList();
		this.observers = new ArrayList<ContactObserver>();
	}
	
	@Override
	public void startApp() {
		
		Scanner scanner = new Scanner(System.in);
		
		boolean exit = false;
		
	
	    while (exit == false) {
	    	 
	    	printOptions();
	    	
		    String option = scanner.next();
		    scanner.nextLine(); 

		    switch (option) {

			    case "1":
				// Add Contact
				System.out.println("Enter name:");
				String name = scanner.nextLine();
				System.out.println("Enter phone number:");
				String phone = scanner.nextLine();
				
				Contact newContact = new Contact(name, phone);
				ourList.addContact(newContact);
				break;

			    case "2":
				// Remove contact
				System.out.println("Enter name:");
				name = scanner.nextLine();
				Contact removedContact = ourList.removeContact(name);
				this.notifyObservers(removedContact);
				break;

			    case "3":
				// Print Phone Book
				ourList.printList();
				break;

			    case "4":
				// Search contact in the list.
				System.out.println("Enter name:");
				name = scanner.nextLine();
				ourList.searchByName(name);
		
				break;

			    case "5":
				// Sort list by name
				ourList.sortListByName();
				break;

			    case "6":
				// Sort list by phone number
				ourList.sortListByPhoneNumber();
				break;

			    case "7":
				// Remove duplicates
				ourList.removeDuplicate();
				break;

			    case "8":
				// Reverse list.
				ourList.reverse();
				break;

			    case "9":
				// Save Phone book to file
				System.out.println("Enter file name:");
				String filename = scanner.nextLine();
				ourList.exportPhoneBook(filename);
				break;

			    case "10":
				// Load and append from file.
				System.out.println("Enter file name:");
				filename = scanner.nextLine();
				ourList.importAndAppendPhoneBook(filename);
				break;

			    case "11":
				// Exit.
				System.out.println("Exiting...");
				exit = true;
				break;

			    default:
				System.out.println("Invalid option. Try again.");
			}

		}
	}

	@Override
	public void printOptions() {
		 // Print options
		System.out.println("Enter an option:");
		System.out.println("1. Add a contact");
		System.out.println("2. Delete a contact");
		System.out.println("3. Print all contacts");
		System.out.println("4. Search for a contact");
		System.out.println("5. Sort phone book by name");
		System.out.println("6. Sort phone book by phone");
		System.out.println("7. Remove duplicates");
		System.out.println("8. Reverse order");
		System.out.println("9. Save to file");
		System.out.println("10. Load from file");
		System.out.println("11. Exit");
		
	}
	
	// Register Observer - Observer Design Pattern
	public void registerObserver(ContactObserver observer) {
		
        observers.add(observer);
    }

	// Unregister Observer - Observer Design Pattern
    public void unregisterObserver(ContactObserver observer) {
    	
        observers.remove(observer);
    }
    
    // Notify Observers - Observer Design Pattern
    private void notifyObservers(Contact contact) {
    	
        for (ContactObserver observer : observers) {
            observer.onContactRemoved(contact);
        }
    }
	
}
