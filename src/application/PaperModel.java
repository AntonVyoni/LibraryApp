package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PaperModel extends MediaTitleModel{

	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet rs;
	
	String title, number, country, subject, publisher, description;
	int paperID, publisherID, mediaID, rentalDuration;
	
	public PaperModel() {
		
		connection = SqlConnection.Connector();
		if (connection == null) {
			System.out.println("Connection failed.");
			System.exit(1);
		} 
	}
	
	PaperModel(String title, String number, String country, String subject, String publisher, String description, int rentalDuration,
			int publisherID, int mediaID, int paperID) {
		this.title = title;
		this.number = number;
		this.country = country;
		this.subject = subject;
		this.publisher = publisher;
		this.description = description;
		this.rentalDuration = rentalDuration;
		this.publisherID = publisherID;
		this.mediaID = mediaID;
		this.paperID = paperID;
	}

	

	
	public boolean addPaperAndPublisher(String subject, String number,String publisherName)  throws SQLException{

		try {
				String sql = "INSERT INTO paper(subject, number) "
						   + "VALUES(?,?);"
						   + "INSERT INTO publisher(name) "
						   + "VALUES(?);";

				preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setString(1, subject);
				preparedStatement.setString(2, number);
				preparedStatement.setString(3, publisherName);

				preparedStatement.executeUpdate();

				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	@Override
	public boolean addTitle(String title, String desc, String country, int rentalDuration, String year, String month, String day) 
			throws SQLException, ParseException {


				try {

					String releaseDate = dateMaker(year, month, day);
					int publisherID = getPublisherID();
					int paperID = getPaperID();
					Date madeDate = convertToDate(releaseDate);
					String sqlFirst = ( "INSERT INTO media(title, country, description,rental_duration,release_date,fk_paper_id, fk_publisher_id)" 
									+ "VALUES (?,?,?,?,?,?,?);");

					preparedStatement = connection.prepareStatement(sqlFirst);



					preparedStatement.setString(1, title);
					preparedStatement.setString(2, country);
					preparedStatement.setString(3, desc);
					preparedStatement.setInt(4, rentalDuration);
					preparedStatement.setDate(5, madeDate);
					preparedStatement.setInt(6, paperID);
					preparedStatement.setInt(7, publisherID);
	
					preparedStatement.executeUpdate();

					return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}				
	}
	
	public int getPaperID() throws SQLException {
		String query = "SELECT MAX(paper_id) FROM paper";
		
		preparedStatement = connection.prepareStatement(query);
		
//		preparedStatement.setString(1, subject);

		rs = preparedStatement.executeQuery();
		
		String bookID = null;
		while(rs.next()) {
			bookID = rs.getString(1);
		}
		if(!(bookID==null)) {
		int bID = Integer.parseInt(bookID);
		return bID;
		} else {
			return 0;
		}
	}
	
	
	public int getPublisherID() throws SQLException {
		String query = "SELECT MAX(publisher_id) FROM publisher;";
		
		
		preparedStatement = connection.prepareStatement(query);
		
//		preparedStatement.setString(1, firstName);
//		preparedStatement.setString(2, lastName);

		rs = preparedStatement.executeQuery();
		
		String authorID = null;
		while(rs.next()) {
			authorID = rs.getString(1);
		}
		if(!(authorID==null)) {
		int aID = Integer.parseInt(authorID);
		return aID;
		} else {
			return 0;
		}
	}


	@Override
	void removeTitle() {
		// TODO Auto-generated method stub
		
	}
	
	public void removeTitle(int mediaID, int paperID) {
		
		try {
			String sql = "DELETE FROM inventory_item WHERE fk_media_id = ?; " + 
						"DELETE FROM media WHERE fk_paper_id = ?;\r\n" +
							"DELETE FROM paper WHERE paper_id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, mediaID);
			preparedStatement.setInt(2, paperID);
			preparedStatement.setInt(3, paperID);

			preparedStatement.executeUpdate();



		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

//	@Override
//	void editTitle() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	void getMediaInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean editTitle() throws SQLException, ParseException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean editTitle(String title, String desc, String country, String year, String month,
			String day, int paperID, int publisherID, int mediaID) throws SQLException, ParseException {

//		this.paperID = paperID;
//		this.publisherID = publisherID;
//		this.mediaID = mediaID;
		
		try {

			String releaseDate = dateMaker(year, month, day);
//			int authorID = getAuthorID();
//			int bookID = getBookID();
			Date madeDate = convertToDate(releaseDate);
			String sqlFirst = ("UPDATE media " + 
								"SET title = ?, " + 
								"country = ?, " + 
								"description = ?, " + 
								"rental_duration = ?, " + 
								"release_date = ?, " + 
								"fk_paper_id = ?, " + 
								"fk_publisher_id = ? " + 
								"WHERE media_id = ?;");

			preparedStatement = connection.prepareStatement(sqlFirst);

			preparedStatement.setString(1, title);
			preparedStatement.setString(2, country);
			preparedStatement.setString(3, desc);
			preparedStatement.setInt(4, 0);
			preparedStatement.setDate(5, madeDate);
			preparedStatement.setInt(6, paperID);
			preparedStatement.setInt(7, publisherID);
			preparedStatement.setInt(8, mediaID);

			preparedStatement.executeUpdate();
			
			System.out.println("publisherID från editTitle: "+publisherID);

			return true;
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean editPaperAndPublisher(String subject, String number, int paperID, String publisherName, int publisherID) throws SQLException {
		

//		this.paperID = paperID;
//		this.publisherID = publisherID;

		try {
			String sql = "UPDATE paper " + 
					"SET subject = ?, " + 
					"number = ? " + 
					"WHERE paper_id = ?; " + 
					"UPDATE publisher " + 
					"SET name = ? " + 
					"WHERE publisher_id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, subject);
			preparedStatement.setString(2, number);
			preparedStatement.setInt(3, paperID);
			preparedStatement.setString(4, publisherName);
			preparedStatement.setInt(5, publisherID);


			preparedStatement.executeUpdate();
			
			System.out.println("publisherID från editPaperAndPublisher: "+publisherID);

			return true;

		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMediaID() {
		return mediaID;
	}

	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}

	public void setPaperID(int paperID) {
		this.paperID = paperID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}
	
	
	public int getPaperIDs() {
		return paperID;
	}
	
	public int getPublisherIDs() {
		return publisherID;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	
	

}
