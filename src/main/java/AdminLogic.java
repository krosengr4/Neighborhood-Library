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
		int bookId = Utils.getUserInputInt("Enter the bookID of the book you want to update: ");
		int updateChoice = UserInterface.displayUpdateBookScreen();
		Book updateBook = bookDao.getBookById(bookId);

		switch(updateChoice) {
			case 1 -> updateBook.setIsbn(Utils.getUserInput("Enter the new IBSN: ").trim());
			case 2 -> updateBook.setTitle(Utils.getUserInput("Enter the new Title: ").trim());
			case 3 -> updateBook.setAuthor(Utils.getUserInput("Enter the new Author: ").trim());
			case 4 -> updateBook.setPublishedYear(Utils.getUserInputInt("Enter the new published year: "));
			case 5 -> updateBook.setCheckedOutBy(Utils.getUserInput("Enter who the book is now checked out by: "));
			case 0 -> {
				return;
			}
		}

		bookDao.updateBook(updateBook, bookId);
	}

	private static void processDeleteBook() {
		int bookId = Utils.getUserInputInt("Enter the BookID of the book to be deleted: ");
		Book book = bookDao.getBookById(bookId);

		String deleteConfirmation = Utils.getUserInput("Are you sure you want to delete " + book.getTitle() + "? (Y or N): ");
		if(deleteConfirmation.equalsIgnoreCase("y")) {
			bookDao.deleteBook(bookId);
		}
	}

}
