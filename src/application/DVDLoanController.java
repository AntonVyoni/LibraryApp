package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DVDLoanController implements Initializable {
	
	public int mediaID, rentalDuration;
	
	public String title, diFirstName, diLastName, isbn, accFullName, email;
	
	public Date loanDate, returnDate;
	
	LoginModel logMod = new LoginModel();
	LoginController logCon = new LoginController();
	LoanModel loanMod = new LoanModel();
	
	public DVDLoanController(int mediaID, int rentalDuration, String title, String diFirstName, String diLastName, 
			String accFullName, String email, Date loanDate, Date returnDate) {
		this.mediaID = mediaID;
		this.rentalDuration = rentalDuration;
		this.title = title;
		this.diFirstName = diFirstName;
		this.diLastName = diLastName;
		this.accFullName = accFullName;
		this.email = email;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}
	
	@FXML
	private Label loanPrompt;
	@FXML
	private Label loanTitle;
	@FXML
	private Label loanAuthorFullName;
	@FXML
	private Label loanDuration;

	// Event Listener on Button.onAction
	@FXML
	public void executeBookLoan(ActionEvent event) {
		try {
			if(!loanMod.loanLimitControl(logCon.getAccountIDs())) {
				loanPrompt.setText("Du har överskridit maxgränsen för lån");
			} else if (loanMod.loanLimitControl(logCon.getAccountIDs())) {
				loanMod.getInventoryID(mediaID);
				loanMod.executeLoanQuery(rentalDuration, logCon.getAccountIDs(), loanMod.getFkInventoryID());
				loanMod.createReceipt(title, diFirstName, diLastName, isbn, loanDate, returnDate, accFullName, email);
				
				String titles = title;
				String diFirstNames = diFirstName;
				String diLastNames = diLastName;
				String accFullNames = accFullName;
				String emails = email;
				Date loanDates = loanDate;
				Date returnDates = returnDate;
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/DVDLoanReceipt.fxml"));
				  DVDLoanReceiptController dvdLoanRecCon = new DVDLoanReceiptController(titles, diFirstNames, diLastNames, accFullNames, emails, loanDates, returnDates);
				  loader.setController(dvdLoanRecCon);
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
	
	public void goBack(ActionEvent event) {
			((Node)event.getSource()).getScene().getWindow().hide();		
		}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loanTitle.setText(title);
		loanAuthorFullName.setText(diFirstName+" "+diLastName);
		loanDuration.setText(Integer.toString(rentalDuration)+" dagar");
		
	}
}
