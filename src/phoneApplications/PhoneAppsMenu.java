package phoneApplications;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.io.*;

// Group Serial Number: 1

// Ahigad Genish  316228022
// Omer Sela      316539535	
// Shir Cohen     314624040
// Almog Sharoni  208611764 
// Yakov Avitan   205517089

public class PhoneAppsMenu{

	private static ContactManager ourListManager;
	//private static SMSManager ourSMSListManager;
	private static DiaryManager ourDiaryManager;
	//private static MediaManager ourMediaManager;
	
	public static void main(String[] args) {
		
		// Hello message
		System.out.println("Welcome to our Phone Apps applications!");
		
		// Create ContactList instance
		ourListManager = new ContactManager();
		//ourSMSListManager = new SMSManager();
		ourDiaryManager = new DiaryManager();
		
		// Start application
		startApp();
		
		return;
	}
	
	
		
	// Print the menu and choose options.
	public static void startApp() {
		
	
		Scanner scanner = new Scanner(System.in);
		
		boolean exit = false;
		
	
	    while (exit == false) {
	    	 
	    	printOptions();
	    	
		    String option = scanner.next();
		    scanner.nextLine(); 

		    switch (option) {

			    case "1":
			    	ourListManager.startApp();
			    	break;

			    case "2":
			    	//ourSMSManager.startApp();
			    	break;

			    case "3":
					ourDiaryManager.startApp();
					break;

			    case "4":
					//ourMediaManager.startApp();
					break;

			    case "5":
					// Exit.
					System.out.println("Exiting...");
					exit = true;
					break;
					
			    default:
			    	System.out.println("Invalid option. Try again.");
			}

		}
	       
	}
	
	// Print Options
	public static void printOptions() {
		
		 // Print options
		System.out.println("Enter an option:");
		System.out.println("1. Phone Book");
		System.out.println("2. SMS");
		System.out.println("3. Diary");
		System.out.println("4. Media");
		System.out.println("5. Exit");
	
	}

}
