package phoneApplications;

import java.util.ArrayList;
import java.util.Scanner;

public class DiaryManager implements ContactObserver, AppHandler{
	
	// Data Member
	private Diary diary;
	
	// Constructor
	public DiaryManager() {
		
		this.diary = new Diary();
	}
	
	// Implement the On contact remove method - Observer Design Pattern
	@Override
	public void onContactRemoved(Contact anyContact) {
		
		// Get all events of the given contact
		ArrayList<Event> allEventsOfGivenContact = this.diary.allEventsWithGivenContact(anyContact);
		
		// Remove all events that related to this contact
		for(Event event : allEventsOfGivenContact) {
			this.diary.removeEvent(event);
		}
	}
	
	// Check if Contact exists in the phone book - Observer Design Pattern
	@Override
	public boolean isContactExists(Contact anyContact) {
		
		return PhoneBookManager.isContactExist(anyContact);
	}
	
	@Override
	public void startApp() {
		
		Scanner scanner = new Scanner(System.in);
		
		boolean exit = false;
		
	
	        while (exit == false) {
	        	
	            printOptions();

	            String option = scanner.next();
			    scanner.nextLine(); 
			    
	            switch (option) {
	            
	                case "1":
	                	
	                    this.diary.addEvent(null);
	                    System.out.println("Adding an event to the diary");
	                    // Implement the logic to add an event
	                    break;
	                    
	                case "2":
	                    // Delete an event from the diary
	                    System.out.println("Deleting an event from the diary");
	                    // Implement the logic to delete an event
	                    break;
	                    
	                case "3":
	                    // Print all events of a certain date
	                    System.out.println("Printing all events of a certain date");
	                    // Implement the logic to print events of a certain date
	                    break;
	                    
	                case "4":
	                    // Print all meetings with the same contact in order of date
	                    System.out.println("Printing all meetings with the same contact in order of date");
	                    // Implement the logic to print meetings with the same contact
	                    break;
	                    
	                case "5":
	                    // Identify if there are events that collide with each other
	                    System.out.println("Identifying events that collide with each other");
	                    // Implement the logic to check for event collisions
	                    break;
	                    
	                case "6":
	                    // Print all events
	                    System.out.println("Printing all events");
	                    // Implement the logic to print all events
	                    break;
	                    
	                case "7":
	                    System.out.println("Exiting the application");
	                    exit = true;
	                    break;
	                    
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    break;
	            }

	        } 
		
	}
	
	@Override
	public void printOptions() {
		
		System.out.println("Enter an option:");
        System.out.println("1. Add an event to the diary");
        System.out.println("2. Delete an event from the diary");
        System.out.println("3. Print all events of a certain date");
        System.out.println("4. Print all meetings with the same contact in order of dates");
        System.out.println("5. Identify events that collide with each other and remove them");
        System.out.println("6. Print Diary");
        System.out.println("7. Exit");
	}
	
	/*
	 * public Event createEvent() {
	 * 
	 * Scanner scanner = new Scanner(System.in);
	 * 
	 * System.out.println("Please enter date");
	 * System.out.println("Please enter year");
	 * 
	 * String year = scanner.next(); scanner.nextLine(); }
	 */


}
