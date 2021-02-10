package application;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegistrationModel {

	Connection connection;
	
	PreparedStatement preparedStatement;
	
	public String roleName;
	
	ResultSet rs;
	
	List<String> rList = new ArrayList<>();
	ObservableList<String> roleList = FXCollections.observableArrayList();

	
	public RegistrationModel() {
		
		connection = SqlConnection.Connector();
		if (connection == null) {
			System.out.println("Connection failed.");
			System.exit(1);
		} 
		
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean registration(String first_name, String last_name, 
								String phone_number, String email, 
								int role, String password) throws SQLException {	
		

	try {
		String sql = "INSERT INTO account (first_name, last_name, phone_number, email, fk_role_id, current_loans, password)" + 
				" VALUES (?, ?, ?, ?, ?, 0, ?);";

		
		preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, first_name);
		preparedStatement.setString(2, last_name);
		preparedStatement.setString(3, phone_number);
		preparedStatement.setString(4, email);
		preparedStatement.setInt(5, role);
		preparedStatement.setString(6, password);

		return true;
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
		
	} 
	
	
	
	}
	
	public boolean insertNewAccount() {
		try {
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		
		}
	}
	
	public List<String> accountRoleToList() {
		
		
		
	    try {
			
			String sql = "SELECT first_name FROM account";
			PreparedStatement statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			
			
			
			while(rs.next()) {
				
				roleName = rs.getString("role_name");
			    roleList.add(roleName);
			    
			}
			    
				return roleList;
			    		
			
		} catch (SQLException e) {
			e.printStackTrace();
		return null;
		}
	}

	

	
}




