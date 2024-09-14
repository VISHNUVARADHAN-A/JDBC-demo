import java.sql.*;

public class PreparedInsertStatement {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "dummy", "Dknight1!");
            // 2. Create a prepared statement
            myStmt = myConn.prepareStatement("insert into employees (last_name, first_name, email, department,salary)"
                +"values (?,?,?,?,?)" );
            
            // 3. pass value  to prepared insert 
            myStmt.setString(1,"selvam");
            myStmt.setString(2, "sundhar");
            myStmt.setString(3, "sundhar@gmail.com");
            myStmt.setString(4, "Engineer");
            myStmt.setDouble(5, 100000.00);
            // 4. execute Insert statement
            int rowsAffected = myStmt.executeUpdate();
            // 5. Execute select query
            myRs = myStmt.executeQuery("select * from employees");
            // 6. Process the result set
            while (myRs.next()) {
            // get values for each column
                for (int i = 1; i < myRs.getMetaData().getColumnCount() + 1; i++) {
                    System.out.print(myRs.getObject(i) + " ");
                }
                System.out.println();
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
