import java.sql.*;

public class ResultSet {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
				
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "dummy", "Dknight1!");
			//2 . Run query
			myStmt=myConn.createStatement();
			myRs= myStmt.executeQuery("select id , last_name, first_name, salary from employees");

			// 3.. get result set 
				ResultSetMetaData rsMetaData =myRs.getMetaData();

			//4. display info
			int columnCount = rsMetaData.getColumnCount();
			System.out.println("Column count: " + columnCount + "\n");
			
			for (int column=1; column <= columnCount; column++) {
				System.out.println("Column name: " + rsMetaData.getColumnName(column));
				System.out.println("Column type name: " + rsMetaData.getColumnTypeName(column));
				System.out.println("Is Nullable: " + rsMetaData.isNullable(column));
				System.out.println("Is Auto Increment: " + rsMetaData.isAutoIncrement(column) + "\n");
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