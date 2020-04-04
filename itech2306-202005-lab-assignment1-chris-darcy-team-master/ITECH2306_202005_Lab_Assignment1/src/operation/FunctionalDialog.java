package operation;

import java.util.Scanner;

/**
 * @author Takeogh
 *
 */
public abstract class FunctionalDialog {
	private Scanner console;
	private boolean stillRunning;
	private int numberOfInputs;
	private String title;

	public FunctionalDialog(int noOfInputs, Scanner console) {
		this.setNumberOfInputs(noOfInputs);
		this.setScanner(console);
	}
	
	protected void operateDialog() {
		setStillRunning(true);
		System.out.println(getTitle());
		while (getStillRunning())
		{
			for(int i= 0; i < getNumberOfInputs(); i++) {
				obtainInput(i);
				if (!getStillRunning())
					break;
			}
			if (getStillRunning())
				respondToInput();
		}
		return;
	}

	private void setScanner(Scanner console) {
		this.console = console;
	}
	
	protected Scanner getScanner() {
		return this.console;
	}
	
	private boolean getStillRunning() {
		return this.stillRunning;
	}

	protected void setStillRunning(boolean b) {
		this.stillRunning = b;
	}

	protected abstract void obtainInput(int i);
		
	protected abstract void respondToInput();
	
	protected void setNumberOfInputs(int noOfInputs) {
		this.numberOfInputs = noOfInputs;
	}
	protected int getNumberOfInputs() {
		return this.numberOfInputs;
	}

	public void setTitle(String title) {
		this.title = title;		
	}
	public String getTitle() {
		return this.title;		
	}

}
