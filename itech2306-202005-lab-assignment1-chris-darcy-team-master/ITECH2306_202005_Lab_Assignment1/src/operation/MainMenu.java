package operation;

import java.util.Scanner;

/**
 * @author Takeogh
 *
 */
public class MainMenu {
	private Scanner console;
	private int choice;
	private boolean stillRunning;
	private final static String SYSTEM_TITLE = "Council Rate Payer System \n" +
											   "=========================";
	final static String ESC = "\033["; //Something to flush the Eclipse console
	
	public MainMenu (){
	}	
		
	public void operateMenu() {
		console = new Scanner(System.in);
		stillRunning = true;		// in order to commence program
		
		while (stillRunning)
		{
			showMainMenu();
			choice = getUserSelection(0,3);
			processChoiceMainMenu(choice);
		}
		console.close();		
	}

	private void showMainMenu() {
		
		System.out.println();		// ensure a break between previous output and the menu
		System.out.println(SYSTEM_TITLE);
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1.  Add a Rate Payer");
		System.out.println("2.  Add a Property");
		System.out.println("3.  Calculate a Property Type's Yearly Rates Total");
		System.out.println("0.  Exit Program");

	}

	private int getUserSelection(int lower, int upper) {
	
		if (lower > upper)
			return 0;
		int userInput;
		do {
			System.out.print("Enter a selection ("+lower + "-" + upper+"):");
			if (!console.hasNextInt())
				userInput = upper+1;
			else
				userInput = console.nextInt();	// obtain the input
			console.nextLine();					// gets rid of the newline after the number we just read
			if (userInput < lower || userInput > upper)
				System.out.println("Invalid choice.");
		} while (userInput < lower || userInput > upper);
		System.out.println();		// put a space before the next output	
		return userInput; 

	}

	private void processChoiceMainMenu(int choice) {
		
		FunctionalDialog fd = null;
		switch (choice)
		{
			case 1:
				fd = new AddRatePayer(console); // we pass the console as a compromise; we want to avoid system resource issues in Eclipse
				break;
			case 2:
				fd = new AddProperty(console);
				break;
			case 3:
				fd = new CalculatePropertyTypeRates(console);
				break;
			case 0:
				System.out.println("Exiting the system...");
				System.out.print(ESC + "2J"); //Something to flush the Eclipse console
				stillRunning = false;		  // causes the main loop of program to end (i.e. quits)
				break;
			default:
				System.out.println("Unexpected selection made. Doing nothing.");
				break;
		}
		if(fd != null) {
			fd.setTitle(SYSTEM_TITLE + "\n" + fd.getClass().getSimpleName() + "\n");
			fd.operateDialog();
		}	
	}

}
