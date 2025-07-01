import org.apache.commons.dbcp2.BasicDataSource;

public class Logic {

	static BasicDataSource dataSource = new BasicDataSource();
	static MySqlBookDao bookDao = new MySqlBookDao(dataSource);

	public static void setDataSource() {
		dataSource.setUrl("jdbc:mysql://localhost:3306/neighborhood_library");
		dataSource.setUsername("root");
		dataSource.setPassword(System.getenv("SQL_PASSWORD"));
	}

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
