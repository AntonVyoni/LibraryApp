package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchResultModel {

//	String titleSearch, authorSearch, countrySearch, subjectSearch, genreSearch, directorSearch = null;

	BookModel books;
	DVDModel dvds;
	PaperModel papers;
	PreparedStatement statementBook;
	PreparedStatement statementDVD;
	PreparedStatement statementPaper;
	PreparedStatement statementInventoryID;
	ResultSet rsBook;
	ResultSet rsDVD;
	ResultSet rsPaper;
	ObservableList<BookModel> bookList = FXCollections.observableArrayList();
	ObservableList<DVDModel> dvdList = FXCollections.observableArrayList();
	ObservableList<PaperModel> paperList = FXCollections.observableArrayList();

	Connection connection;

	public PreparedStatement getBooks(String titleSearch, String authorFirstNameSearch, String authorLastNameSearch,
			String countrySearch, String subjectSearch, String isbnSearch) {
//		this.titleSearch = titleSearch;
//		this.authorSearch = authorSearch;
//		this.countrySearch = countrySearch;
//		this.subjectSearch = subjectSearch;

		Connection connection = SqlConnection.Connector();

		try {
			String sql = "SELECT DISTINCT title, first_name, last_name, country, ämnesord, description, rental_duration, "
						+ "referenslitteratur, kurslitteratur, media_id, book_id, author_id, isbn\r\n" + 
						"FROM inventory_item\r\n" + 
						"JOIN media ON inventory_item.fk_media_id = media.media_id\r\n" + 
						"JOIN author_or_director ON author_or_director.author_id = media.fk_author_or_director_id\r\n" + 
						"JOIN book ON book.book_id = media.fk_book_id "
						+ "WHERE title ILIKE ? AND first_name ILIKE ? AND last_name ILIKE ? AND country ILIKE ? AND ämnesord ILIKE ?"
						+ " AND isbn ILIKE ?";

			statementBook = connection.prepareStatement(sql);
			statementBook.setString(1, "%" + titleSearch + "%");
			statementBook.setString(2, "%" + authorFirstNameSearch + "%");
			statementBook.setString(3, "%" + authorLastNameSearch + "%");
			statementBook.setString(4, "%" + countrySearch + "%");
			statementBook.setString(5, "%" + subjectSearch + "%");
			statementBook.setString(6, "%" + isbnSearch + "%");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statementBook;
	}

	public PreparedStatement getDVD(String titleSearch, String directorFirstNameSearch, String directorLastNameSearch,
			String countrySearch, String genreSearch) {

		Connection connection = SqlConnection.Connector();

		try {
			String sqlDVD = "SELECT DISTINCT title, first_name, last_name, country, genre, description, "
							+ "rental_duration, åldersgräns, media_id, fk_author_or_director_id, dvd_id "
							+ "FROM inventory_item "
							+ "JOIN media ON inventory_item.fk_media_id = media.media_id "
							+ "JOIN author_or_director ON author_or_director.author_id = media.fk_author_or_director_id \r\n"
							+ "JOIN dvd ON dvd.dvd_id = media.fk_dvd_id\r\n"
							+ "WHERE title ILIKE ? AND first_name ILIKE ? AND last_name ILIKE ? AND country ILIKE ? AND genre ILIKE ? AND rented = false";

			statementDVD = connection.prepareStatement(sqlDVD);
			statementDVD.setString(1, "%" + titleSearch + "%");
			statementDVD.setString(2, "%" + directorFirstNameSearch + "%");
			statementDVD.setString(3, "%" + directorLastNameSearch + "%");
			statementDVD.setString(4, "%" + countrySearch + "%");
			statementDVD.setString(5, "%" + genreSearch + "%");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statementDVD;
	}

	public PreparedStatement getPaper(String titleSearch, String numberSearch, String countrySearch, String subjectSearch, String publisherSearch) {

		Connection connection = SqlConnection.Connector();

		try {
			String sqlPaper = "SELECT DISTINCT title, number, country, subject, name, "
					+ "description, rental_duration, publisher_id, media_id, paper_id FROM media \r\n"
					+ "JOIN paper ON paper.paper_id = media.fk_paper_id\r\n"
					+ "JOIN publisher ON media.fk_publisher_id = publisher.publisher_id "
					+ "WHERE title ILIKE ? AND number ILIKE ? AND country ILIKE ? AND subject ILIKE ? AND name ILIKE ?";

			statementPaper = connection.prepareStatement(sqlPaper);
			statementPaper.setString(1, "%" + titleSearch + "%");
			statementPaper.setString(2, "%" + numberSearch + "%");
			statementPaper.setString(3, "%" + countrySearch + "%");
			statementPaper.setString(4, "%" + subjectSearch + "%");
			statementPaper.setString(5, "%" + publisherSearch + "%");

		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statementPaper;
	}

	public ObservableList<BookModel> printBooks() {

		try {
			rsBook = statementBook.executeQuery();

			while (rsBook.next()) {
				bookList.add(new BookModel(rsBook.getString(1), rsBook.getString(2), rsBook.getString(3),
						rsBook.getString(4), rsBook.getString(5), rsBook.getString(6), rsBook.getInt(7),
						rsBook.getString(8), rsBook.getString(9), rsBook.getString(2) + " " + rsBook.getString(3), rsBook.getInt(10),
						rsBook.getInt(11), rsBook.getInt(12), rsBook.getString(13)));
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	public ObservableList<DVDModel> printDVD() {

		try {
			rsDVD = statementDVD.executeQuery();

			while (rsDVD.next()) {
				dvdList.add(new DVDModel(rsDVD.getString(1), rsDVD.getString(2), rsDVD.getString(3), rsDVD.getString(4),
						rsDVD.getString(5), rsDVD.getString(6),
						rsDVD.getString(2) + " " + rsDVD.getString(3), 
						rsDVD.getInt(7), rsDVD.getInt(8), rsDVD.getInt(9), rsDVD.getInt(10), rsDVD.getInt(11)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dvdList;
	}
	
	public ObservableList<PaperModel> printPaper() {

		try {
			rsPaper = statementPaper.executeQuery();

			while (rsPaper.next()) {
				paperList.add(new PaperModel(rsPaper.getString(1), rsPaper.getString(2), rsPaper.getString(3), rsPaper.getString(4),
											rsPaper.getString(5), rsPaper.getString(6), rsPaper.getInt(7), rsPaper.getInt(8), rsPaper.getInt(9), rsPaper.getInt(10)));
			}
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paperList;
	}
	
	public int getInventoryItem(int mediaID) {
		try {
			String sqlInventory = "SELECT inventory_id from inventory_item WHERE fk_media_id = ?";

			statementInventoryID = connection.prepareStatement(sqlInventory);
			statementInventoryID.setInt(1, mediaID);
			
			ResultSet resultSet;
			
			resultSet = statementInventoryID.executeQuery();
			while(resultSet.next()) {
				mediaID = resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mediaID;
	}
	

}
