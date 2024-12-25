import java.sql.*;

public class UpdateStatement {
    @SuppressWarnings("unused")
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
            // 2. Create a statement
            myStmt = myConn.createStatement();
            // 3. check before update
            System.out.println("before update");
            myRs = myStmt.executeQuery("select * from employees where last_name='Doe' and first_name='John'");
            // 4. Process the result set
            while (myRs.next()) {
                //get values for each column
                for (int i = 1; i < myRs.getMetaData().getColumnCount() + 1; i++) {
                    System.out.print(myRs.getObject(i)+ " ");
                }
                System.out.println();
            }
            //5. update statement
            System.out.println("after update");
            int rowsAffected=myStmt.executeUpdate(
                "update employees set email='john123@gmail.com' "
                +"where last_name='Doe' and first_name='John'"
            );
            //6. result after update
            myRs = myStmt.executeQuery("select * from employees where last_name='Doe' and first_name='John'");
            while (myRs.next()) {
                //get values for each column
                for (int i = 1; i < myRs.getMetaData().getColumnCount() + 1; i++) {
                    System.out.print(myRs.getObject(i)+ " ");
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
