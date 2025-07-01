public class UserInterface {

	public static String displayMain() {
		Utils.designLine(70, false);
		System.out.println("\t\tWELCOME TO THE NEIGHBORHOOD LIBRARY");
		Utils.designLine(70, true);
		return Utils.getUserInput("Enter Your Name: ");
	}

	public static int displayHomeScreen() {
		System.out.println("""
					-----OPTIONS-----
				1 - Show Available Books
				2 - Show Checked Out Books
				3 - Check Out A Book
				4 - Return A Book
				0 - Exit
				""");
		return Utils.getUserInputInt("Enter your option: ");
	}

}
