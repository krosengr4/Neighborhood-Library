import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class Logic {

	static BasicDataSource dataSource = new BasicDataSource();
	static MySqlBookDao bookDao = new MySqlBookDao(dataSource);

	public static void setDataSource() {
		dataSource.setUrl("jdbc:mysql://localhost:3306/neighborhood_library");
		dataSource.setUsername("root");
		dataSource.setPassword(System.getenv("SQL_PASSWORD"));
	}

	public static void processHomeScreen() {
		String userName = Utils.getUserInput("Please Enter Your Name: ").trim();

		boolean ifContinue = true;

		while(ifContinue) {
			int userAction = UserInterface.displayHomeScreen(userName);

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
		List<Book> bookList = bookDao.getAllAvailable();

		if(bookList.isEmpty()) {
			System.out.println("There are no available books at this time...");
		} else {
			for(Book book : bookList) {
				book.print();
				System.out.println("----------------------------------------");
			}
		}
		Utils.pauseApp();
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
