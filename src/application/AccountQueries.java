//package application;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AccountQueries {
//	
//	Connection connection;
//	
//	PreparedStatement accountRegistrationEmailControl = null;
//	
//	public AccountQueries() {
//		
//		connection = SqlConnection.Connector();
//		
//		public int accountControl(String email) {
//			
//			//Ordna så att registrerad email inte får vara samma som finns i databasen
//			List emailList = new ArrayList();
//			
//			
//			String query = "SELECT email FROM account WHERE email='" + email + "'";
//			
//			try {
//				accountRegistrationEmailControl = connection.prepareStatement(query);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//			try (ResultSet resultSet = accountRegistrationEmailControl.executeQuery()) {
//				
//				
//				
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			
//		}
//		
//	}
//	
//
//
//}
