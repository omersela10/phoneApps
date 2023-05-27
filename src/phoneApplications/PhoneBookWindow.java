package phoneApplications;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PhoneBookWindow extends JFrame {

	private static PhoneBookWindow instance;
    private PhoneBookManager phoneBookManager;
	// Text area to display the phone book list
	private JTextArea phoneBookTextArea; 
	
    // Singleton Design Pattern
    private PhoneBookWindow(PhoneBookManager manager) {
        this.phoneBookManager = manager;
        initialize();
    }

    public static synchronized PhoneBookWindow getInstance(PhoneBookManager manager) {
    	
        if (instance == null) {
            instance = new PhoneBookWindow(manager);
        }
        return instance;
    }

    private void initialize() {
    	
    	setTitle("Phone Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(800, 400));

        // Set up the window components and event handlers
        // ...
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
    
    // Create Buttons
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
                phoneBookManager.getPhoneBook().addContact(new Contact(name, phone));
                break;
                
            case "Remove Contact":
                // Open dialog to get contact name
                String contactName = JOptionPane.showInputDialog(this, "Enter contact name:");
                // Perform logic to remove the contact
                Contact removed =  phoneBookManager.getPhoneBook().removeContact(contactName);
                // Notify Observers upon changes.
                phoneBookManager.notifyObservers(removed);
                break;
                
            case "Print Phone Book":
            	// Clear the text area
                phoneBookTextArea.setText("");
                phoneBookManager.getPhoneBook().printList(phoneBookTextArea);
               
                break;
            case "Search Contact":
                // Open dialog to get contact name
                String searchName = JOptionPane.showInputDialog(this, "Enter contact name:");
                // Perform logic to search for the contact
                phoneBookManager.getPhoneBook().searchByName(searchName);
                break;
                
            case "Sort by Name":
                // Perform logic to sort the phone book by name
            	phoneBookManager.getPhoneBook().sortListByName();
                break;
            case "Sort by Phone Number":
                // Perform logic to sort the phone book by phone number
            	phoneBookManager.getPhoneBook().sortListByPhoneNumber();
                break;
                
            case "Remove Duplicates":
                // Perform logic to remove duplicates from the phone book
            	phoneBookManager.getPhoneBook().removeDuplicate();
                break;
            case "Reverse List":
                // Perform logic to reverse the phone book list
            	phoneBookManager.getPhoneBook().reverse();
                break;
                
            case "Save Phone Book":
                // Open dialog to get file name
                String fileName = JOptionPane.showInputDialog(this, "Enter file name:");
                // Perform logic to save the phone book to a file
                phoneBookManager.getPhoneBook().exportPhoneBook(fileName);
                break;
                
            case "Load Phone Book":
                // Open dialog to get file name
                String loadFileName = JOptionPane.showInputDialog(this, "Enter file name:");
                // Perform logic to load and append phone book from a file
                phoneBookManager.getPhoneBook().importAndAppendPhoneBook(loadFileName);
                break;
                
           
        }
    }
    // Other methods and event handlers
    // ...
}
