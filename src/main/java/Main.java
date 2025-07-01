import java.util.ArrayList;

public class Main {

    public static ArrayList<Book> availableBooks;

    public static void main(String[] args) {

		Logic.setDataSource();
		Logic.processHomeScreen();

		//goodbye message for when user exits
		System.out.println("""
				\nGoodbye!
				Thank you for visiting the Neighborhood Library!
				See you soon! :)
				""");
    }
}
