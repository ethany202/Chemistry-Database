package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	private static String url = "jdbc:mysql://qg7obpjdhpx5oil4:zba32s55cu29aiyu@r1bsyfx4gbowdsis.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/ogg20qt38u11pv0g";
	private static String username = "qg7obpjdhpx5oil4";
	private static String password = "zba32s55cu29aiyu";
	private static Connection conn = null;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("getting jdbc connection");
			conn = DriverManager.getConnection(url, username, password); 		// getConnection() is a static method in DriverManager class which creates a Connection object after having a parameter of the location passed.
			System.out.println("done getting connections");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			return conn;
		}
	public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
		}
		catch(SQLException se) {}
		try {
			if(stmt!=null) {
				stmt.close();
			}
		}
		catch(SQLException se) {}
		try {
			if(conn!=null) {
				conn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
