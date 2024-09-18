import java.sql.*;
import java.util.Scanner;
public class Transactions {

    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "dummy", "Dknight1!");

            // 2. turn off auto commit
            myConn.setAutoCommit(false);

            // 3. show data before updating
            System.out.println("before updating");
            myStmt = myConn.createStatement();
                       
            displayunit(myRs, myStmt);
            
            myStmt.executeUpdate("delete from employees where department ='HR'");
            
            myStmt.executeUpdate("update employees set salary =300000 where department='Engineering'" );

            System.out.println("transactions step executed");
        displayunit(myRs, myStmt);
        Scanner scanner = new Scanner(System.in);

		System.out.println("Is it okay to save?  yes/no: ");
		String input = scanner.nextLine();

		scanner.close();
        boolean ok = input.equalsIgnoreCase("yes");
            if (ok){
                myConn.commit();
                System.out.println("transaction commited");
                }
                else{
                    myConn.rollback();
                    System.out.println("transcation roll back");
                }
                displayunit(myRs, myStmt);
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
        public static void displayunit(ResultSet myRs, Statement myStmt) throws SQLException {
            myRs = myStmt.executeQuery("select * from employees where department='HR' or department='Engineering'");
            
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
    }
    
