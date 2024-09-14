import java.sql.*;
public class DeleteStatement {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "dummy", "Dknight1!");
            // 2. Create a statement
            myStmt = myConn.createStatement();
            // 3. check before update
            System.out.println("before update");
            myRs = myStmt.executeQuery("select * from employees where first_name='John'and last_name='Doe' ");
            // 4. Process the result set
            // while (myRs.next()) {
            //     //get values for each column
            //     for (int i = 1; i < myRs.getMetaData().getColumnCount() + 1; i++) {
            //         System.out.print(myRs.getObject(i)+ " ");
            //     }
            //     System.out.println();
            // }
            boolean found = false;
			while (myRs.next()) {
				String theLastName = myRs.getString("Last_name");
				String theFirstName = myRs.getString("First_name");
				String email = myRs.getString("email");
			
				System.out.printf("Found employee: %s %s, %s\n", theFirstName, theLastName, email);
				found=true;
			}
            if (!found) {
				System.out.println("Employee NOT FOUND: " + "John" + " " + "Doe");				
			}

            //5. update statement
            System.out.println("after update");
            int rowsAffected=myStmt.executeUpdate(
                "delete from employees "
                +"where last_name='Doe' and first_name='John'"
            );
            //6. result after update
            myRs = myStmt.executeQuery("select * from employees where first_name='John' and last_name='Doe'");
            // while (myRs.next()) {
            //     //get values for each column
            //     for (int i = 1; i < myRs.getMetaData().getColumnCount() + 1; i++) {
            //         System.out.print(myRs.getObject(i)+ " ");
            //     }
            //     System.out.println();
        //}
        boolean found1 = false;
        while (myRs.next()) {
            String theLastName = myRs.getString("John");
            String theFirstName = myRs.getString("Doe");
            String email = myRs.getString("email");
        
            System.out.printf("Found employee: %s %s, %s\n", theFirstName, theLastName, email);
            found1=true;
        }
        if (!found1) {
            System.out.println("Employee NOT FOUND: " + "John" + " " + "Doe");				
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
