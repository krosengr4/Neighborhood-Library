import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class Logic {

	static BasicDataSource dataSource = new BasicDataSource();
	static MySqlBookDao bookDao = new MySqlBookDao(dataSource);
	static String userName;

	public static void setDataSource() {
		dataSource.setUrl("jdbc:mysql://localhost:3306/neighborhood_library");
		dataSource.setUsername("root");
		dataSource.setPassword(System.getenv("SQL_PASSWORD"));
	}

	public static void processHomeScreen() {
		userName = Utils.getUserInput("Please Enter Your Name: ").trim();

		boolean ifContinue = true;

		while(ifContinue) {
			int userAction = UserInterface.displayHomeScreen(userName);

			switch(userAction) {
				case 1 -> processShowAvailableBooks();
				case 2 -> processShowCheckedOutBooks();
				case 3 -> processSearchBookAuthor();
				case 4 -> processSearchBookTitle();
				case 5 -> processCheckOutBook();
				case 6 -> processReturnBook();
				case 0 -> ifContinue = false;
			}
		}
	}

	private static void processShowAvailableBooks() {
		List<Book> bookList = bookDao.getAvailable();

		if(bookList.isEmpty()) {
			System.out.println("There are no available books at this time...");
		} else {
			for(Book book : bookList) {
				book.print();
				System.out.println("----------------------------------------");
			}
		}
		System.out.println("Number of Books Available: " + bookList.size());
		Utils.pauseApp();
	}

	private static void processShowCheckedOutBooks() {
		List<Book> bookList = bookDao.getCheckedOut();

		if(bookList.isEmpty()) {
			System.out.println("There are no checked out books right now...");
		} else {
			for(Book book : bookList) {
				book.print();
				System.out.println("----------------------------------------");
			}
		}
		System.out.println("Number of Checked Out Books: " + bookList.size());
		Utils.pauseApp();
	}

	private static void processSearchBookAuthor() {
		String authorToSearch = Utils.getUserInput("Enter the name of the author: ");
		List<Book> bookList = bookDao.searchByAuthor(authorToSearch);

		if(bookList.isEmpty()) {
			System.out.println("We do not have any books by that author right now...");
		} else {
			for(Book book : bookList) {
				book.print();
				System.out.println("----------------------------------------");
			}
		}
		Utils.pauseApp();
	}

	private static void processSearchBookTitle() {
		String titleToSearch = Utils.getUserInput("Enter the title of the book: ");
		Book book = bookDao.searchByTitle(titleToSearch);

		if(book == null) {
			System.out.println("There are no books with that title...");
		} else {
			book.print();
			System.out.println("----------------------------------------");
		}
		Utils.pauseApp();
	}

	private static void processCheckOutBook() {
		int bookId = Utils.getUserInputInt("Enter the ID of the book to check out: ");
		Book book = bookDao.getBookById(bookId);

		if(book.isCheckedOut) {
			System.out.println("We're sorry... that book is unavailable for checkout.");
		} else {
			bookDao.checkoutBook(userName, bookId);
		}
		Utils.pauseApp();
	}

	private static void processReturnBook() {
		int bookId = Utils.getUserInputInt("Enter the ID of the book to return: ");
		Book book = bookDao.getBookById(bookId);

		if(!book.isCheckedOut) {
			System.out.println("This book has not been checked out yet!!!");
		} else if(!book.getCheckedOutBy().equalsIgnoreCase(userName)) {
			System.out.println("You cannot return this book! It was checked out by someone else!");
		} else {
			bookDao.returnBook(bookId);
		}
		Utils.pauseApp();
	}

	private static void processAdminScreen() {
		String userPassword = Utils.getUserInput("Enter the password: ");
		boolean isPasswordCorrect = Utils.passwordCheck(userPassword);

		if(isPasswordCorrect) {
			AdminLogic.processAdminOptions();
		} else {
			System.err.println("ERROR! Wrong Password!");
		}
	}

}
