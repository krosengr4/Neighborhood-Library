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
				0 - Exit
				""");
		return Utils.getUserInputIntMinMax("Enter your option: ", 0, 6);
	}

}
