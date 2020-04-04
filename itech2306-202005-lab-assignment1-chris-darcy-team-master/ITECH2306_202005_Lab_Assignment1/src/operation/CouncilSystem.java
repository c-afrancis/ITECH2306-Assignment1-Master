package operation;

/**
 * @author Takeogh
 *
 */
public class CouncilSystem {

	public static void main(String[] args) {
		CouncilSystem cs = new CouncilSystem();
		cs.startApplication();
	}

	private void startApplication() {
		MainMenu mm = new MainMenu();
		mm.operateMenu();	
	}

}
