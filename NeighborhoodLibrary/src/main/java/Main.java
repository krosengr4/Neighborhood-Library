import java.util.ArrayList;

public class Main {

    public static ArrayList<Book> availableBooks;

    public static void main(String[] args) {

        availableBooks = loadBookList();

        Utils.designLine(70, false);
        System.out.println("\t\tWELCOME TO THE NEIGHBORHOOD LIBRARY");
        Utils.designLine(70, true);
        String userName = Utils.getUserInput("What is your name?: ");

        System.out.println("\n\t-HOME SCREEN-\n\tWelcome " + userName + "!");
        Utils.designLine(25, true);

        boolean ifContinue = true;

        while (ifContinue) {
            System.out.println("\nOPTIONS:\n1 - Show Available Books\n2 - Show Checked Out Books\n3 - Exit");
            int userAction = Integer.parseInt(Utils.getUserInput("Enter a number: ").trim());

            switch (userAction) {
                case 1 -> showAvailableBooks(userName, availableBooks);
                case 2 -> showCheckedOutBooks();
                case 3 -> {
                    System.out.println("\n\tThank You for using the Neighborhood Library. Goodbye!");
                    ifContinue = false;
                }
                default -> System.err.println("ERROR! Please Enter a number 1 - 3!");
            }
        }
    }

    public static ArrayList<Book> loadBookList() {
        ArrayList<Book> availableBooks = new ArrayList<>();

        availableBooks.add(new Book(1, "978-0-123456-00-1", "To Kill a Mockingbird", false, ""));
        availableBooks.add(new Book(2, "978-0-123456-01-3", "1984", false, ""));
        availableBooks.add(new Book(3, "978-0-123456-02-3", "Pride and Prejudice", false, ""));
        availableBooks.add(new Book(4, "978-0-123456-03-6", "The Great Gatsby", false, ""));
        availableBooks.add(new Book(5, "978-0-123456-04-3", "The Catcher in the Rye", false, ""));
        availableBooks.add(new Book(6, "978-0-123456-02-5", "The Hobbit", false, ""));
        availableBooks.add(new Book(7, "978-0-123456-07-4", "Moby-Dick", false, ""));
        availableBooks.add(new Book(8, "978-0-123456-01-7", "The Lord of the Rings", false, ""));
        availableBooks.add(new Book(9, "978-0-123456-08-9", "Harry Potter and the Sorcerer's Stone", false, ""));
        availableBooks.add(new Book(10, "978-0-123456-03-3", "The Da Vinci Code", false, ""));
        availableBooks.add(new Book(11, "978-0-123456-04-8", "The Alchemist", false, ""));
        availableBooks.add(new Book(12, "978-0-123456-06-3", "Brave New World", false, ""));
        availableBooks.add(new Book(13, "978-0-123456-02-7", "The Book Thief", false, ""));
        availableBooks.add(new Book(14, "978-0-123456-01-4", "The Road", false, ""));
        availableBooks.add(new Book(15, "978-0-123456-03-3", "A Game of Thrones", false, ""));
        availableBooks.add(new Book(16, "978-0-123456-07-5", "The Kite Runner", false, ""));
        availableBooks.add(new Book(17, "978-0-123456-07-2", "Life of Pi", false, ""));
        availableBooks.add(new Book(18, "978-0-123456-09-6", "The Hunger Games", false, ""));
        availableBooks.add(new Book(19, "978-0-123456-03-4", "The Girl with the Dragon Tattoo", false, ""));
        availableBooks.add(new Book(20, "978-0-123456-04-6", "Where the Crawdads Sing", false, ""));

        return availableBooks;
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
