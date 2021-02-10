package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BookLoanReceiptController implements Initializable {
	
	
	public String title, auFirstName, auLastName, isbn, accFullName, email;
	
	public Date loanDate, returnDate;
	

	
	BookSearchResultController bookSearchResCon = new BookSearchResultController();
	
	ReceiptModel recMod = new ReceiptModel();
	
	BookLoanController bookLoanCon = new BookLoanController();
	
	public BookLoanReceiptController(String title, String auFirstName, String auLastName, 
			String isbn, String accFullName, String email, Date loanDate, Date returnDate) {
			this.title = title;
			this.auFirstName = auFirstName;
			this.auLastName = auLastName;
			this.isbn = isbn;
			this.accFullName = accFullName;
			this.email = email;
			this.loanDate = loanDate;
			this.returnDate = returnDate;
	}
	
	@FXML
	private Label titleReceipt;
	@FXML
    private Label authorReceipt;
	@FXML
	private Label isbnReceipt;
	@FXML
	private Label loanDateReceipt;
	@FXML
	private Label returnDateReceipt;
	@FXML
	private Label accountNameReceipt;
	@FXML
	private Label emailReceipt;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		recMod.getReceiptInfo();
		
		titleReceipt.setText(title);
		authorReceipt.setText(auFirstName + " " + auLastName);
		isbnReceipt.setText(isbn);
		loanDateReceipt.setText(loanDate.toString());
		returnDateReceipt.setText(returnDate.toString());
		accountNameReceipt.setText(accFullName);
		emailReceipt.setText(email);
		
	}
	
	public void closeReceipt(ActionEvent event)  {


				
				((Node)event.getSource()).getScene().getWindow().hide();

			 

	
	
	}
}
