package phoneApplications;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//Window will be Singleton
public class DiaryWindow extends JFrame{

	// Data Members
	private static DiaryWindow instance;
	private DiaryManager diaryManager;
	// Text area to display the phone book list
	private JTextArea diaryTextArea; 
		
	 // Singleton Design Pattern
    private DiaryWindow(DiaryManager newDiaryManager) {
    	
        this.diaryManager = newDiaryManager;
        initialize();
    }

    private void initialize() {
    	setTitle("Diary");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 400));

        // Create buttons
        JButton addEventButton = createButton("Add Event");
        JButton deleteEventButton = createButton("Delete Event");
        JButton printEventsByDateButton = createButton("Print Events by Date");
        JButton printMeetingsByContactButton = createButton("Print Meetings by Contact");
        JButton checkEventCollisionsButton = createButton("Check Event Collisions");
        JButton printAllEventsButton = createButton("Print All Events");
        
        // Create the text area
        diaryTextArea = new JTextArea();
        diaryTextArea.setEditable(false); // Make it read-only
    	JScrollPane scrollPane = new JScrollPane(diaryTextArea);
    	// Create the main panel with BorderLayout
    	JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the main panel
        JPanel leftPanel = new JPanel(new GridLayout(6, 1));
        leftPanel.add(addEventButton);
        leftPanel.add(deleteEventButton);
        leftPanel.add(printEventsByDateButton);
        leftPanel.add(printMeetingsByContactButton);
        leftPanel.add(checkEventCollisionsButton);
        leftPanel.add(printAllEventsButton);
        
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

