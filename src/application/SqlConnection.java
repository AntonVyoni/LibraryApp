package application;


import java.sql.*;

public class SqlConnection {

	public static Connection Connector() {
		
		try {
			Class.forName("org.postgresql.Driver");
			//De två sista stringsen är username och password
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java4library", "POSTGREUSER", "PASSWORD");
			return conn;
			
		} catch (Exception e) {
			return null;
		}
	}
}