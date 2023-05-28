package phoneApplications;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//Window will be Singleton
public class SMSWindow extends JFrame{
	
	private static SMSWindow SMSWindowInstance;
    private SMSManager SmsManager;
	// Text area to display the phone book list
	private JTextArea SMSTextArea; 
	
    // Singleton Design Pattern
    private SMSWindow(SMSManager manager) {
        this.SmsManager = manager;
    }
    
	// Get Instance - Singleton Design Pattern 
    public static synchronized SMSWindow getInstance(SMSManager manager) {
    	
        if (SMSWindowInstance == null) {
        	SMSWindowInstance = new SMSWindow(manager);
        }
        initialize();
        
        return SMSWindowInstance;
    } 

    private static void initialize() {
    	
    	SMSWindowInstance.setTitle("SMS");
    	SMSWindowInstance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	SMSWindowInstance.setPreferredSize(new Dimension(800, 400));

        // Set up the window components and event handlers
        // ...
    	// Create the buttons
    	JButton addMessageButton = SMSWindowInstance.createButton("Add message");
    	JButton deleteContactMessagesButton = SMSWindowInstance.createButton("Delete Contact messages");
    	JButton printContactMessagesButton = SMSWindowInstance.createButton("Print Contact messages");
    	JButton searchForSentenceButton = SMSWindowInstance.createButton("Search for a sentence");
    	JButton PrintAllSMSButton = SMSWindowInstance.createButton("Print all SMS");
    			

    	// Create the text area
    	SMSWindowInstance.SMSTextArea = new JTextArea();
    	SMSWindowInstance.SMSTextArea.setEditable(false); // Make it read-only
    	JScrollPane scrollPane = new JScrollPane(SMSWindowInstance.SMSTextArea);

    	// Create the main panel with BorderLayout
    	JPanel mainPanel = new JPanel(new BorderLayout());

    	// Create the left panel with GridLayout for the buttons
    	JPanel leftPanel = new JPanel(new GridLayout(5, 1));
    	leftPanel.add(addMessageButton);
    	leftPanel.add(deleteContactMessagesButton);
    	leftPanel.add(printContactMessagesButton);
    	leftPanel.add(searchForSentenceButton);
    	leftPanel.add(PrintAllSMSButton);
    			

    	// Add the left panel to the main panel on the left side
    	mainPanel.add(leftPanel, BorderLayout.WEST);

    	// Add the scroll pane to the main panel on the right side
    	mainPanel.add(scrollPane, BorderLayout.CENTER);

    	// Add the main panel to the frame
    	SMSWindowInstance.add(mainPanel);

    	// Pack and display the frame
    	SMSWindowInstance.pack();
    	SMSWindowInstance.setLocationRelativeTo(null);
    	SMSWindowInstance.setVisible(true);	
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
            
            case "Add message":
                	
                // Open dialog to get contact name
                String name = JOptionPane.showInputDialog(this, "Enter name from phone book:");
                // Open dialog to get contact message
                String text = JOptionPane.showInputDialog(this, "Enter the message with this contact:");
                    
                if (text == null || name == null) {
                    break;
                }
                    
                if(PhoneBookManager.ContactByName(name) != null) {
                    	
                    SmsManager.getSMSList().addContentToContact(PhoneBookManager.ContactByName(name), text);
                }
                else {
                    	
                    	JOptionPane.showMessageDialog(null, name + " does not exist in the phone book.");
                }
                
                break;
                    
            case "Delete Contact messages":
                	
                // Open dialog to get contact name
                String nameToDelete = JOptionPane.showInputDialog(this, "Enter contact name:");
                    
                if (nameToDelete == null) {
                    break;
                }
                    
                // Perform logic to delete the contact messages
                if (PhoneBookManager.ContactByName(nameToDelete) != null) {
						
                    SmsManager.getSMSList().deleteAllContentsWithContact(PhoneBookManager.ContactByName(nameToDelete));
                }
		        else {
		        	JOptionPane.showMessageDialog(null, nameToDelete + " does not exist in the phone book.");
		        }
                    
                break;
                    
            case "Print Contact messages":
                	
                //  Open dialog to get contact name
                String nameToPrint = JOptionPane.showInputDialog(this, "Enter contact name:");
                	
                if (nameToPrint == null) {
                    break;
                }
                    
                if (PhoneBookManager.ContactByName(nameToPrint) != null) {
                    	
                    // Clear the text area
                    SMSTextArea.setText("");
                    SmsManager.getSMSList().printAllContentsWithContact(PhoneBookManager.ContactByName(nameToPrint), SMSTextArea);
                }
                else {
			
                	JOptionPane.showMessageDialog(null, nameToPrint + " does not exist in the phone book.");
                }
                   
                break;
                    
            case "Search for a sentence":
                // Open dialog to get sentence to search
                String sentence = JOptionPane.showInputDialog(this, "Enter sentence to search:");
                    
                if (sentence == null) {
                    break;
                }
                    
                // Perform logic to search for the contact
                // Clear the text area
                SMSTextArea.setText("");
                SmsManager.getSMSList().printAllContactContainDesireSentence(sentence, SMSTextArea);
                    
                break;
                    
            case "Print all SMS":
                	
                // Clear the text area
                SMSTextArea.setText("");
                // Print all Sms
                SmsManager.getSMSList().printAllSMSList(SMSTextArea);
                	
      
          }
      }
 }
    

