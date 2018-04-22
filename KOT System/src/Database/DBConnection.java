package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DBConnection {

	public static Connection getConnection() throws SQLException {

		Connection conn = null;
		try {

			return conn = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/" + "kot_system" + "?useSSL=true", "kumaran", "toor");

		}

		catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}

}