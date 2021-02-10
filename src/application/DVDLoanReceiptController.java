package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DVDLoanReceiptController implements Initializable {
	
public String title, auFirstName, auLastName, isbn, accFullName, email;
	
	public Date loanDate, returnDate;
	
	DVDSearchResultController dvdSearchResCon = new DVDSearchResultController();
	
	ReceiptModel recMod = new ReceiptModel();
	
	BookLoanController bookLoanCon = new BookLoanController();
	
	public DVDLoanReceiptController(String title, String auFirstName, String auLastName, 
			String accFullName, String email, Date loanDate, Date returnDate) {
			this.title = title;
			this.auFirstName = auFirstName;
			this.auLastName = auLastName;
			this.accFullName = accFullName;
			this.email = email;
			this.loanDate = loanDate;
			this.returnDate = returnDate;
	}
	
	@FXML
	private Label titleReceipt;
	@FXML
	private Label loanDateReceipt;
	@FXML
	private Label returnDateReceipt;
	@FXML
	private Label accountNameReceipt;
	@FXML
	private Label emailReceipt;
	@FXML
	private Label authorReceipt;

	// Event Listener on Button.onAction
	@FXML
	public void closeReceipt(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titleReceipt.setText(title);
		authorReceipt.setText(auFirstName + " " + auLastName);
		loanDateReceipt.setText(loanDate.toString());
		returnDateReceipt.setText(returnDate.toString());
		accountNameReceipt.setText(accFullName);
		emailReceipt.setText(email);
		
	}
}
