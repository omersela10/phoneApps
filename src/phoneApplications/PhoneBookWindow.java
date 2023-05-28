package phoneApplications;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//Window will be Singleton
public class PhoneBookWindow extends JFrame {

	// Data Members
	private static PhoneBookWindow phoneBookWindowInstance;
    private PhoneBookManager phoneBookManager;
	// Text area to display the phone book list
	private JTextArea phoneBookTextArea; 
	
    // Singleton Design Pattern
    private PhoneBookWindow(PhoneBookManager newPhoneBookmanager) {
        this.phoneBookManager = newPhoneBookmanager;
    }

    // Get Instance function - Singleton Design Pattern
    public static synchronized PhoneBookWindow getInstance(PhoneBookManager newPhoneBookmanager) {
    	
        if (phoneBookWindowInstance == null) {
        	phoneBookWindowInstance = new PhoneBookWindow(newPhoneBookmanager);
        }
        initialize(); 
        return phoneBookWindowInstance;
    }

    private static void initialize() {
    	
    	phoneBookWindowInstance.setTitle("Phone Book");
    	phoneBookWindowInstance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	phoneBookWindowInstance.setPreferredSize(new Dimension(800, 400));

        // Set up the window components 
    	// Create the buttons
    	JButton addContactButton = phoneBookWindowInstance.createButton("Add Contact");
    	JButton removeContactButton = phoneBookWindowInstance.createButton("Remove Contact");
    	JButton printPhoneBookButton =phoneBookWindowInstance.createButton("Print Phone Book");
    	JButton searchContactButton = phoneBookWindowInstance.createButton("Search Contact");
    	JButton sortByNameButton = phoneBookWindowInstance.createButton("Sort by Name");
    	JButton sortByPhoneNumberButton = phoneBookWindowInstance.createButton("Sort by Phone Number");
    	JButton removeDuplicatesButton = phoneBookWindowInstance.createButton("Remove Duplicates");
    	JButton reverseListButton = phoneBookWindowInstance.createButton("Reverse List");
    	JButton savePhoneBookButton = phoneBookWindowInstance.createButton("Save Phone Book");
    	JButton loadPhoneBookButton = phoneBookWindowInstance.createButton("Load Phone Book");
    			

    	// Create the text area
    	phoneBookWindowInstance.phoneBookTextArea = new JTextArea();
    	phoneBookWindowInstance.phoneBookTextArea.setEditable(false); // Make it read-only
    	JScrollPane scrollPane = new JScrollPane(phoneBookWindowInstance.phoneBookTextArea);

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
    	phoneBookWindowInstance.add(mainPanel);

    	// Pack and display the frame
    	phoneBookWindowInstance.pack();
    	phoneBookWindowInstance.setLocationRelativeTo(null);
    	phoneBookWindowInstance.setVisible(true);	
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
                
                if (name == null || phone == null) {
                	break;
                }
                phoneBookManager.getPhoneBook().addContact(new Contact(name, phone));
                break;
                
            case "Remove Contact":
                // Open dialog to get contact name
                String contactName = JOptionPane.showInputDialog(this, "Enter contact name:");
                
                if (contactName == null) {
                	break;
                }
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
                if (searchName == null) {
                	break;
                }
                
                phoneBookManager.getPhoneBook().searchByName(searchName);
                break;
                
            case "Sort by Name":
            	phoneBookManager.getPhoneBook().sortListByName();
                break;
            case "Sort by Phone Number":
            	phoneBookManager.getPhoneBook().sortListByPhoneNumber();
                break;
                
            case "Remove Duplicates":
            	phoneBookManager.getPhoneBook().removeDuplicate();
                break;
                
            case "Reverse List":
            	phoneBookManager.getPhoneBook().reverse();
                break;
                
            case "Save Phone Book":
                // Open dialog to get file name
                String fileName = JOptionPane.showInputDialog(this, "Enter file name:");
                if (fileName == null) {
                	break;
                }
                phoneBookManager.getPhoneBook().exportPhoneBook(fileName);
                break;
                
            case "Load Phone Book":
                // Open dialog to get file name
                String loadFileName = JOptionPane.showInputDialog(this, "Enter file name:");
                if (loadFileName == null) {
                	break;
                }
                phoneBookManager.getPhoneBook().importAndAppendPhoneBook(loadFileName);
                break;
                
           
        }
    }

}
