package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanModel {
	public String title, email, barcode, rentalDuration;
	
	public Date loanDates, returnDates;
	
	public String loanDate, returnDate;
	
	public int returnDateMod, fkAccountID, fkInventoryID, returnInvID, lendingLimit, loanCount;
	
	Date loanedDate, returnedDate;

	ObservableList<LoanModel> lateReturnList = FXCollections.observableArrayList();
	
	ObservableList<LoanModel> loanList = FXCollections.observableArrayList();
	
	ArrayList<String> lateReturnEmail = new ArrayList<String>();
	
	ArrayList<String> lateReturnTitle = new ArrayList<String>();
	
	ArrayList<LoanModel> lateReturnEmailAndTitle = new ArrayList<LoanModel>();
	
	public LoanModel(String email) {
		this.email = email;
	}
	
	public LoanModel(String email, String title) {
		this.email = email;
		this.title = title;
	}

	
	public LoanModel(String title, String rentalDuration, Date returnDates, String email, Date loanDates,  String barcode) {
		this.title = title;
		this.email = email;
		this.barcode = barcode;
		this.loanDates = loanDates;
		this.returnDates = returnDates;
		this.rentalDuration = rentalDuration;
	}
	
	public LoanModel(String title, String rentalDuration, String returnDate, String email, String loanDate,  String barcode) {
		this.title = title;
		this.email = email;
		this.barcode = barcode;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
		this.rentalDuration = rentalDuration;
	}
	
	public LoanModel(int returnDateMod, int fkAccountID, int fkInventoryID) {
		this.returnDateMod = returnDateMod;
		this.fkAccountID = fkAccountID;
		this.fkInventoryID = fkInventoryID;
	}

	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet rs;
	public LoanModel() {
		
		connection = SqlConnection.Connector();
		if (connection == null) {
			System.out.println("Connection failed.");
			System.exit(1);
		} 
	}
	
	public Date convertToDate(String stringWithDate) throws ParseException {
		java.sql.Date dDate =  new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(stringWithDate).getTime());
		return dDate;
	}
	
	public ArrayList<String> getLateReturnees() {
	    try {
	    String query = "SELECT DISTINCT email "
	    			+ "FROM account "
	    			+ "JOIN loan "
	    			+ "ON loan.fk_account_id = account.account_id "
	    			+ "WHERE return_date < CURRENT_DATE AND returned = false;";
	  preparedStatement = connection.prepareStatement(query);
	  
	  rs = preparedStatement.executeQuery();

      while (rs.next()) {
    	  
        lateReturnEmail.add(new String(rs.getString(1)));
        
      }

	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    return lateReturnEmail;
	    }
	  
//	  public ArrayList<String> printLateReturnees(){
//	    
//	    try {
//	      rs = preparedStatement.executeQuery();
//
//	      while (rs.next()) {
//	    	  
//	        lateReturnEmail.add(new String(rs.getString(1)));
//	        
//	      }
//	      System.out.println(lateReturnList.toString());
//	    } catch (SQLException e) {
//	      e.printStackTrace();
//	    }
//	    return lateReturnEmail;
//	    
//	  }
	
	
	public PreparedStatement getLateReturns() {
		try {
			String query = "SELECT title,to_char(rental_duration, '99') AS rentDur,to_char(return_date, 'yyyy-mm-dd') AS retDate,email,to_char(loan_date, 'yyyy-mm-dd') AS loanDate,barcode FROM loan\r\n" 
						 + "JOIN account ON loan.fk_account_id = account.account_id\r\n" 
						 + "JOIN inventory_item ON loan.fk_inventory_id = inventory_item.inventory_id\r\n"
						 + "JOIN media ON inventory_item.fk_media_id = media.media_id\r\n" 
						 + "WHERE loan.return_date+media.rental_duration > CURRENT_DATE AND returned = false";
			preparedStatement = connection.prepareStatement(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	public ArrayList<String> getLateTitle(String email) {
		try {
			String query = "SELECT title FROM media\r\n" + 
							"JOIN inventory_item ON media.media_id = inventory_item.fk_media_id\r\n" + 
							"JOIN loan ON loan.fk_inventory_id = inventory_item.inventory_id\r\n" + 
							"JOIN account ON account.account_id = loan.fk_account_id\r\n" + 
							"WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			
			rs = preparedStatement.executeQuery();
			
		      while (rs.next()) {
		    	  
			        lateReturnTitle.add(new String(rs.getString(1)));
			        
			      }
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lateReturnTitle;
	}
	
	public ArrayList<LoanModel> getLateEmailAndTitle() {
		try {
			String query = "SELECT email, title FROM media\r\n" + 
							"JOIN inventory_item ON media.media_id = inventory_item.fk_media_id\r\n" + 
							"JOIN loan ON loan.fk_inventory_id = inventory_item.inventory_id\r\n" + 
							"JOIN account ON account.account_id = loan.fk_account_id\r\n" + 
							"WHERE return_date < CURRENT_DATE AND returned = false;";
		
			preparedStatement = connection.prepareStatement(query);

			
			rs = preparedStatement.executeQuery();
			
		      while (rs.next()) {
		    	  
			        lateReturnEmailAndTitle.add(new LoanModel(rs.getString(1), rs.getString(2)));
			        
			      }
			
//		      for(int i = 0; i < lateReturnEmailAndTitle.size(); i++) {
//		    	  System.out.println(lateReturnEmailAndTitle.get(i));
//		      }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lateReturnEmailAndTitle;
	}
	
	
	public ObservableList<LoanModel> printLateReturns(){
		try {
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
//				System.out.println(rs.getString(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getString(3));
//				System.out.println(rs.getString(4));
//				System.out.println(rs.getString(5));
//				System.out.println(rs.getString(6));
				lateReturnList.add(new LoanModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6)));
				
			}
//			System.out.println(lateReturnList.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lateReturnList;
	}
	
	public PreparedStatement executeLoanQuery(int returnDateMod, int fkAccountID, int fkInventoryID) throws ParseException {
		try {
			String query = "INSERT INTO loan(loan_date, return_date,fk_account_id,fk_inventory_id)\r\n" + 
							"VALUES(CURRENT_DATE,CURRENT_DATE+?,?,?);"
					+ "UPDATE inventory_item "
					+ "SET rented = true "
					+ "WHERE inventory_id = ?;";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, returnDateMod);
			preparedStatement.setInt(2, fkAccountID);
			preparedStatement.setInt(3, fkInventoryID);
			preparedStatement.setInt(4, fkInventoryID);
			preparedStatement.executeUpdate();
			
//			loanedDate = convertToDate(loanDate);
//			returnedDate = convertToDate(returnDate);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	
	
	public int checkLendingLimit(int accountID) {
		
		try {
			String query = "SELECT lending_limit FROM account_role\r\n" + 
							"JOIN account ON fk_role_id = role_id\r\n" + 
							"WHERE account_id = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, accountID);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				lendingLimit = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lendingLimit;
		
		
	}
	
		public int countLoans(int accountID) {
		
		try {
			String query = "SELECT COUNT(loan_id) FROM loan\r\n" + 
					"WHERE fk_account_id = ? AND returned = false";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, accountID);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				loanCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loanCount;
		
		
	}
		
		
	public boolean loanLimitControl(int accountID) {
		int lendLim  = checkLendingLimit(accountID);
		int cLoan = countLoans(accountID);
		
		System.out.println("lednLim: " + lendLim);
		System.out.println("cLoan: " + cLoan);
		if (cLoan >= lendLim) {
			return false;
		} else {
			return true;
		}
	}
	
	public PreparedStatement createReceipt(String title, String auFirstName, String auLastName, String isbn, 
											Date loanDate, Date returnDate, String accFullName, String email) throws ParseException {
		try {
			
			
			
			String query = "INSERT INTO loan_receipt(title,au_first_name,au_last_name,isbn,loan_date,return_date,acc_full_name,email)\r\n" + 
					"VALUES(?,?,?,?,?,?,?,?);";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, auFirstName);
			preparedStatement.setString(3, auLastName);
			preparedStatement.setString(4, isbn);
			preparedStatement.setDate(5, loanDate);
			preparedStatement.setDate(6, returnDate);
			preparedStatement.setString(7, accFullName);
			preparedStatement.setString(8, email);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	
	
//	public ObservableList<LoanModel> printLoanQuery(){
//		try {
//			rs = preparedStatement.executeQuery();
//
//			while (rs.next()) {
//				
//				loanList.add(new LoanModel(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
//				
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return lateReturnList;
//	}
	
	public int getInventoryID(int mediaID) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT inventory_id from inventory_item WHERE fk_media_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, mediaID);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				fkInventoryID = resultSet.getInt(1);
			}
			return fkInventoryID;
			
		} catch(Exception e) {
			return 0;
		}
	}
	
	public Date getLoanDates(int accountID) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT loan_date FROM loan WHERE fk_account_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, accountID);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				loanDates = resultSet.getDate(1);
			}
			System.out.println(accountID);
			return loanDates;
			
			
		} catch(Exception e) {
			System.out.println(accountID);
			return loanDates;
		}
	}
	
	public Date getReturnDates(int accountID) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT return_date FROM loan WHERE fk_account_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, accountID);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				returnDates = resultSet.getDate(1);
			}
			System.out.println(accountID);
			return returnDates;
			
		} catch(Exception e) {
			System.out.println(accountID);
			return returnDates;
		}
	}
	
	public String returnLoan(String barcode) {
		PreparedStatement preparedStatement = null;
		
		
		try {
			String query = "UPDATE inventory_item " + 
							"SET rented = false " + 
							"WHERE barcode = ?; "; 
							
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, barcode);

			
			preparedStatement.executeUpdate();
			
			return barcode;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "SQL exception i returnLoan";
			
		}

	}
	
	public int invIdToUpdateLoan(String barcode) {
		ResultSet resultSet = null;
		
		try {
			String query = "SELECT inventory_id " + 
							"FROM inventory_item " + 
							"WHERE barcode = ?; ";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, barcode);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				returnInvID = resultSet.getInt(1);
			}
			
			if(returnInvID==0) {
				return 0;
			} else {
				return returnInvID;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} 
		
	}
	
	public void updateLoan(int invID) {
		
		try {
			String query = "UPDATE loan " + 
							"SET returned = true " + 
							"WHERE fk_inventory_id = ?;";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, invID);


			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBarcode() {
		return barcode;
	}


	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


//	public String getLoanDate() {
//		return loanDate;
//	}
//
//
//	public void setLoanDate(String loanDate) {
//		this.loanDate = loanDate;
//	}
//
//
//	public String getReturnDate() {
//		return returnDate;
//	}
//
//
//	public void setReturnDate(String returnDate) {
//		this.returnDate = returnDate;
//	}


	public String getRentalDuration() {
		return rentalDuration;
	}


	public void setRentalDuration(String rentalDuration) {
		this.rentalDuration = rentalDuration;
	}


	public ObservableList<LoanModel> getLateReturnList() {
		return lateReturnList;
	}


	public void setLateReturnList(ObservableList<LoanModel> lateReturnList) {
		this.lateReturnList = lateReturnList;
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
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

	public int getReturnDateMod() {
		return returnDateMod;
	}

	public void setReturnDateMod(int returnDateMod) {
		this.returnDateMod = returnDateMod;
	}

	public int getFkAccountID() {
		return fkAccountID;
	}

	public void setFkAccountID(int fkAccountID) {
		this.fkAccountID = fkAccountID;
	}

	public int getFkInventoryID() {
		return fkInventoryID;
	}

	public void setFkInventoryID(int fkInventoryID) {
		this.fkInventoryID = fkInventoryID;
	}
	
	
	
}
