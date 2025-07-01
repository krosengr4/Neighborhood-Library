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
							   "WHERE checked_in = 0;";

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

	public List<Book> getCheckedOut() {
		List<Book> bookList = new ArrayList<>();

		String query = "SELECT * FROM books " +
							   "WHERE checked_in = 0;";

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

	private Book mapRow(ResultSet results) throws SQLException {
		int bookId = results.getInt("book_id");
		String ibsn = results.getString("ibsn");
		String title = results.getString("title");
		boolean isCheckedIn = results.getBoolean("checked_in");
		String author = results.getString("author");
		int publishedYear = results.getInt("published_year");

		return new Book(bookId, ibsn, title, isCheckedIn, author, publishedYear);
	}
}

