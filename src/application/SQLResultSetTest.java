package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLResultSetTest {
	Connection connection;
	List<String> testList = new ArrayList<>();
	
	
	public SQLResultSetTest() {
		connection = SqlConnection.Connector();
		if(connection == null) {
			System.out.println("Connection to database failed.");		
			System.exit(1);}
	}
	
	public void sqlTest() {
		
		
		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("SELECT first_name FROM account");
			
			while(rs.next()) {
				testList.add(rs.getString("first_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(testList);
		
	}
}
