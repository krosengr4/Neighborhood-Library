public class UserInterface {

	public static String displayHomeScreen() {

		Utils.designLine(70, false);
		System.out.println("\t\tWELCOME TO THE NEIGHBORHOOD LIBRARY");
		Utils.designLine(70, true);
		return Utils.getUserInput("What is your name?: ");
	}

}
