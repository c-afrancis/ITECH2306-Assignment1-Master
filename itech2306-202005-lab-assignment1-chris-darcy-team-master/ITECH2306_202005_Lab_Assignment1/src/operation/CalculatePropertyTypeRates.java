package operation;

import java.text.NumberFormat;
import java.util.Scanner;

import domain.OtherProperty;
import domain.Property;
import domain.Residential;

/**
 * @author Takeogh
 *
 */
public class CalculatePropertyTypeRates extends FunctionalDialog {

	private static final int RESIDENTIAL = 1,
							 COMMERCIAL = 2,
							 VACANT_LAND = 3,
							 HOSPITAL = 4,
							 INDUSTRIAL =5,
							 SCHOOL_COMMUNITY = 6,
							 OTHER = 7,
							 END = 0;
	private static final int MAX_PROPERTY_TYPES = 7;
	private static final String PROPERTY_TYPE_PROMPT = "What type of property are we dealing with? \n" +
														RESIDENTIAL + ". Residential \n" +
														COMMERCIAL + ". Commercial \n" +
														VACANT_LAND + ". Vacant Land \n" +
														HOSPITAL + ". Hospital \n" +
														INDUSTRIAL + ". Industrial \n" +
														SCHOOL_COMMUNITY + ". School/Community \n" +
														OTHER + ". Other \n" +
														END + ".  To exit";
	
	private int propertyType;
	private static final String CIV_PROMPT = "What is the value of the property? ";
	private static final double MAX_CIV = 50000000.00;
	private static final NumberFormat MYFORMAT = NumberFormat.getNumberInstance();
	private double capitalImprovedValue;
	private static final int SMALL = 1, 
							 MEDIUM = 2,
							 LARGE = 3;
	private static final int MAX_COMMUNITY_CATEGORIES = 3;
	private static final String COMMUNITY_CATEGORY_PROMPT = "What type of category is the school or community building? \n" +
															SMALL + ". Small (1-20 members) \n" +
															MEDIUM +". Medium (21-100) \n" +
															LARGE + ". Large (101 upwards \n";
	private int communityCategory;
	private static final String CHARITY_STATUS_PROMPT = "Does the rate payer have a charity status? ";
	private boolean charityStatus;
	private static final int MAX_NO_USER_INPUTS = 4;
	
	public CalculatePropertyTypeRates(Scanner console) {
		super(MAX_NO_USER_INPUTS, console);
	}
	
	@Override
	public void obtainInput (int i) {
		switch (i)
		{
		case 0:
			propertyType = obtainIntInput(MAX_PROPERTY_TYPES, PROPERTY_TYPE_PROMPT);
			if (propertyType == END)
				setStillRunning(false);
			break;
		case 1:
			capitalImprovedValue = obtainDoubleInput(MAX_CIV, CIV_PROMPT);
			break;
		case 2:
			if (propertyType == SCHOOL_COMMUNITY)
				communityCategory = obtainIntInput(MAX_COMMUNITY_CATEGORIES, COMMUNITY_CATEGORY_PROMPT);
			break;
		case 3:
			charityStatus = obtainBooleanInput(CHARITY_STATUS_PROMPT);
			break;
		}
	}
	
	// These next input and validation methods could perhaps be tidied up/refactored
	private int obtainIntInput(int max, String prompt) {
		System.out.println(prompt);
		return validateInt(0, max);
	}

	private double obtainDoubleInput(double max, String prompt) {
		System.out.println(prompt);
		return validateDouble(100.00, max);
	}

	private boolean obtainBooleanInput(String prompt) {
		System.out.println(prompt);
		return validateBoolean();
	}
	
	private int validateInt(int min, int max) {
		int userInput;
		do {
			System.out.print("Enter a selection ("+min + "-" + max +"):");
			if (!getScanner().hasNextInt())
				userInput = max+1;
			else
				userInput = getScanner().nextInt();	// obtain the input
			getScanner().nextLine();					// gets rid of the newline after the number we just read
			if (userInput < min || userInput > max)
				System.out.println("Invalid choice.");
		} while (userInput < min || userInput > max);
		System.out.println();		// put a space before the next output	
		return userInput; 

	}
	
	private double validateDouble(double min, double max){
		double userInput;
		do {
			MYFORMAT.setMinimumFractionDigits(2);
			MYFORMAT.setMaximumFractionDigits(2);
			System.out.print("Enter a selection ("+ MYFORMAT.format(min) + "-" + MYFORMAT.format(max) +"):");

			if (!getScanner().hasNextDouble())
				userInput = max+0.01;
			else
				userInput = getScanner().nextDouble();	// obtain the input
			getScanner().nextLine();					// gets rid of the newline after the number we just read
			if (userInput < min || userInput > max)
				System.out.println("Invalid choice.");
		} while (userInput < min || userInput > max);
		System.out.println();		// put a space before the next output	
		return userInput; 

	}
	
	private boolean validateBoolean(){
		boolean userInput;
		System.out.print("Enter a selection -(true or false)");

		if (!getScanner().hasNextBoolean()) {
			userInput = false;
			System.out.println("Invalid choice. Assuming false.");
		}	
		else
			userInput = getScanner().nextBoolean();	// obtain the input
		getScanner().nextLine();					// gets rid of the newline after the input we just read
		System.out.println();		                // put a space before the next output	
		return userInput; 

	}

	@Override
	public void respondToInput() {
		Property property = null;
		switch (propertyType) 
		{
			case(RESIDENTIAL):
				property = new Residential();
				break;
			case(COMMERCIAL):
				break;
			case(VACANT_LAND):
				break;
			case(HOSPITAL):
				break;
			case(INDUSTRIAL):
				break;
			case(SCHOOL_COMMUNITY):
				break;
			case(OTHER):
				property = new OtherProperty();
				break;
			case(END):
				break;				
		}
		if (property != null) {
			property.setCapitalImprovedValue(capitalImprovedValue);
			property.setUpExtraServices();
			property.getOwner().setCharity(charityStatus);
			System.out.println("Details Selected: " + property +  "\n" +
							   "Total Rate Costs: " +property.calculateRates() + "\n");
		}

		
		
	}

}
