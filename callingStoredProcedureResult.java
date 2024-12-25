import java.sql.*;
public class callingStoredProcedureResult {

    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        CallableStatement myStmt = null;
        ResultSet myRs = null;
        try {
            // 1. Get a connection to database
            String password = System.getenv("DB_PASSWORD");
            String Let_JDBC_url = System.getenv("DB_URL");
            String Let_JDBC_user = System.getenv("DB_USER");
            myConn = DriverManager.getConnection(Let_JDBC_url, Let_JDBC_user, password);
            // 2. initiate depatement and amount
            String theDepartment ="Engineering";
            
            // 3. Create a statement
            System.out.println("before executing stored procedure");
            myStmt = myConn.prepareCall("{call get_employees_for_department(?)}");

            myStmt.setString(1, theDepartment);
            

            
            System.out.println("calling stored procedure('"+theDepartment+"')");

            myStmt.execute();
            
            System.out.println("end");
            myRs =myStmt.getResultSet();
            while (myRs.next()) {
                String lastName = myRs.getString("last_name");
                String firstName = myRs.getString("first_name");
                double salary = myRs.getDouble("salary");
                String department = myRs.getString("department");
    
                System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);

        }}
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
    