// Get Instance function - Singleton Design Pattern
public static synchronized void getInstance(DiaryManager newDiaryManager) {
    	
    	 if (instance == null) {
    		instance = new DiaryWindow(newDiaryManager);
    	 }
    	 
    	 instance.setTitle("Diary");
         instance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         instance.setPreferredSize(new Dimension(800, 400));

         // Create buttons
         JButton addEventButton = instance.createButton("Add Event");
         JButton deleteEventButton = instance.createButton("Delete Event");
         JButton printEventsByDateButton = instance.createButton("Print Events by Date");
         JButton printMeetingsByContactButton = instance.createButton("Print Meetings by Contact");
         JButton checkEventCollisionsButton = instance.createButton("Check Event Collisions");
         JButton printAllEventsButton = instance.createButton("Print All Events");

         // Create the text area
         instance.diaryTextArea = new JTextArea();
         instance.diaryTextArea.setEditable(false); // Make it read-only
         JScrollPane scrollPane = new JScrollPane(instance.diaryTextArea);

         // Create the main panel with BorderLayout
         JPanel mainPanel = new JPanel(new BorderLayout());

         // Create the left panel
         JPanel leftPanel = new JPanel(new GridLayout(6, 1));
         leftPanel.add(addEventButton);
         leftPanel.add(deleteEventButton);
         leftPanel.add(printEventsByDateButton);
         leftPanel.add(printMeetingsByContactButton);
         leftPanel.add(checkEventCollisionsButton);
         leftPanel.add(printAllEventsButton);

         // Add the left panel to the main panel on the left side
         mainPanel.add(leftPanel, BorderLayout.WEST);

         // Add the scroll pane to the main panel on the right side
         mainPanel.add(scrollPane, BorderLayout.CENTER);

         // Set the content pane of the instance
         instance.setContentPane(mainPanel);

         // Pack and display the frame
         instance.pack();
         instance.setLocationRelativeTo(null);
         instance.setVisible(true);
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
    
    // Handle Buttons
    private void handleButtonAction(String buttonText) {
    	
        switch (buttonText) {
        
            case "Add Event":
            	// True meaning add
            	addOrRemoveEvent(true);
                break;
                
            case "Delete Event":
            	// False meaning remove
            	addOrRemoveEvent(false);
                break;
                
            case "Print Events by Date":
            	printEventsByDate();
                break;
                
            case "Print Meetings by Contact":
                printMeetingByContact();
                break;
                
            case "Check Event Collisions":
            	checkEventCollisions();
                break;
                
            case "Print All Events":
            	printAllEvents();
                break;
                
            default:
                break;
        }
}
private void checkEventCollisions() {
		
	diaryManager.getDiary().detectCollisionAndRemoveLast();
	JOptionPane.showMessageDialog(this, "Collision removed");
}

private void printAllEvents() {
	// Clear Text area 
	this.diaryTextArea.setText("");
	this.diaryManager.getDiary().printAllEvents(diaryTextArea);
}
// Print Meeting by name
private void printMeetingByContact() {
	
	// Open dialog to get contact details
    String name = JOptionPane.showInputDialog(this, "Enter name:");

    if(name == null) {
    	return;
    }
    
    // Get the Contact
    Contact theContact = PhoneBookManager.ContactByName(name);
    if(theContact == null) {
    	JOptionPane.showMessageDialog(this,name + " not exist in Phone book");
    	return;
    }
    
    // Clear Text area 
	this.diaryTextArea.setText("");
    this.diaryManager.getDiary().printAllEventsWithGivenContact(theContact, diaryTextArea);
		
}

// Print events by date
private void printEventsByDate() {
	
	
	// Create a JDialog to display the date picker
    JDialog dialog = new JDialog();
    dialog.setTitle("Choose Date");
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setSize(200, 100);
    dialog.setLocationRelativeTo(this);

    // Create a JDatePicker component
    SpinnerDateModel dateModel = new SpinnerDateModel();
	JSpinner dateSpinner = new JSpinner(dateModel);
	// Show only Date
	JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
	dateSpinner.setEditor(dateEditor);

    // Create a button to confirm the selected date
    JButton confirmButton = new JButton("Select");
    confirmButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Retrieve the selected date
            Date selectedDate = (Date) dateModel.getValue();

            // Process the selected date
            if (selectedDate != null) {
                // Convert the selected date to LocalDateTime
            	LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            	    
                diaryTextArea.setText("");
                diaryManager.getDiary().printCertainDatesEvents(localDate, diaryTextArea);

                // Close the dialog
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Please select a valid date.");
            }
        }
    });

    // Create a panel to hold the components
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(dateSpinner, BorderLayout.CENTER);
    panel.add(confirmButton, BorderLayout.SOUTH);

    // Set the panel as the content pane of the dialog
    dialog.setContentPane(panel);

    // Display the dialog to the user
    dialog.setVisible(true);
		
}

