package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class ReserveItemController implements Initializable{
	

    @FXML
    private Label prompt;

	
	int accID, mediaID;
	ReservationModel rm = new ReservationModel();
	LoanModel lm = new LoanModel();

	public ReserveItemController(int accID, int mediaID) { 
		this.accID = accID;
		this.mediaID = mediaID;
	}
	
	public void executeReservation(ActionEvent event) {
		try {
			
			rm.executeReservationQuery(accID, lm.getInventoryID(mediaID));
			prompt.setText("Exemplar reserverat");
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goBack(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public int getAccID() {
		return accID;
	}

	public void setAccID(int accID) {
		this.accID = accID;
	}

	public int getMediaID() {
		return mediaID;
	}

	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}

	public ReservationModel getRm() {
		return rm;
	}

	public void setRm(ReservationModel rm) {
		this.rm = rm;
	}

	public LoanModel getLm() {
		return lm;
	}

	public void setLm(LoanModel lm) {
		this.lm = lm;
	}
	
	
}
