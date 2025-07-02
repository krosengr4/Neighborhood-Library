public class Book implements MySqlDao {

	//declare variables
	int id;
	String isbn;
	String title;
	boolean isCheckedOut;
	String author;
	int publishedYear;

	//Constructor for Book
	public Book(int id, String isbn, String title, boolean isCheckedOut, String author, int publishedYear) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.isCheckedOut = isCheckedOut;
		this.author = author;
		this.publishedYear = publishedYear;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}
	//endregion

	public void checkOut(String name) {
		if(!this.isCheckedOut()) {
			setCheckedOut(true);
		} else {
			System.err.println("This book is already checked out!");
		}
	}

	public void checkIn() {
		if(isCheckedOut()) {
			setCheckedOut(false);
		} else {
			System.err.println("This book hasn't been checked out!");
		}
	}

	public void print() {
		System.out.println("---BOOK---");
		System.out.println("Book ID: " + this.id);
		System.out.println("Title: " + this.title);
		System.out.println("Author: " + this.author);
		System.out.println("Year Published: " + this.publishedYear);
		if(!this.isCheckedOut) {
			System.out.println("Available!");
		} else {
			System.out.println("This book is currently checked out.");
		}

	}
}
