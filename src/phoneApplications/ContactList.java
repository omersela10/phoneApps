package phoneApplications;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.io.*;

public class ContactList {

	// Data Members

	private ArrayList<Contact> contactList;
	
	
	// Constructor
	public ContactList() {
		
		this.contactList = new ArrayList<Contact>();
	}
	
	// Getter
	public ArrayList<Contact> getList() {
		return this.contactList;
	}
	
	// Methods : 
	
	// Add contact 
	public void addContact(Contact anyContact) {
		
		// Check if any contact with this name already exist in the list.
		//Contact exist = this.searchByName(anyContact.getName());
		
		//if(exist == null) {
			// Notify to user and return
			//JOptionPane.showMessageDialog(null,anyContact.getName() + " already exist in the list");
			//return;
		//}
		
		// Build Contact
		Contact newContact = new Contact(anyContact);
		
		this.contactList.add(newContact);
		
		// Notify upon insertion
		JOptionPane.showMessageDialog(null, newContact + " added to the list");
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
			JOptionPane.showMessageDialog(null, name + " does not exist the contact list.");
		}
		else {
			JOptionPane.showMessageDialog(null, name + " removed from contact list.");
		}
		
		return returnContact;
	}
	
	// Print list
	public void printList (JTextArea phoneBookTextArea) {
		
		phoneBookTextArea.append("The Phone Book: \n");
		 // Append each contact to the text area
        for (Contact contact : this.getList()) {
            phoneBookTextArea.append(contact.toString());
        }
		
		
	}
	
	// Search for name in the Phone Book
	public boolean searchByName (String name) {
			
			// Check if the contact exist and display it.
			for(Contact contact : this.contactList) {
				
				if(contact.getName().equals(name) == true) {
					JOptionPane.showMessageDialog(null, contact + " in the contact list.");
					return true;
				}
	
			}
			
			JOptionPane.showMessageDialog(null, name + " does not in the contact list.");
			return false;
	}
	
	
	// Sort List By name.
	public void sortListByName () {
		
		// Initialize the comparator 
		ContactNameComparator sortByName = new ContactNameComparator();
		
		// Sort By name
		Collections.sort(this.contactList, sortByName);
		
		JOptionPane.showMessageDialog(null,"Phone book sorted by name.");
	}
	
	// Sort List By Phone Number.
	public void sortListByPhoneNumber () {
		
		// Initialize the comparator
		ContactPhoneComparator sortByPhone = new ContactPhoneComparator();
		
		// Sort By phone number
		Collections.sort(this.contactList, sortByPhone);
		
		JOptionPane.showMessageDialog(null,"Phone book sorted by phone.");
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
		
		JOptionPane.showMessageDialog(null,"Duplicates removed.");
	}

	// Reverse list function
	public void reverse() {
		
		Collections.reverse(this.contactList);
		
		JOptionPane.showMessageDialog(null,"Phone book reversed.");
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
	        
	        JOptionPane.showMessageDialog(null,"Phone book loaded from file.");
        
		} 
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,"An error occurred.");
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
            
            JOptionPane.showMessageDialog(null,"Phone book saved to file.");
             
       } 
	   catch (Exception e) {
			
		   JOptionPane.showMessageDialog(null,"An error occurred.");
            e.printStackTrace();
       } 
	
	}

	
	
}
