package phoneApplications;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PhoneAppsMenu extends JFrame {

	private static PhoneBookManager phoneBookManager;
    private static DiaryManager diaryManager;
    private static SMSManager smsManager;
    //private static MediaManager mediaManager;
    
	// Main
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PhoneAppsMenu();
            }
        });
    }
    
    // Constructor
    public PhoneAppsMenu() {
    	
    	 // Initialize the app managers
        phoneBookManager = new PhoneBookManager();
        diaryManager = new DiaryManager();
        smsManager = new SMSManager();
        //mediaManager = new MediaManager();
    
        // Add Observers - Observer Design Pattern
        phoneBookManager.registerObserver(diaryManager);
        phoneBookManager.registerObserver(smsManager);
        
        // Create GUI :
        setTitle("Phone Applications");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        // Create the buttons with icons
        JButton phoneBookButton = createButton("PhoneBook", "resources/phonebook.png");
        JButton smsSenderButton = createButton("SMSSender", "resources/smssender.png");
        JButton diaryButton = createButton("Diary", "resources/diary.png");
        JButton mediaButton = createButton("Media", "resources/media.png");

        // Create the main panel
        JPanel mainPanel = new JPanel(new GridLayout(2, 2));
        mainPanel.add(phoneBookButton);
        mainPanel.add(smsSenderButton);
        mainPanel.add(diaryButton);
        mainPanel.add(mediaButton);
        
        // Create the copyright label
        JLabel copyrightLabel = new JLabel("© Group Number 1");
        copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create the bottom panel and add the copyright label
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(copyrightLabel, BorderLayout.CENTER);

        // Create the main container panel and add the main and bottom panels
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(mainPanel, BorderLayout.CENTER);
        containerPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the container panel to the frame
        add(containerPanel);
 
        // Pack and display the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Create Button and the event
    private JButton createButton(String text, String iconFileName) {
    	
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(iconFileName));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAppWindow(text);
            }
        });
        return button;
    }

    // Open the desire app.
    private void openAppWindow(String appName) {
    	
    	// Logic to open the app window based on the app name
        switch (appName) {
        case "PhoneBook":
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    PhoneBookWindow.getInstance(phoneBookManager);
                }
            });
            break;
            
        case "Diary":
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    DiaryWindow.getInstance(diaryManager);
                }
            });
            break;
            
        case "SMSSender":
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                   SMSWindow.getInstance(smsManager);
                }
            });
            break;
            
        case "Media":
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //new MediaWindow(mediaManager);
                }
            });
            break;
            
        default:
        	JOptionPane.showMessageDialog(this,"Invalid Option");
            break;
        }
    }

    
}