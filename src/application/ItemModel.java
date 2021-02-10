package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemModel {

	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet rs;
	Statement statement;
	
	public ItemModel() {
		
		connection = SqlConnection.Connector();
		if (connection == null) {
			System.out.println("Connection failed.");
			System.exit(1);
		} 
		
	}
	
	public void addItem(String barcode) throws SQLException {
		
	
		int mediaID = getMediaId();
		
		
		String sqlItem		= "INSERT INTO inventory_item(barcode,fk_media_id)"
							+ "VALUES(?,?);";
		preparedStatement = connection.prepareStatement(sqlItem);
		
		preparedStatement.setString(1, barcode);
		preparedStatement.setInt(2, mediaID);
		preparedStatement.executeUpdate();
		
			
	}
	
	public int getMediaId() throws SQLException {
		String query = "SELECT MAX(media_id) as media_id FROM media;";
		
		preparedStatement = connection.prepareStatement(query);
		rs = preparedStatement.executeQuery();
		
		String mediaID = null;
		while(rs.next()) {
			mediaID = rs.getString(1);
		}
		if(!(mediaID==null)) {
		int mID = Integer.parseInt(mediaID);
		return mID;
		} else {
			return 0;
		}
	}
	
	public boolean deleteItem(int mediaID, String barcode) {
		try {
			String sql		= "DELETE FROM inventory_item WHERE fk_media_id = ? AND barcode = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, mediaID);
			preparedStatement.setString(2, barcode);
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkInventoryItem(int fkMediaID) throws SQLException {
		int invItem = 0;
		
		try {
			String query = "SELECT * FROM inventory_item WHERE fk_media_id = ?;";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, fkMediaID);
			rs = preparedStatement.executeQuery();
			
//		String mediaID = null;
			while(rs.next()) {
				invItem = rs.getInt(1);
			}
			
			if(invItem==0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	
	
	
}
