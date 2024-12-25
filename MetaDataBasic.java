import java.sql.*;

public class MetaDataBasic {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			// 1. Get a connection to database
			            String password = System.getenv("DB_PASSWORD");
            String Let_JDBC_url = System.getenv("DB_URL");
            String Let_JDBC_user = System.getenv("DB_USER");
            myConn = DriverManager.getConnection(Let_JDBC_url, Let_JDBC_user, password);

			// 2. get metadata
			DatabaseMetaData databaseMetaData = myConn.getMetaData();
			// 3. Display infor about database
			System.out.println("product Name " + databaseMetaData.getDatabaseProductName());
			System.out.println("Product version " + databaseMetaData.getDatabaseProductVersion());
			System.out.println();

			// 4. know about JDBC
			System.out.println("JDBC Drivername " + databaseMetaData.getDriverName());
			System.out.println("JDBC Driverversion " + databaseMetaData.getDriverVersion());

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}
	}

}
