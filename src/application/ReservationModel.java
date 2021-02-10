package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class ReservationModel {
	
	PreparedStatement preparedStatement;
	Connection connection;
	ResultSet rs;
	
	int accID, mediaID, fkInventoryID;
	public ReservationModel(int accID,int mediaID) {
		this.accID = accID;
		this.mediaID = mediaID;
	}
	
	public ReservationModel() {
		connection = SqlConnection.Connector();
		if (connection == null) {
			System.out.println("Connection failed.");
			System.exit(1);
		} 
	}
		
		
		public boolean executeReservationQuery(int accID, int fkInventoryID) throws SQLException {
			try {
				System.out.println("eRQ accID: " + accID);
				System.out.println("eRQ invID: " + fkInventoryID);
				String query = "INSERT INTO user_reservation(reservation_date, collection_deadline,fk_account_id,fk_inventory_id)" + 
						   "VALUES(CURRENT_DATE,CURRENT_DATE+1,?,?);";
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, accID);
				preparedStatement.setInt(2, fkInventoryID);

				preparedStatement.executeUpdate();
				
//				loanedDate = convertToDate(loanDate);
//				returnedDate = convertToDate(returnDate);

			} catch (SQLException e) {
				System.out.println("eRQ accID: " + accID);
				System.out.println("eRQ invID: " + fkInventoryID);
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		public int getInventoryID(int mediaID) throws SQLException{
			int mID = mediaID;
			System.out.println("mediaID getInventoryItem: "+mID);
			try {
				String query = "SELECT inventory_id from inventory_item WHERE fk_media_id = ?;";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, mID);
				System.out.println(preparedStatement);
				rs = preparedStatement.executeQuery();
				while(rs.next()) {
					fkInventoryID = rs.getInt(1);
				}				
				return fkInventoryID;
				
			} catch(SQLException e) {
				System.out.println(e);
				return 0;
			}
		}
		
		public void removeOldReservations() {
			
			try {
				String query = "DELETE FROM user_reservation WHERE collection_deadline < CURRENT_DATE;";
				preparedStatement = connection.prepareStatement(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public PreparedStatement getPreparedStatement() {
			return preparedStatement;
		}

		public void setPreparedStatement(PreparedStatement preparedStatement) {
			this.preparedStatement = preparedStatement;
		}

		public Connection getConnection() {
			return connection;
		}

		public void setConnection(Connection connection) {
			this.connection = connection;
		}

		public ResultSet getRs() {
			return rs;
		}

		public void setRs(ResultSet rs) {
			this.rs = rs;
		}

		public int getAccID() {
			return accID;
		}

		public void setAccID(int accID) {
			this.accID = accID;
		}

		public int getMediaID() {
			return mediaID;
		}

		public void setMediaID(int mediaID) {
			this.mediaID = mediaID;
		}

		public int getFkInventoryID() {
			return fkInventoryID;
		}

		public void setFkInventoryID(int fkInventoryID) {
			this.fkInventoryID = fkInventoryID;
		}
		
		
}
