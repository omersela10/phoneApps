package mediaApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

// Window will be Singleton
public class MediaWindow extends JFrame{

	
	  private static MediaWindow mediaWindowInstance; 
	  private MediaManager mediaManager; 
	  // Text area to display the media
	  private JTextArea mediaTextArea;
	  
	  // Singleton Design Pattern 
	  private MediaWindow(MediaManager newMediaManager) {
	       this.mediaManager = newMediaManager;
	  }
	  
	  // Get Instance - Singleton Design Pattern 
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
		  
		  switch(buttonText) {
		      case "Add Media":
		    	  addMedia();
		    	  break;
		    	  
		      case "Play Media By Name":
		    	  playMediaByName();
		    	  break;
		    	  
		  	  case "Play All Media":
		  		  playAllMedia();
		  		  break;
		  }
		  
	  }

	// Add media function
	private void addMedia() {
		
	        // Create a custom dialog to get the media details
	        JDialog dialog = new JDialog();
	        dialog.setTitle("Add Media");
	        dialog.setModal(true);
	        dialog.setLayout(new GridLayout(5, 2));

	        // Create input fields for Name
	        JTextField mediaLabel = new JTextField();
	        dialog.add(new JLabel("Media Name:"));
	        dialog.add(mediaLabel);

	        // Create time picker fields for duration
	        JSpinner hoursSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
	        JSpinner minutesSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
	        JSpinner secondsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

	        JPanel durationPanel = new JPanel(new FlowLayout());
	        durationPanel.add(hoursSpinner);
	        durationPanel.add(new JLabel("h "));
	        durationPanel.add(minutesSpinner);
	        durationPanel.add(new JLabel("m "));
	        durationPanel.add(secondsSpinner);
	        durationPanel.add(new JLabel("s "));

	        dialog.add(new JLabel("Duration:"));
	        dialog.add(durationPanel);

	        // Create radio buttons for media type
	        JRadioButton musicRadioButton = new JRadioButton("Music");
	        JRadioButton videoRadioButton = new JRadioButton("Video");
	        // Set "Music" as selected by default
	        musicRadioButton.setSelected(true); 
	        ButtonGroup radioButtonGroup = new ButtonGroup();
	        radioButtonGroup.add(musicRadioButton);
	        radioButtonGroup.add(videoRadioButton);
	        dialog.add(musicRadioButton);
	        dialog.add(videoRadioButton);

	        // Create a button to confirm the media creation
	        JButton confirmButton = new JButton("Add Media");
	        confirmButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    // Retrieve the hours, minutes, and seconds from the spinners
	                    int hours = (int) hoursSpinner.getValue();
	                    int minutes = (int) minutesSpinner.getValue();
	                    int seconds = (int) secondsSpinner.getValue();

	                    // Calculate the total duration in seconds
	                    long totalSeconds = (hours * 3600) + (minutes * 60) + seconds;
	                    Duration duration = Duration.ofSeconds(totalSeconds);

	                    // Retrieve the media name
	                    String mediaName = mediaLabel.getText();
	                    if(mediaName == null || mediaName.equals("") == true || totalSeconds == 0){
	                    	JOptionPane.showMessageDialog(null, "Please insert Media Name and Duration");
	                    	return;
	                    }
	                    

	                    // Create the media object based on the selected radio button
	                    Media theMedia;
	                    if (musicRadioButton.isSelected() == true) {
	                    	theMedia = new Music(mediaName, duration);
	                    } else {
	                    	theMedia = new Video(mediaName, duration);
	                    }
	                    
	                    // Add this media to the list.
	                    mediaManager.getMedaiaPlayer().addMedia(theMedia);
	                  
	                    

	                    // Close the dialog
	                    dialog.dispose();
	                } 
	                catch (Exception exception) {
	                    JOptionPane.showMessageDialog(null, "Invalid duration format");
	                }
	            }
	        });
	        dialog.add(confirmButton);

	        // Set the layout manager and pack the dialog
	        dialog.setLayout(new GridLayout(5, 2));
	        dialog.pack();

	        // Display the dialog to the user
	        dialog.setVisible(true);
	    }
    	
	
	// Play Media by name
	private void playMediaByName() {
		
		// Open dialog to get mediaName details and retrieve the media name for play
	    String mediaName = JOptionPane.showInputDialog(this, "Enter Media Name:");   	    
		if(mediaName.isBlank() == true) {
			return;
		}
			
		mediaManager.getMedaiaPlayer().playMediaByName(mediaName, mediaTextArea);
			
	}
	
	// Play all media function
	private void playAllMedia() {
		
		// Print all Media
		mediaManager.getMedaiaPlayer().playingAllMedia(mediaTextArea);
		
	}

	
	  
 }
    
