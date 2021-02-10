package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DVDModel extends MediaTitleModel{

	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet rs;
	
	int mediaID, authorID, dvdID, rentalDuration, ageRestriction;
	
	String title, directorFirstName, directorLastName, country, genre, description, directorFullName;
	
	public DVDModel(String title, String directorFirstName, String directorLastName, String country, 
					String genre, String description, String directorFullName, int rentalDuration, int ageRestriction, int mediaID,
					int authorID, int dvdID) {
		this.title = title;
		this.directorFirstName = directorFirstName;
		this.directorLastName = directorLastName;
		this.country = country;
		this.genre = genre;
		this.description = description;
		this.rentalDuration = rentalDuration;
		this.directorFullName = directorFullName;
		this.ageRestriction = ageRestriction;
		this.mediaID = mediaID;
		this.authorID = authorID;
		this.dvdID = dvdID;
	}


	
public DVDModel() {
		
		connection = SqlConnection.Connector();
		if (connection == null) {
			System.out.println("Connection failed.");
			System.exit(1);
		} 
	}
	
	public String dateMaker(String year, String month, String day) {
		String date = (year+"-"+month+"-"+day);
		return date;
	}
	
	public boolean addDVDAndDirector(String genre, int ageLimit, int loanTime,
			String firstName, String lastName)  throws SQLException{

		try {
				String sql = "INSERT INTO DVD(genre, åldersgräns, lånetid) "
						   + "VALUES(?,?,?);"
						   + "INSERT INTO author_or_director(first_name,last_name) "
						   + "VALUES(?,?);";

				preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setString(1, genre);
				preparedStatement.setInt(2, ageLimit);
				preparedStatement.setInt(3, loanTime);
				preparedStatement.setString(4, firstName);
				preparedStatement.setString(5, lastName);

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
				int authorID = getDirectorID();
				int bookID = getDVDID();
				Date madeDate = convertToDate(releaseDate);
				String sqlFirst = ( "INSERT INTO media(title, country, description,rental_duration,release_date,fk_dvd_id, fk_author_or_director_id)" 
								+ "VALUES (?,?,?,?,?,?,?);");

				preparedStatement = connection.prepareStatement(sqlFirst);



				preparedStatement.setString(1, title);
				preparedStatement.setString(2, country);
				preparedStatement.setString(3, desc);
				preparedStatement.setInt(4, rentalDuration);
				preparedStatement.setDate(5, madeDate);
				preparedStatement.setInt(6, bookID);
				preparedStatement.setInt(7, authorID);

				preparedStatement.executeUpdate();
				
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
				}				
	}
	
	public int getDVDID() throws SQLException {
		String query = "SELECT MAX(dvd_id) FROM DVD";
		
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
	
	public int getDirectorID() throws SQLException {
		String query = "SELECT MAX(author_id) FROM author_or_director;";
		
		
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
	
	public Date convertToDate(String stringWithDate) throws ParseException {
		java.sql.Date dDate =  new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(stringWithDate).getTime());
		return dDate;
	}

	@Override
	void removeTitle() {
		// TODO Auto-generated method stub
		
	}
	
	public void removeTitle(int mediaID, int dvdID) {
		
		try {
			String sql = "DELETE FROM inventory_item WHERE fk_media_id = ?; " + 
						"DELETE FROM media WHERE fk_dvd_id = ?;\r\n" +
							"DELETE FROM dvd WHERE dvd_id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, mediaID);
			preparedStatement.setInt(2, dvdID);
			preparedStatement.setInt(3, dvdID);

			preparedStatement.executeUpdate();



		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}


	public boolean editDVDTitle(String title, String desc, String country, int rentalDuration, String year, String month,
			String day, int dvdID, int authorID, int mediaID) throws SQLException, ParseException {

		this.dvdID = dvdID;
		this.authorID = authorID;
		this.mediaID = mediaID;
		
		try {

			String releaseDate = dateMaker(year, month, day);
			Date madeDate = convertToDate(releaseDate);
			String sqlFirst = ("UPDATE media " + 
								"SET title = ?, " + 
								"country = ?, " + 
								"description = ?, " + 
								"rental_duration = ?, " + 
								"release_date = ?, " + 
								"fk_dvd_id = ?, " + 
								"fk_author_or_director_id = ? " + 
								"WHERE media_id = ?;");

			preparedStatement = connection.prepareStatement(sqlFirst);

			preparedStatement.setString(1, title);
			preparedStatement.setString(2, country);
			preparedStatement.setString(3, desc);
			preparedStatement.setInt(4, rentalDuration);
			preparedStatement.setDate(5, madeDate);
			preparedStatement.setInt(6, dvdID);
			preparedStatement.setInt(7, authorID);
			preparedStatement.setInt(8, mediaID);

			preparedStatement.executeUpdate();

			return true;
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean editDVDAndDirector(String subject, int ageLimit, int dvdID, String firstName,
			String lastName, int authorID) throws SQLException {
		
		this.authorID = authorID;
		this.dvdID = dvdID;

		try {
			String sql = "UPDATE dvd " + 
					"SET genre = ?, "
					+ "åldersgräns = ?" + 
					"WHERE dvd_id = ?; " + 
					"UPDATE author_or_director " + 
					"SET first_name = ?, " + 
					"last_name = ? " + 
					"WHERE author_id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, subject);
			preparedStatement.setInt(2, ageLimit);
			preparedStatement.setInt(3, dvdID);
			preparedStatement.setString(4, firstName);
			preparedStatement.setString(5, lastName);
			preparedStatement.setInt(6, authorID);

			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	void getMediaInfo() {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	boolean editTitle() throws SQLException, ParseException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getAgeRestriction() {
		return ageRestriction;
	}

	public void setAgeRestriction(int ageRestriction) {
		this.ageRestriction = ageRestriction;
	}

	public String getDirectorLastName() {
		return directorLastName;
	}

	public void setDirectorLastName(String directorLastName) {
		this.directorLastName = directorLastName;
	}

	public String getDirectorFullName() {
		return directorFullName;
	}

	public void setDirectorFullName(String directorFullName) {
		this.directorFullName = directorFullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirectorFirstName() {
		return directorFirstName;
	}

	public void setDirectorFirstName(String directorFirstName) {
		this.directorFirstName = directorFirstName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}



	public int getMediaID() {
		return mediaID;
	}



	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}



	public int getAuthorID() {
		return authorID;
	}



	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}



	public int getDvdID() {
		return dvdID;
	}



	public void setDvdID(int dvdID) {
		this.dvdID = dvdID;
	}
	
	

	
}
