package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReceiptModel {
	
	PreparedStatement preparedStatement;
	ResultSet rs;
	
	ObservableList<ReceiptModel> receiptList = FXCollections.observableArrayList();
	ObservableList<ReceiptModel> receiptAuthorList = FXCollections.observableArrayList();

	String title, authorFirstName, authorLastName, ISBN, accountFullName, email, loanDate, returnDate;
	
	public ReceiptModel() {
		
	}
	
	public ReceiptModel(String title, String authorFirstName, String authorLastName, String ISBN, 
						String loanDate, String returnDate, String accountFullName, String email) {
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.ISBN = ISBN;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
		this.accountFullName = accountFullName;
		this.email = email;
	}
	
	
	public ObservableList<ReceiptModel> getReceiptInfo() {

		Connection connection = SqlConnection.Connector();

		try {
			String sql = "SELECT title, au_first_name, au_last_name, isbn, to_char(loan_date, 'yyyy-mm-dd'), "
					+ "to_char(return_date, 'yyyy-mm-dd'), acc_full_name, email"
					+ "FROM loan_receipt "
					+ "ORDER BY receipt_id DESC "
					+ "LIMIT 1;";

			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				receiptList.add(new ReceiptModel(rs.getString(1), rs.getString(2), rs.getString(3), 
												rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receiptList;
		
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public ObservableList<ReceiptModel> getReceiptList() {
		return receiptList;
	}

	public void setReceiptList(ObservableList<ReceiptModel> receiptList) {
		this.receiptList = receiptList;
	}

	public ObservableList<ReceiptModel> getReceiptAuthorList() {
		return receiptAuthorList;
	}

	public void setReceiptAuthorList(ObservableList<ReceiptModel> receiptAuthorList) {
		this.receiptAuthorList = receiptAuthorList;
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

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAccountFullName() {
		return accountFullName;
	}

	public void setAccountFullName(String accountFullName) {
		this.accountFullName = accountFullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	



	
	
}