// Add or remove events
private void addOrRemoveEvent(boolean addOrRemove) {
    	
    	// Create a custom dialog to get the event details
    	JDialog dialog = new JDialog();
    	if(addOrRemove == true) {
    		dialog.setTitle("Add Event");
    	}
    	else {
    		dialog.setTitle("Remove Event");
    	}
    	dialog.setModal(true);

    	// Create input fields for date and time
    	SpinnerDateModel dateModel = new SpinnerDateModel();
    	JSpinner dateSpinner = new JSpinner(dateModel);
    	JLabel dateLabel = new JLabel("Date:");
    	dialog.add(dateLabel);
    	dialog.add(dateSpinner);


    	// Create input fields for duration
    	SpinnerNumberModel durationModel = new SpinnerNumberModel(1, 1, 60, 1);
    	JSpinner durationSpinner = new JSpinner(durationModel);
    	dialog.add(new JLabel("Duration (minutes):"));
    	dialog.add(durationSpinner);
    	
    	// Create radio buttons for contact picker
    	JRadioButton contactRadioButton = new JRadioButton("Contact");
    	JRadioButton descriptionRadioButton = new JRadioButton("Description");
    	ButtonGroup radioButtonGroup = new ButtonGroup();
    	radioButtonGroup.add(contactRadioButton);
    	radioButtonGroup.add(descriptionRadioButton);
    	dialog.add(contactRadioButton);
    	dialog.add(descriptionRadioButton);

    	// Set the default selection to the description radio button
    	descriptionRadioButton.setSelected(true);
    
    	// Create input fields for contact name and description
    	JTextField contactField = new JTextField();
    	JTextField descriptionField = new JTextField();
    	dialog.add(new JLabel("Contact Name:"));
    	dialog.add(contactField);
    	// Disable Contact at first
    	contactField.setEnabled(false);
    	dialog.add(new JLabel("Description:"));
    	dialog.add(descriptionField);

    	// Enable/disable contact and description fields based on selected radio button
    	contactRadioButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        contactField.setEnabled(true);
    	        descriptionField.setEnabled(false);
    	    }
    	});
    	descriptionRadioButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        contactField.setEnabled(false);
    	        descriptionField.setEnabled(true);
    	    }
    	});

    	// Create a button to confirm the event creation
    	JButton confirmButton = null;
    	if(addOrRemove == true) {
    		confirmButton = new JButton("Add Event");
    	}
    	else {
    		confirmButton = new JButton("Remove Event");
    	}
    	
    	confirmButton.addActionListener(new ActionListener() {
    		
    	    public void actionPerformed(ActionEvent e) {
    	    	
    	    try {
    	    	// Retrieve the selected date and time
    	    	Date dateTime = (Date)dateSpinner.getValue();

    	    	// Convert Date to LocalDateTime
    	    	Instant instant = dateTime.toInstant();
    	    	ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
    	    	LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
    	    	DateTimeFormatter   formatter = DateTimeFormatter.ofPattern("dd - MM - yyyy HH:mm");
    	        String formattedDateTime = localDateTime.format(formatter);
    	        
    	    	// Parse to Date, Hour, Minutes
    	        LocalDateTime theLocalDateTime = LocalDateTime.parse(formattedDateTime, formatter);
    	        // Retrieve the duration
    	        int duration = (int)durationSpinner.getValue();
    	        
    	     
    	        Duration meetingDuration = Duration.ofMinutes(duration);
    	      
    	        
    	        // Retrieve the contact name or description based on the selected radio button
    	        String contactName = "";
    	        String description = "";
    	        
    	        if (contactRadioButton.isSelected() == true) {
    	            contactName = contactField.getText();
    	            Contact theContact = PhoneBookManager.ContactByName(contactName);
    	            
    	            if(theContact == null) {
    	            	// The Contact not exist,
    	            	JOptionPane.showMessageDialog(null,contactName + " not exist in Phone Book");
    	            	return;
  
    	            }
    	            // Check if add or remove
    	            if(addOrRemove == true) {
    	            	// Add it.
    	            	diaryManager.getDiary().addEvent(new MeetingEvent(theLocalDateTime, meetingDuration, theContact));
    	            }
    	            else {
    	            	// Remove it
    	            	diaryManager.getDiary().removeEvent(new MeetingEvent(theLocalDateTime, meetingDuration, theContact));
    	            }
    	            
    	        } else if (descriptionRadioButton.isSelected() == true) {
    	            description = descriptionField.getText();
    	            
    	            // Check if add or remove
    	            if(addOrRemove == true) {
    	            	// Add it.
    	            	diaryManager.getDiary().addEvent(new GeneralEvent(theLocalDateTime, meetingDuration, description));
    	            }
    	            else {
    	            	// Remove it
    	            	diaryManager.getDiary().removeEvent(new GeneralEvent(theLocalDateTime, meetingDuration, description));
    	            }
    	           
    	            
    	        }

    	        
    	        // Close the dialog
    	        dialog.dispose();
    	    	}
    	    	catch (Exception eexception) {
    				
    				JOptionPane.showMessageDialog(null,"An error occurred.");
    				eexception.printStackTrace();
    			}
    	    }
    	});
    	dialog.add(confirmButton);

    	// Set the layout manager and pack the dialog
    	dialog.setLayout(new GridLayout(8, 2));
    	dialog.pack();

    	// Display the dialog to the user
    	dialog.setVisible(true);
    	
    }

}


