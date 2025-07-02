public class UserInterface {

	public static int displayHomeScreen(String userName) {
		Utils.designLine(70, false);
		System.out.println("\t\tWELCOME " + userName.toUpperCase() + " TO THE NEIGHBORHOOD LIBRARY");
		Utils.designLine(70, true);

		System.out.println("""
					-----OPTIONS-----
				1 - Show Available Books
				2 - Show Checked Out Books
				3 - Search Books By Author
				4 - Search Books By Title
				5 - Check Out A Book
				6 - Return A Book
				7 - Admin Screen
				0 - Exit
				""");
		return Utils.getUserInputIntMinMax("Enter your option: ", 0, 7);
	}

	public static int displayAdminScreen() {
		Utils.designLine(70, false);
		System.out.println("\t\tADMIN SCREEN");
		Utils.designLine(70, true);

		System.out.println("""
				1 - Add A Book
				2 - Update A Book
				3 - Delete A Book
				0 - Go Back
				""");

		return Utils.getUserInputIntMinMax("Enter your option: ", 0, 3);
	}

}
