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
			    	System.out.println("Enter name from phone book.");
					String name = scanner.nextLine();
					
			    	if(PhoneBookManager.isContactExist(name)==true) {
			    		
			    		System.out.println("Enter the content with this contact.");
						String text = scanner.nextLine();
						this.sms.addContentToContact(PhoneBookManager.ContactByName(name), text);
			    	}
			    	else {
			    		
			    		System.out.println("This contact doesnt exist in the phone book.");
			    		System.out.println("If you want add content with this contact, please add this contact to your phone book.");
			    	}
			    	
					break;

			    case "2":
					// Delete a sms with contact
			    	System.out.println("Enter name from phone book to delete the SMS with him.");
					String nameToDelete = scanner.nextLine();
					
					this.sms.deleteAllContentsWithContact(PhoneBookManager.ContactByName(nameToDelete));
			    	
					break;

			    case "3":
			    	// Print SMS with contact
			    	System.out.println("Enter name from phone book to print the SMS with him.");
					String nameToPrint = scanner.nextLine();
					
					this.sms.printAllContentsWithContact(PhoneBookManager.ContactByName(nameToPrint));
					
			    	break;

			    case "4":
					// Search for a sentence
			    	System.out.println("Enter sentence to search.");
					String sentence = scanner.nextLine();
					
					this.sms.printAllContactContainDesireSentence(sentence);
					
				    break;

			    case "5":
			    	// Print all SMS
			    	this.sms.printAllSMSList();
			    	
			    	break;
			    	
			    case "6":
			    	// Exit
			    	System.out.println("Exiting...");
					exit = true;
			
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
