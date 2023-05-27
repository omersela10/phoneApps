package phoneApplications;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PhoneBookManager extends JFrame{

	private static ContactList phoneBook;
	private ArrayList<ContactObserver> observers;
	
	// Text area to display the phone book list
	private JTextArea phoneBookTextArea; 
	
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
		
		setTitle("Phone Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(800, 400));

		phoneBook = new ContactList();
		this.observers = new ArrayList<ContactObserver>();

		// Create the buttons
		JButton addContactButton = createButton("Add Contact");
		JButton removeContactButton = createButton("Remove Contact");
		JButton printPhoneBookButton = createButton("Print Phone Book");
		JButton searchContactButton = createButton("Search Contact");
		JButton sortByNameButton = createButton("Sort by Name");
		JButton sortByPhoneNumberButton = createButton("Sort by Phone Number");
		JButton removeDuplicatesButton = createButton("Remove Duplicates");
		JButton reverseListButton = createButton("Reverse List");
		JButton savePhoneBookButton = createButton("Save Phone Book");
		JButton loadPhoneBookButton = createButton("Load Phone Book");
		

		// Create the text area
		phoneBookTextArea = new JTextArea();
		phoneBookTextArea.setEditable(false); // Make it read-only
		JScrollPane scrollPane = new JScrollPane(phoneBookTextArea);

		// Create the main panel with BorderLayout
		JPanel mainPanel = new JPanel(new BorderLayout());

		// Create the left panel with GridLayout for the buttons
		JPanel leftPanel = new JPanel(new GridLayout(10, 1));
		leftPanel.add(addContactButton);
	    leftPanel.add(removeContactButton);
		leftPanel.add(printPhoneBookButton);
		leftPanel.add(searchContactButton);
		leftPanel.add(sortByNameButton);
		leftPanel.add(sortByPhoneNumberButton);
		leftPanel.add(removeDuplicatesButton);
		leftPanel.add(reverseListButton);
		leftPanel.add(savePhoneBookButton);
		leftPanel.add(loadPhoneBookButton);
		

		// Add the left panel to the main panel on the left side
		mainPanel.add(leftPanel, BorderLayout.WEST);

		// Add the scroll pane to the main panel on the right side
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Add the main panel to the frame
		add(mainPanel);

		// Pack and display the frame
	    pack();
		setLocationRelativeTo(null);
	    setVisible(true);
	}
	 // Add your phone book methods and event handlers here
    private JButton createButton(String text) {
    	
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleButtonAction(text);
            }
        });
        return button;
    }
    
    // Handled button 
    private void handleButtonAction(String buttonText) {
    	
        switch (buttonText) {
        
            case "Add Contact":
                // Open dialog to get contact details
                String name = JOptionPane.showInputDialog(this, "Enter name:");
                String phone = JOptionPane.showInputDialog(this, "Enter phone number:");
                // Perform logic to add the contact
                this.phoneBook.addContact(new Contact(name, phone));
                break;
                
            case "Remove Contact":
                // Open dialog to get contact name
                String contactName = JOptionPane.showInputDialog(this, "Enter contact name:");
                // Perform logic to remove the contact
                Contact removed = this.phoneBook.removeContact(contactName);
                // Notify Observers upon changes.
                this.notifyObservers(removed);
                break;
                
            case "Print Phone Book":
            	// Clear the text area
                phoneBookTextArea.setText("");
                this.phoneBook.printList(phoneBookTextArea);
               
                break;
            case "Search Contact":
                // Open dialog to get contact name
                String searchName = JOptionPane.showInputDialog(this, "Enter contact name:");
                // Perform logic to search for the contact
                this.phoneBook.searchByName(searchName);
                break;
                
            case "Sort by Name":
                // Perform logic to sort the phone book by name
            	this.phoneBook.sortListByName();
                break;
            case "Sort by Phone Number":
                // Perform logic to sort the phone book by phone number
            	this.phoneBook.sortListByPhoneNumber();
                break;
                
            case "Remove Duplicates":
                // Perform logic to remove duplicates from the phone book
            	this.phoneBook.removeDuplicate();
                break;
            case "Reverse List":
                // Perform logic to reverse the phone book list
            	this.phoneBook.reverse();
                break;
                
            case "Save Phone Book":
                // Open dialog to get file name
                String fileName = JOptionPane.showInputDialog(this, "Enter file name:");
                // Perform logic to save the phone book to a file
                this.phoneBook.exportPhoneBook(fileName);
                break;
                
            case "Load Phone Book":
                // Open dialog to get file name
                String loadFileName = JOptionPane.showInputDialog(this, "Enter file name:");
                // Perform logic to load and append phone book from a file
                this.phoneBook.importAndAppendPhoneBook(loadFileName);
                break;
                
           
        }
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
