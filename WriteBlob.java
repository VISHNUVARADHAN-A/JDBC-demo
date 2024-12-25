import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class WriteBlob {
    
	public static void main(String[] args) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		FileInputStream input = null;
		
		try {
			// 1. Get a connection to database
			            String password = System.getenv("DB_PASSWORD");
            String Let_JDBC_url = System.getenv("DB_URL");
            String Let_JDBC_user = System.getenv("DB_USER");
            myConn = DriverManager.getConnection(Let_JDBC_url, Let_JDBC_user, password);

			// 2. Prepare statement
			String sql = "update employees set resume=? where email='john.doe@foo.com'";
			myStmt = myConn.prepareStatement(sql);
			
			// 3. Set parameter for resume file name
			File theFile = new File("C:\\Users\\vishn\\coding\\java\\jdbc\\JDBC\\sample_resume.pdf");
			input = new FileInputStream(theFile);
			myStmt.setBinaryStream(1, input);
			
			System.out.println("Reading input file: " + theFile.getAbsolutePath());
			
			// 4. Execute statement
			System.out.println("\nStoring resume in database: " + theFile);
			System.out.println(sql);
			
			myStmt.executeUpdate();
			
			System.out.println("\nCompleted successfully!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {			
			if (input != null) {
				input.close();
			}
			
			close(myConn, myStmt);			
		}
	}

	private static void close(Connection myConn, Statement myStmt)
			throws SQLException {

		if (myStmt != null) {
			myStmt.close();
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

    
}
