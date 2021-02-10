package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class MediaTitleModel {
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet rs;
	
	public MediaTitleModel() {
		
		connection = SqlConnection.Connector();
		if (connection == null) {
			System.out.println("Connection failed.");
			System.exit(1);
		} 
		
	}

	
	abstract boolean addTitle(String title, String desc, String country, int rentalDuration, String year, String month, String day) throws SQLException, ParseException;
	
	abstract void removeTitle();
	
	abstract boolean editTitle() throws SQLException, ParseException;
	
	abstract void getMediaInfo();
	
	

	public String dateMaker(String year, String month, String day) {
		String date = (year+"-"+month+"-"+day);
		return date;
	}
	
	public Date convertToDate(String stringWithDate) throws ParseException {
		java.sql.Date dDate =  new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(stringWithDate).getTime());
		return dDate;
	}

	public int getMaxAuthorID() throws SQLException {
		String query = "SELECT MAX(author_id) FROM author_or_director;";
		
		
		preparedStatement = connection.prepareStatement(query);
		
//		preparedStatement.setString(1, firstName);
//		preparedStatement.setString(2, lastName);

		rs = preparedStatement.executeQuery();
		
		String authorMaxID = null;
		while(rs.next()) {
			authorMaxID = rs.getString(1);
		}
		
		if(!(authorMaxID==null)) {
			
		int aID = Integer.parseInt(authorMaxID);
		System.out.println(authorMaxID);
		return aID;
		} else  {
			System.out.println(authorMaxID);
			return 0;
		}
	}
}
