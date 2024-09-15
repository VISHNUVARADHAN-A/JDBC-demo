import java.sql.*;
public class callingStoredProcedureInOut {

    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        CallableStatement myStmt = null;
        ResultSet myRs = null;
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "dummy", "Dknight1!");
            // 2. initiate depatement and amount
            String theDepartment ="Engineering";
            
            // 3. Create a statement
            System.out.println("before executing stored procedure");
            myStmt = myConn.prepareCall("{call greet_the_department(?)}");


            myStmt.registerOutParameter(1,Types.VARCHAR);
            myStmt.setString(1, theDepartment);

            
            System.out.println("calling stored procedure"+theDepartment);

            myStmt.execute();
            
            // 6. after calling stored procedure
            String theResult =myStmt.getString(1);
            System.out.println("\n the Result "+theResult);

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
    
