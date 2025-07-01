public class Logic {

	public static void processHomeScreen() {
		boolean ifContinue = true;

		while(ifContinue) {
			int userAction = UserInterface.displayHomeScreen();

			switch(userAction) {
				case 1 -> processShowAvailableBooks();
				case 2 -> processShowCheckedOutBooks();
				case 3 -> processCheckOutBook();
				case 4 -> processReturnBook();
				case 0 -> ifContinue = false;
			}
		}
	}

	private static void processShowAvailableBooks() {
		System.out.println("available books");
	}

	private static void processShowCheckedOutBooks() {
		System.out.println("Checked out books");
	}

	private static void processCheckOutBook() {
		System.out.println("Check out a book");
	}

	private static void processReturnBook() {

	}

}
