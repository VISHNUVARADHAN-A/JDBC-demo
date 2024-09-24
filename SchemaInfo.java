import java.sql.*;

public class SchemaInfo {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String catalog = null;
		String schemaPattern =null;
		String tableNamePattern=null;
		String CoulumnNamePattern = null;
		String[] types = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "dummy", "Dknight1!");

			// 2. get metadata
			DatabaseMetaData databaseMetaData = myConn.getMetaData();

			// 3. Get list of tables
			System.out.println("List of Tables");
			System.out.println("--------------");

			myRs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern,	types);

			while (myRs.next()) {
				System.out.printf(myRs.getString("TABLE_NAME"));
				System.out.println();
			}
			System.out.println("Column name =====================");
			myRs = databaseMetaData.getColumns(catalog, schemaPattern, "employees", CoulumnNamePattern);
			while(myRs.next()){
				System.out.println(myRs.getString("COLUMN_NAME"));

			}
			System.out.println("Column name");


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
