import java.sql.*;
public class StoreedProcedureStatement {

    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "dummy", "Dknight1!");
            // 2. initiate depatement and amount
            String theDepartment ="Engineering";
            int theincreaseAmount =10000;
            // 3. Create a statement
            System.out.println("before executing stored procedure");
            myStmt = myConn.prepareStatement(("select * from employees where department='"+theDepartment+"'" ));  
            myRs = myStmt.executeQuery();
           		
			// 4. Process the result set
            for (int i = 0; i < myRs.getMetaData().getColumnCount(); i++) {
                System.out.print(myRs.getMetaData().getColumnName(i + 1)+" ");
            }
            System.out.println();
            while (myRs.next()) {
                //get values for each column
                for (int i = 1; i < myRs.getMetaData().getColumnCount() + 1; i++) {
                    System.out.print(myRs.getObject(i)+ " ");
                }
                System.out.println();
            }
            // 5. call stored procedure
            myStmt=myConn.prepareCall("{call increase_salaries_for_department(?,?)}");
            myStmt.setString(1,theDepartment);
            myStmt.setDouble(2,theincreaseAmount);
            System.out.println("executing stored procedure");
            myStmt.execute();
            
            // 6. after calling stored procedure
            System.out.println("after executing stored procedure");
            myStmt = myConn.prepareStatement(("select * from employees where department='"+theDepartment+"'" ));  
            myRs = myStmt.executeQuery();
           		
			// 4. Process the result set
            for (int i = 0; i < myRs.getMetaData().getColumnCount(); i++) {
                System.out.print(myRs.getMetaData().getColumnName(i + 1)+" ");
            }
            System.out.println();
            while (myRs.next()) {
                //get values for each column
                for (int i = 1; i < myRs.getMetaData().getColumnCount() + 1; i++) {
                    System.out.print(myRs.getObject(i)+ " ");
                }
                System.out.println();

            }
        }
        catch (Exception exc) {
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
    
