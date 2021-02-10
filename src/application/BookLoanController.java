package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class BookLoanController implements Initializable {
	
	public int mediaID, rentalDuration;
	
	public String title, auFirstName, auLastName, isbn, accFullName, email;
	
	public Date loanDate, returnDate;
	
	LoginModel logMod = new LoginModel();
	LoginController logCon = new LoginController();
	LoanModel loanMod = new LoanModel();
	
	AdminStartPageController adminStart;
	
	
    @FXML
    private Label loanTitle;

    @FXML
    private Label loanAuthorFullName;

    @FXML
    private Label loanDuration;
	

	@FXML
    private Label loanPrompt;

	public BookLoanController() {
		
	}
	
	public BookLoanController(int mediaID, int rentalDuration, String title, String auFirstName, String auLastName, 
						String isbn, String accFullName, String email, Date loanDate, Date returnDate) {
		this.mediaID = mediaID;
		this.rentalDuration = rentalDuration;
		this.title = title;
		this.auFirstName = auFirstName;
		this.auLastName = auLastName;
		this.isbn = isbn;
		this.accFullName = accFullName;
		this.email = email;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}
	
	public void executeBookLoan(ActionEvent event) throws ParseException, InterruptedException {

			try {
				if(!loanMod.loanLimitControl(logCon.getAccountIDs())) {
					loanPrompt.setText("Du har överskridit maxgränsen för lån");
				} else if (loanMod.loanLimitControl(logCon.getAccountIDs())) {
					loanMod.getInventoryID(mediaID);
					loanMod.executeLoanQuery(rentalDuration, logCon.getAccountIDs(), loanMod.getFkInventoryID());
					Thread.sleep(2000);
					loanMod.createReceipt(title, auFirstName, auLastName, isbn, loanDate, returnDate, accFullName, email);
					
					String titles = title;
					String auFirstNames = auFirstName;
					String auLastNames = auLastName;
					String isbns = isbn;
					String accFullNames = accFullName;
					String emails = email;
					Date loanDates = loanDate;
					Date returnDates = returnDate;
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/BookLoanReceipt.fxml"));
					  BookLoanReceiptController bookLoanRecCon = new BookLoanReceiptController(titles, auFirstNames, auLastNames, isbns, accFullNames, emails, loanDates, returnDates);
					  loader.setController(bookLoanRecCon);
					  Parent root = loader.load();         
					  Stage stage = new Stage();
					  stage.setScene(new Scene(root));
					  stage.show();
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loanTitle.setText(title);
		loanAuthorFullName.setText(auFirstName+" "+auLastName);
		loanDuration.setText(Integer.toString(rentalDuration)+ " dagar");
	}
	
	public void goBack(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();		
	}

	public int getMediaID() {
		return mediaID;
	}

	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
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

	public String getAuFirstName() {
		return auFirstName;
	}

	public void setAuFirstName(String auFirstName) {
		this.auFirstName = auFirstName;
	}

	public String getAuLastName() {
		return auLastName;
	}

	public void setAuLastName(String auLastName) {
		this.auLastName = auLastName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

//	public String getAccFirstName() {
//		return accFirstName;
//	}
//
//	public void setAccFirstName(String accFirstName) {
//		this.accFirstName = accFirstName;
//	}
//
//	public String getAccLastName() {
//		return accLastName;
//	}
//
//	public void setAccLastName(String accLastName) {
//		this.accLastName = accLastName;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public String getLoanDate() {
//		return loanDate;
//	}
//
//	public void setLoanDate(String loanDate) {
//		this.loanDate = loanDate;
//	}
//
//	public String getReturnDate() {
//		return returnDate;
//	}
//
//	public void setReturnDate(String returnDate) {
//		this.returnDate = returnDate;
//	}

	public LoginModel getLogMod() {
		return logMod;
	}

	public void setLogMod(LoginModel logMod) {
		this.logMod = logMod;
	}

	public LoginController getLogCon() {
		return logCon;
	}

	public void setLogCon(LoginController logCon) {
		this.logCon = logCon;
	}

	public LoanModel getLoanMod() {
		return loanMod;
	}

	public void setLoanMod(LoanModel loanMod) {
		this.loanMod = loanMod;
	}

	public AdminStartPageController getAdminStart() {
		return adminStart;
	}

	public void setAdminStart(AdminStartPageController adminStart) {
		this.adminStart = adminStart;
	}

	
	
}
