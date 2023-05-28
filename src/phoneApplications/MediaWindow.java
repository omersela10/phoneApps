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
		  
	  }
	  
 }
    
