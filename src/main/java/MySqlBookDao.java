import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlBookDao {

	private final DataSource dataSource;

	public MySqlBookDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Book> getAvailable() {
		List<Book> booksList = new ArrayList<>();

		String query = "SELECT * FROM books " +
							   "WHERE checked_out = 0;";

		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();

			while(results.next()) {
				Book book = mapRow(results);
				booksList.add(book);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return booksList;
	}

	public Book getBookById(int bookId) {
		String query = "SELECT * FROM books " +
							   "WHERE book_id = ?;";

		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, bookId);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return mapRow(result);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public List<Book> getCheckedOut() {
		List<Book> bookList = new ArrayList<>();

		String query = "SELECT * FROM books " +
							   "WHERE checked_out = 1;";

		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();

			while(results.next()) {
				Book book = mapRow(results);
				bookList.add(book);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return bookList;
	}

	public List<Book> searchByAuthor(String authorName) {
		List<Book> bookList = new ArrayList<>();

		String query = "SELECT * FROM books " +
							   "WHERE author LIKE ?;";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + authorName + "%");

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Book book = mapRow(results);
				bookList.add(book);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return bookList;
	}

	public Book searchByTitle(String bookTitle) {
		String query = "SELECT * FROM books " +
							   "WHERE title LIKE ?;";

		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + bookTitle + "%");

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return mapRow(result);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public void checkoutBook(String userName, int bookId) {
		String query = "UPDATE books " +
							   "SET checked_out = 1, check_out_by = ? " +
							   "WHERE book_id = ?;";

		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			statement.setInt(2, bookId);

			int rows = statement.executeUpdate();
			if(rows > 0) {
				Book book = getBookById(bookId);
				System.out.println("Success! You have checked out " + book.getTitle());
			} else {
				System.err.println("Error! Book could not be checked out!");
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Book mapRow(ResultSet results) throws SQLException {
		int bookId = results.getInt("book_id");
		String ibsn = results.getString("ibsn");
		String title = results.getString("title");
		boolean isCheckedIn = results.getBoolean("checked_out");
		String author = results.getString("author");
		int publishedYear = results.getInt("published_year");

		return new Book(bookId, ibsn, title, isCheckedIn, author, publishedYear);
	}
}

