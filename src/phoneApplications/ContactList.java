package phoneApplications;

import java.util.*;
import java.io.*;

public class ContactList {

	// Data Members

	private ArrayList<Contact> contactList;
	
	
	// Constructor
	public ContactList() {
		
		this.contactList = new ArrayList<Contact>();
	}

	// Methods : 
	
	// Add contact 
	public void addContact(Contact anyContact) {
		
		// Check if any contact with this name already exist in the list.
		for(Contact contact : this.contactList) {
			
			if(contact.getName().equals(anyContact.getName()) == true) {
				
				System.out.println("Contact " + anyContact.getName() + " already exist");
				return;
			}
			
		}
		
		// Build Contact
		Contact newContact = new Contact(anyContact);
		
		this.contactList.add(newContact);
		
		// Notify upon insertion
		System.out.println("Contact " + newContact + "added.");
	}
	
	// Iterator
	public Iterator<Contact> getIterator() {
		
		Iterator<Contact> iterator = this.contactList.iterator();
		return iterator;		
	}
	
	
	// Remove contact
	public Contact removeContact (String name) {
		
		boolean anyName = false;
		Contact returnContact = null;
		// Check if the contact exist an remove it.
		for(Contact contact : this.contactList) {
			
			if(contact.getName().equals(name) == true) {
				// Found and remove
				anyName = true;
				this.contactList.remove(contact);
				returnContact = contact;
				break;
			}

		}
		
		
		// Print result
		if (anyName == false) {
			System.out.println(name + " does not exist in the contact list.");
		}
		else {
			System.out.println(name + " removed from contact list.");
		}
		
		return returnContact;
	}
	
	// Print list
	public void printList () {
		
		// Print the list
		for(Contact anyContact : this.contactList) {
			
			System.out.println(anyContact);
		}
		
		
	}
	
	// Search for name in the Phone Book
	//שיניתי לפונקציה שמחזירה ערך
	public boolean searchByName (String name) {
			
			// Check if the contact exist.
			for(Contact contact : this.contactList) {
				
				if(contact.getName().equals(name) == true) {
					System.out.print(contact);
					return true;
				}
	
			}
			System.out.println( name + " does not in the contact list.");
			return false;
			}
	
	// Search for name in the Phone Book and return the contact
	public Contact searchContactByName (String name) {
		
		// Check if the contact exist.
		for(Contact contact : this.contactList) {
			
			if(contact.getName().equals(name) == true) {
				
				return contact;
			}

		}

		return null;
	}
		
	/*public void searchByName (String name) {
		
		boolean exist = false;
		
		// Check if the contact exist an remove it.
		for(Contact contact : this.contactList) {
			
			if(contact.getName().equals(name) == true) {
				
				// Found and display
				exist = true;
				System.out.print(contact);
			}

		}
		
		
		if (exist == false) {
			System.out.println( name + " does not in the contact list.");
		}
		
	}*/
	
	// Sort List By name.
	public void sortListByName () {
		
		// Initialize the comparator 
		ContactNameComparator sortByName = new ContactNameComparator();
		
		// Sort By name
		Collections.sort(this.contactList, sortByName);
		
		System.out.println("Phone book sorted by name.");
	}
	
	// Sort List By Phone Number.
	public void sortListByPhoneNumber () {
		
		// Initialize the comparator
		ContactPhoneComparator sortByPhone = new ContactPhoneComparator();
		
		// Sort By phone number
		Collections.sort(this.contactList, sortByPhone);
		
		System.out.println("Phone book sorted by phone.");
	}
	

	// Remove duplicates in the list.
	public void removeDuplicate () {
		
		// Initialize hash table to store which contact we already seen.
		Map<Contact, Boolean> seen = new HashMap<Contact, Boolean>();
		
		// Initialize iterator.
		Iterator<Contact> iterator = this.getIterator();
	
		
		while(iterator.hasNext() == true) {
			
			Contact cur = iterator.next();
			
			if (seen.containsKey(cur) == true) {
				// See duplicate so remove
				iterator.remove();
			}
			else {
				// Insert to the hash if not seen already.
				seen.put(cur, true);
			}
			
		}
		
		System.out.println("Duplicates removed.");
	}

	// Reverse list function
	public void reverse() {
		
		Collections.reverse(this.contactList);
		
		System.out.println("Phone book reversed.");
	}
	
	//Import phoneBook from txt file
	public void importAndAppendPhoneBook(String filePath) {
		
		try {
			// Initialize File Object and Scanner Object
	        File file = new File(filePath);
	        Scanner scanner = new Scanner(file);
	        
	        // Iterate over the file content
	        while (scanner.hasNextLine() == true) {
	        	
	        	// Get the current line in the file
	            String line = scanner.nextLine();
	            
	            // Split by spaces
	            String[] fields = line.split(",");
	            // The format is : (Name: name, Phone: phone) So retrieve name and phone by substring function.
	            String name = fields[0].substring(6);
	            String phone = fields[1].substring(8);
	            
	            // Add to the list.
	            this.addContact(new Contact(name,phone));
	            
	        }
	        // Close file stream
	        scanner.close();
	        
	        System.out.println("Phone book loaded from file.");
        
		} 
		catch (Exception e) {
			
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	
	}
		
	//Export phoneBook to txt file
	public void exportPhoneBook(String filePath) {
		
	    try {
	    	// Initialize File Object and Writer Object
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            
            // Initialize iterator to scan the list.
            Iterator<Contact> iter = this.getIterator();
            
            while(iter.hasNext() == true) {
            	
            	// Write the Contact to file.
                writer.write(iter.next().toString());
   
            }
            
            // Close writer
            writer.close();
            
            System.out.println("Phone book saved to file.");
             
       } 
	   catch (Exception e) {
			
            System.out.println("An error occurred.");
            e.printStackTrace();
       } 
	
	}

	
	
}
