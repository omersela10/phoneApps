package phoneApplications;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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
            	addEvent();
                break;
            case "Delete Event":
                // Implement the logic for deleting an event
             
                break;
            case "Print Events by Date":
                // Implement the logic for printing events by date
              
                break;
            case "Print Meetings by Contact":
                // Implement the logic for printing meetings by contact
                
                break;
            case "Check Event Collisions":
                // Implement the logic for checking event collisions
           
                break;
            case "Print All Events":
                // Implement the logic for printing all events
               
                break;
            default:
                break;
        }
    }
    
private void addEvent() {
    	
    	// Create a custom dialog to get the event details
    	JDialog dialog = new JDialog();
    	dialog.setTitle("Add Event");
    	dialog.setModal(true);

    	// Create input fields for date and time
    	SpinnerDateModel dateModel = new SpinnerDateModel();
    	JSpinner dateSpinner = new JSpinner(dateModel);
    	JLabel dateLabel = new JLabel("Date:");
    	dialog.add(dateLabel);
    	dialog.add(dateSpinner);

    	SpinnerNumberModel hoursModel = new SpinnerNumberModel(0, 0, 23, 1);
    	JSpinner hoursSpinner = new JSpinner(hoursModel);
    	SpinnerNumberModel minutesModel = new SpinnerNumberModel(0, 0, 59, 1);
    	JSpinner minutesSpinner = new JSpinner(minutesModel);
 
    	// Create input fields for duration
    	JTextField durationField = new JTextField();
    	dialog.add(new JLabel("Duration (minutes):"));
    	dialog.add(durationField);

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
    	JButton confirmButton = new JButton("Add Event");
    	confirmButton.addActionListener(new ActionListener() {
    		
    	    public void actionPerformed(ActionEvent e) {
    	    	
    	    	try {
    	        // Retrieve the selected date and time
    	        Date selectedDate = (Date) dateSpinner.getValue();
    	        int selectedHour = (int) hoursSpinner.getValue();
    	        int selectedMinute = (int) minutesSpinner.getValue();

    	        // Combine the date and time into a LocalDateTime object
    	        LocalDateTime dateTime = LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault())
    	                .withHour(selectedHour).withMinute(selectedMinute);

    	        // Retrieve the duration
    	        int duration = Integer.parseInt(durationField.getText());
    	        Duration meetingDuration = Duration.ofMinutes(duration);
    	      
    	        // Retrieve the contact name or description based on the selected radio button
    	        String contactName = "";
    	        String description = "";
    	        if (contactRadioButton.isSelected() == true) {
    	            contactName = contactField.getText();
    	            Contact theContact = PhoneBookManager.ContactByName(contactName);
    	            if(theContact == null) {
    	            	JOptionPane.showMessageDialog(null,contactName + "not exist in Phone Book");
  
    	            }
    	            // The Contact exist, add it.
    	            diaryManager.getDiary().addEvent(new MeetingEvent(dateTime, meetingDuration, theContact));
    	            
    	        } else if (descriptionRadioButton.isSelected() == true) {
    	            description = descriptionField.getText();
    	            diaryManager.getDiary().addEvent(new GeneralEvent(dateTime, meetingDuration, description));
    	            
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


