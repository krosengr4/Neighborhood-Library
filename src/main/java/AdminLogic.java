import com.mysql.cj.log.Log;

public class AdminLogic {

	static MySqlBookDao bookDao = Logic.bookDao;

	public static void processAdminOptions() {
		boolean ifAdminContinue = true;

		while(ifAdminContinue) {
			int adminChoice = UserInterface.displayAdminScreen();

			switch(adminChoice) {
				case 1 -> processAddBook();
				case 2 -> processUpdateBook();
				case 3 -> processDeleteBook();
				case 0 -> ifAdminContinue = false;
			}
		}
	}

	private static void processAddBook() {
		String ibsn = Utils.getUserInput("Enter the IBSN: ");
		String title = Utils.getUserInput("Enter the Title: ");
		String author = Utils.getUserInput("Enter the Author: ");
		int publishedYear = Utils.getUserInputInt("Enter the year the book was published: ");

		String userAvailability = Utils.getUserInput("Is the book checked out? (Y or N): ").trim();
		boolean checkedOut = userAvailability.equalsIgnoreCase("y");

		String checkedOutBy = null;
		if(checkedOut) {
			checkedOutBy = Utils.getUserInput("Enter who the book was checked out by: ");
		}

		Book book = bookDao.addBook(new Book(0, ibsn, title, checkedOut, author, publishedYear, checkedOutBy));
		book.print();

		Utils.pauseApp();
	}

	private static void processUpdateBook() {
		System.out.println("Update Book");
	}

	private static void processDeleteBook() {
		System.out.println("Delete book");
	}

}
