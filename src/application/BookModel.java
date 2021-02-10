package application;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BookModel extends MediaTitleModel {

	String title, authorFirstName, authorLastName, country, subject, description, refLit, courseLit,
			authorFullName, ISBN;
	
	int mediaID, bookID, authorID, rentalDuration;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet rs;

	public BookModel() {

		connection = SqlConnection.Connector();
		if (connection == null) {
			System.out.println("Connection failed.");
			System.exit(1);
		}

	}
	
	

	

	public BookModel(String title, String authorFirstName, String authorLastName, String country, String subject,
			String description, int rentalDuration, String refLit, String courseLit, String authorFullName, int mediaID,
			int bookID, int authorID, String ISBN) {
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.country = country;
		this.subject = subject;
		this.description = description;
		this.rentalDuration = rentalDuration;
		this.refLit = refLit;
		this.courseLit = courseLit;
		this.authorFullName = authorFullName;
		this.mediaID = mediaID;
		this.bookID = bookID;
		this.authorID = authorID;
		this.ISBN = ISBN;
	}

	
	


	public boolean addBookAndAuthor(String subject, boolean refLit, boolean courseLit, String firstName,
			String lastName, String ISBN) throws SQLException {

		try {
			String sql = "INSERT INTO book(ämnesord, referenslitteratur,kurslitteratur,isbn) " + "VALUES(?,?,?,?);"
					+ "INSERT INTO author_or_director(first_name,last_name) " + "VALUES(?,?);";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, subject);
			preparedStatement.setBoolean(2, refLit);
			preparedStatement.setBoolean(3, courseLit);
			preparedStatement.setString(4, ISBN);
			preparedStatement.setString(5, firstName);
			preparedStatement.setString(6, lastName);

			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addTitle(String title, String desc, String country, int rentalDuration, String year, String month,
			String day) throws SQLException, ParseException {

		try {

			String releaseDate = dateMaker(year, month, day);
			int authorMaxID = getMaxAuthorID();
			System.out.println(authorMaxID);
			int bookMaxID = getMaxBookID();
			System.out.println(bookMaxID);
			Date madeDate = convertToDate(releaseDate);
			String sqlFirst = ("INSERT INTO media(title, country, description,rental_duration,release_date,fk_book_id, fk_author_or_director_id)"
					+ "VALUES (?,?,?,?,?,?,?);");

			preparedStatement = connection.prepareStatement(sqlFirst);

			preparedStatement.setString(1, title);
			preparedStatement.setString(2, country);
			preparedStatement.setString(3, desc);
			preparedStatement.setInt(4, rentalDuration);
			preparedStatement.setDate(5, madeDate);
			preparedStatement.setInt(6, bookMaxID);
			preparedStatement.setInt(7, authorMaxID);

			preparedStatement.executeUpdate();

			return true;
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public int getMaxBookID() throws SQLException {
		String query = "SELECT MAX(book_id) FROM book";

		preparedStatement = connection.prepareStatement(query);

//preparedStatement.setString(1, subject);

		rs = preparedStatement.executeQuery();

		String bookMaxID = null;
		while (rs.next()) {
			bookMaxID = rs.getString(1);
		}
		if (!(bookMaxID == null)) {
			int bID = Integer.parseInt(bookMaxID);
			return bID;
		} else {
			return 0;
		}
	}


	public void removeTitle(int mediaID, int bookID) {
		
		try {
			String sql = "DELETE FROM inventory_item WHERE fk_media_id = ?; " + 
						"DELETE FROM media WHERE fk_book_id = ?;\r\n" +
							"DELETE FROM book WHERE book_id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, mediaID);
			preparedStatement.setInt(2, bookID);
			preparedStatement.setInt(3, bookID);

			preparedStatement.executeUpdate();



		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	@Override
	void getMediaInfo() {
		// TODO Auto-generated method stub

	}
	

	
	
	public boolean editTitle(String title, String desc, String country, int rentalDuration, String year, String month,
			String day, int bookID, int authorID, int mediaID) throws SQLException, ParseException {

		this.bookID = bookID;
		this.authorID = authorID;
		this.mediaID = mediaID;
		
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
								"fk_book_id = ?, " + 
								"fk_author_or_director_id = ? " + 
								"WHERE media_id = ?;");

			preparedStatement = connection.prepareStatement(sqlFirst);

			preparedStatement.setString(1, title);
			preparedStatement.setString(2, country);
			preparedStatement.setString(3, desc);
			preparedStatement.setInt(4, rentalDuration);
			preparedStatement.setDate(5, madeDate);
			preparedStatement.setInt(6, bookID);
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
	
	public boolean editBookAndAuthor(String subject, boolean refLit, boolean courseLit, int bookID, String firstName,
			String lastName, int authorID) throws SQLException {
		
		this.authorID = authorID;
		this.bookID = bookID;

		try {
			String sql = "UPDATE book " + 
					"SET ämnesord = ?, " + 
					"referenslitteratur = ?, " + 
					"kurslitteratur = ? " + 
					"WHERE book_id = ?; " + 
					"UPDATE author_or_director " + 
					"SET first_name = ?, " + 
					"last_name = ? " + 
					"WHERE author_id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, subject);
			preparedStatement.setBoolean(2, refLit);
			preparedStatement.setBoolean(3, courseLit);
			preparedStatement.setInt(4, bookID);
			preparedStatement.setString(5, firstName);
			preparedStatement.setString(6, lastName);
			preparedStatement.setInt(7, authorID);

			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
//	public int getMaxAuthorID() {
//		return authorID;
//	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getMediaID() {
		return mediaID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}

	public String getAuthorFullName() {
		return authorFullName;
	}

	public void setAuthorFullName(String authorFullName) {
		this.authorFullName = authorFullName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
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

	public String getRefLit() {
		return refLit;
	}

	public void setRefLit(String refLit) {
		this.refLit = refLit;
	}

	public String getCourseLit() {
		return courseLit;
	}

	public void setCourseLit(String courseLit) {
		this.courseLit = courseLit;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}
	
	public int getBookID() {
		return bookID;
	}



	public int getAuthorID() {
		return authorID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String author) {
		this.authorFirstName = author;
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





	public String getISBN() {
		return ISBN;
	}





	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}





	@Override
	void removeTitle() {
		// TODO Auto-generated method stub
		
	}





	@Override
	boolean editTitle() throws SQLException, ParseException {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
