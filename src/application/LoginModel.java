package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
	
	public int accountID;
	
	public String firstName, lastName, email;
	
	public LoginModel(int accountID) {
		this.accountID = accountID;
	}
	
	Connection connection;
	public LoginModel() {
		connection = SqlConnection.Connector();
		if(connection == null) {
			System.out.println("Connection to database failed.");		
			System.exit(1);}
	}
		
	
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean isLogin(String user, String pass) throws SQLException {		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM account WHERE email = ? and password = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	public int getLoggedInUserRole(String user) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int roleID = 0;
		String query = "SELECT fk_role_id FROM account WHERE email = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				roleID = resultSet.getInt(1);
			}
			return roleID;
			
		} catch(Exception e) {
			return 0;
		}
	}
	
	public int getLoggedInUser(String user) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT account_id FROM account WHERE email = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				accountID = resultSet.getInt(1);
			}
			return accountID;
			
		} catch(Exception e) {
			return 0;
		}
	}
	
	
	public String getUserFullName(int accountID) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT first_name, last_name FROM account WHERE account_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, accountID);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				firstName = resultSet.getString(1);
				lastName = resultSet.getString(2);
			}
			return firstName + " " + lastName;
			
		} catch(Exception e) {
			return "null";
		}
	}
	
	public String getUserEmail(int accountID) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT email FROM account WHERE account_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, accountID);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				email = resultSet.getString(1);
			}
			return email;
			
		} catch(Exception e) {
			return "null";
		}
	}


	public int getAccountID() {
		return accountID;
	}



	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	
}
