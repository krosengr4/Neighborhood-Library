import java.util.ArrayList;

public class Main {

    public static ArrayList<Book> availableBooks;

    public static void main(String[] args) {

		Logic.processHomeScreen();

		//goodbye message for when user exits
		System.out.println("""
				\nGoodbye!
				Thank you for visiting the Neighborhood Library!
				See you soon! :)
				""");
    }

    public static void showAvailableBooks(String userName, ArrayList<Book> availableBooks) {
        System.out.println("\t\tAVAILABLE BOOKS");
        Utils.designLine(40, false);

        for (Book book : availableBooks) {
            System.out.printf("ID: %d | Title: %s |IBSN: %s\n", book.getId(), book.getTitle(), book.getIsbn());
        }

        String userCheckOut = Utils.getUserInput("\nWould you like to check out a book? (Y or N): ").trim();

        if (userCheckOut.equalsIgnoreCase("y")) {
            bookCheckOut(availableBooks, userName);
        }
    }

    public static void showCheckedOutBooks() {
        System.out.println("\t\tCHECKED OUT BOOKS");
        Utils.designLine(40, false);

        ArrayList<Book> checkedOutBooks = Utils.checkedOutBooks;
        if (checkedOutBooks.isEmpty()) {
            System.out.println("There aren't any books that are checked out.");
        } else {
            for (Book book : checkedOutBooks) {
                System.out.printf("ID: %d | Title: %s | IBSN: %s | Checked Out By: %s\n",
                        book.getId(), book.getTitle(), book.getIsbn(), book.getCheckedOutTo());
            }
            String userCheckIn = Utils.getUserInput("\nWould you like to check a book back in? (Y or N): ").trim();

            if (userCheckIn.equalsIgnoreCase("y")) {
                bookCheckIn(checkedOutBooks);
            }
        }

    }

    public static void bookCheckOut(ArrayList<Book> availableBooks, String userName) {

        String userBookChoice = Utils.getUserInput("Enter the ID of the book you want to checkout: ").trim();
        int userBookID = Integer.parseInt(userBookChoice);

        boolean isBookAvailable = false;

        for (Book book : availableBooks) {
            if (userBookID == book.getId()) {
                book.checkOut(userName);
                Utils.checkedOutBooks.add(book);
                availableBooks.remove(book);

                System.out.println("Success! You have checked out \"" + book.getTitle() + "\"");
                isBookAvailable = true;
                break;
            }
        }
        if (!isBookAvailable) {
            System.out.println("Sorry... We could not find a book with that ID.");
        }
        Utils.pauseApp();
    }

    public static void bookCheckIn(ArrayList<Book> checkedOutBooks) {

        int checkInID = Integer.parseInt(Utils.getUserInput("Enter the ID of the book you want to check in: "));
        boolean isAvailable = false;

        for (Book book : checkedOutBooks) {
            if (checkInID == book.getId()) {
                book.checkIn();
                Utils.checkedOutBooks.remove(book);
                availableBooks.add(book);

                System.out.println("Success! Thank you for checking in \"" + book.getTitle() + "\"");
                isAvailable = true;
                break;
            }
        }
        if(!isAvailable) {
            System.out.println("There is no book with that ID being checked out.");
        }
        Utils.pauseApp();
    }
}
