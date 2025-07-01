import javax.sql.DataSource;

public class MySqlBookDao {

	private final DataSource dataSource;

	public MySqlBookDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
