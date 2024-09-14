
import java.sql.*;

public class PreparedSelectStatement {
        public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "dummy", "Dknight1!");
            // 2. Create a statement
            myStmt = myConn.prepareStatement(("select * from employees where salary>? and department=?"));  
            // 3. pass valuse to perpare Statement
            myStmt.setDouble(1,80000);
            myStmt.setString(2,"HR");
            // 4. Execute SQL query
			myRs = myStmt.executeQuery();
            		
			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}

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
