package phoneApplications;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;


public class MediaWindow extends JFrame{

	
	  private static MediaWindow mediaWindowInstance; 
	  private MediaManager mediaManager; 
	  // Text area to display the media
	  private JTextArea mediaTextArea;
	  
	  // Singleton Design Pattern 
	  private MediaWindow(MediaManager newMediaManager) {
	       this.mediaManager = newMediaManager;
	  }
	  
	  public static synchronized MediaWindow getInstance(MediaManager newMediaManager) {
	  
		  if (mediaWindowInstance == null) { 
			  mediaWindowInstance = new MediaWindow(newMediaManager);
		  } 
		  initialize();
	  
		  return mediaWindowInstance; 
	  
	  }
	  
	  
	  private static void initialize() {
	  
		  mediaWindowInstance.setTitle("Media");
		  mediaWindowInstance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		  mediaWindowInstance.setPreferredSize(new Dimension(800, 400));
	  
		  // Set up the window components:
		  
		  // Create the buttons
		  JButton addMediaButton = mediaWindowInstance.createButton("Add Media"); 
		  JButton playMediaByNameButton = mediaWindowInstance.createButton("Play Media By Name"); 
		  JButton playAllMediaButton = mediaWindowInstance.createButton("Play All Media"); 
		 		
	 
		  // Create the text area
		  mediaWindowInstance.mediaTextArea = new JTextArea();
		  mediaWindowInstance.mediaTextArea.setEditable(false); // Make it read-only
	      JScrollPane scrollPane = new JScrollPane(mediaWindowInstance.mediaTextArea);

		  // Create the main panel with BorderLayout 
		  JPanel mainPanel = new JPanel(new BorderLayout());
	  
		  // Create the left panel with GridLayout for the buttons 
		  JPanel leftPanel = new JPanel(new GridLayout(3, 1)); 
		  leftPanel.add(addMediaButton);
		  leftPanel.add(playMediaByNameButton);
		  leftPanel.add(playAllMediaButton);
		
	  
		  // Add the left panel to the main panel on the left side
		  mainPanel.add(leftPanel, BorderLayout.WEST);
	  
		  // Add the scroll pane to the main panel on the right side
		  mainPanel.add(scrollPane, BorderLayout.CENTER);
	  
		  // Add the main panel to the frame 
		  mediaWindowInstance.add(mainPanel);
	  
		  // Pack and display 
		  mediaWindowInstance.pack();
		  mediaWindowInstance.setLocationRelativeTo(null);
		  mediaWindowInstance.setVisible(true); 
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
	  
	  // Handle Buttons event
	  private void handleButtonAction(String buttonText) {
		  
		  // TODO: 
		  switch(buttonText) {
		      case "Add Media":
		    	  addMedia();
		    	  break;
		    	  
		      case "Play Media By Name":
		    	  try {
		    		  playMediaByName();
		    	  }
		    	  catch(Exception e) {
		    		  
		    	  }
		    	  break;
		    	  
		  	  case "Play All Media":
		  		  playAllMedia();
		  		  break;
		  }
		  
	  }

	// Add media function
	private void addMedia() {
		// TODO: Get the media from user, ask for Video\ MUSIC by radio button. name and duration
		// Add it to the media list.
    	// Create a custom dialog to get the event details
    	JDialog dialog = new JDialog();
    	dialog.setTitle("Add Media");
    	dialog.setModal(true);

    	// Create input fields for Name
//    	SpinnerDateModel MediaModel = new SpinnerDateModel();
    	
    	JTextField mediaLabel = new JTextField();
    	dialog.add(new JLabel("Name:"));
    	dialog.add(mediaLabel);

    	
    	// Create input fields for duration
    	SpinnerNumberModel mediaModel = new SpinnerNumberModel();
    	JSpinner mediaSpinner = new JSpinner(mediaModel);
    	dialog.add(new JLabel("Duration (minutes):"));
    	dialog.add(mediaSpinner);
//    	dialog.add(durationField);
    	
    	// Create radio buttons for contact picker
    	JRadioButton musictRadioButton = new JRadioButton("Music");
    	JRadioButton videoRadioButton = new JRadioButton("Video");
    	ButtonGroup radioButtonGroup = new ButtonGroup();
    	radioButtonGroup.add(musictRadioButton);
    	radioButtonGroup.add(videoRadioButton);
    	dialog.add(musictRadioButton);
    	dialog.add(videoRadioButton);
    	
    	// Create a button to confirm the media creation
    	JButton confirmButton = new JButton("Add Media");
    	confirmButton.addActionListener(new ActionListener() {
    		
    	    public void actionPerformed(ActionEvent e) {
    	    	
    	    try {
    	    	// Retrieve the selected date and time
    	    	Integer time = (int) mediaSpinner.getModel().getValue();
    	    	Duration duration = Duration.ofMinutes(time);
//    	    	// Convert Date to LocalDateTime
//    	    	Instant instant = dateTime.toInstant();
//    	    	ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
//    	    	LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
//    	    	DateTimeFormatter   formatter = DateTimeFormatter.ofPattern("dd - MM - yyyy HH:mm");
//    	        String formattedDateTime = localDateTime.format(formatter);
//    	        
//    	    	// Parse to Date, Hour, Minutes
//    	        LocalDateTime theLocalDateTime = LocalDateTime.parse(formattedDateTime, formatter);
//    	        // Retrieve the duration
//    	        int duration = Integer.parseInt(durationField.getText());
//    	        Duration meetingDuration = Duration.ofMinutes(duration);
    	      
    	    	
    	        // Retrieve the contact name or description based on the selected radio button
    	        String mediaName = mediaLabel.getText();
    	        Media media = null;
    	        
    	        if (musictRadioButton.isSelected() == true) {
    	        //TODO: music checked
    	          Music newMusic = new Music(mediaName, duration);
    	          media = newMusic;
    	        } else if (videoRadioButton.isSelected() == true) {
    	        //TODO: video checked
    	           Video newVideo = new Video(mediaName, duration);
     	          media = newVideo;

    	        }
    	        //TODO: add media to DB
    	       
    	        mediaManager.getMedaiaPlayer().addMedia(media);
    	        
    	        // Close the dialog
    	        dialog.dispose();
    	    	}
    	    	catch (Exception eexception) {
    				
    				JOptionPane.showMessageDialog(null, eexception);
    				eexception.printStackTrace();
    			}
    	    }
    	});
    	dialog.add(confirmButton);
    	
    	
    	// Set the layout manager and pack the dialog
    	dialog.setLayout(new GridLayout(4, 3));
    	dialog.pack();

    	// Display the dialog to the user
    	dialog.setVisible(true);
    	
	}
	
	// Play Media by name
	private void playMediaByName() throws IOException, URISyntaxException {
		
		// TODO: Get The mediaName from user and check it's not null.
			// Create a custom dialog to get the event details
	    	JDialog dialog = new JDialog();
	    	dialog.setTitle("Media Name");
	    	dialog.setModal(true);

	    	//Create input fields for Name
	    	//SpinnerDateModel MediaModel = new SpinnerDateModel();
	    	
	    	JTextField mediaLabel = new JTextField();
	    	dialog.add(new JLabel("Media Name:"));
	    	dialog.add(mediaLabel);
	    	
	    	// Create a button to confirm the media creation
	    	JButton confirmButton = new JButton("Play Media");
	    	confirmButton.addActionListener(new ActionListener() {
	    		
	    	    public void actionPerformed(ActionEvent e) {
	    	    	
	    	    try {
	 
	    	     	    	
	    	        // Retrieve the media name for search
	    	        String mediaForSearch = mediaLabel.getText();
	    	        
	    	       
	    	        mediaManager.getMedaiaPlayer().playMediaByName(mediaForSearch, mediaTextArea);
	    	        
	    	        // Close the dialog
	    	        dialog.dispose();
	    	        
	    	    	//Play the song
	    			String mediaName = mediaForSearch;
	    			mediaTextArea.setText("");
	    			mediaManager.getMedaiaPlayer().playMediaByName(mediaName, mediaTextArea);
	    	    	}
	    	    	catch (Exception eexception) {
	    				
	    				JOptionPane.showMessageDialog(null, eexception);
	    				eexception.printStackTrace();
	    			}
	    	    }
	    	});
	    	dialog.add(confirmButton);
	    	
	    	// Set the layout manager and pack the dialog
	    	dialog.setLayout(new GridLayout(4, 3));
	    	dialog.pack();

	    	// Display the dialog to the user
	    	dialog.setVisible(true);


			
	}
	
	// Add media function
	private void playAllMedia() {
		
		// Print all Media
		mediaTextArea.setText("");
		mediaManager.getMedaiaPlayer().playingAllMedia(mediaTextArea);
		
	}

	
	  
 }
    
