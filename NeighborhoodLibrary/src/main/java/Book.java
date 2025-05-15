public class Book {

    //declare variables
    int id;
    String isbn;
    String title;
    boolean isCheckedOut;
    String checkedOutTo;

    //Constructor for Book
    public Book(int id, String isbn, String title, boolean isCheckedOut, String checkedOutTo){
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = isCheckedOut;
        this.checkedOutTo = checkedOutTo;
    }

    //region Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }
    public void setCheckedOut(boolean checkedOut) {
        this.isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }
    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }
    //endregion

    public void checkOut(String name) {
        if (!this.isCheckedOut()) {
            setCheckedOut(true);
            setCheckedOutTo(name);
        } else {
            System.err.println("This book is already checked out!");
        }
    }

    public void checkIn() {
        if(isCheckedOut()) {
            setCheckedOut(false);
            setCheckedOutTo("");
        } else {
            System.err.println("This book hasn't been checked out!");
        }
    }
}
