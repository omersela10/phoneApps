package phoneApplications;

import java.util.Scanner;

public class SMSManager implements AppHandler, ContactObserver {

	private SMSList sms;
	
	public SMSManager () {
		
		this.sms = new SMSList();
	}
	
	@Override
	public void onContactRemoved(Contact anyContact) {
		
		this.sms.deleteAllContentsWithContact(anyContact);
	}

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
					// Add content to contact
			    	if(PhoneBookManager.isContactExist("omer")==true) {
			    		
			    	}
			    	else {
			    		
			    	}
					break;

			    case "2":
					// Delete a sms with contact
					break;

			    case "3":
			    	// Print SMS with contact
			    
			    	break;

			    case "4":
					// Search for a sentence
			
				    break;

			    case "5":
			    	// Print all SMS
			    	
			    	break;
			    	
			    case "6":
			    	// Exit
			    	
			    	break;
			    	
			    default:
			    	System.out.println("Invalid option. Try again.");
			}

		}
		
	}

	@Override
	public void printOptions() {
		 // Print options
		System.out.println("Enter an option:");
		System.out.println("1. Add content to contact");
		System.out.println("2. Delete a SMS with contact");
		System.out.println("3. Print SMS with contact");
		System.out.println("4. Search for a sentence");
		System.out.println("5. Print all SMS");
		System.out.println("6. Exit");
		
	}

}
