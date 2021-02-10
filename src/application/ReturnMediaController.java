package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ReturnMediaController implements Initializable {
	
	LoanModel lm = new LoanModel();
	
	@FXML
	private Label returnMediaPrompt;
	
	@FXML
    private TextField barcodeTxt;
	
	public void returnMediaExecute(ActionEvent event) {
		if(barcodeTxt.getText().equals("")) {
			returnMediaPrompt.setText("Du måste ange en streckkod");
			} else if (lm.invIdToUpdateLoan(barcodeTxt.getText()) == 0) {
				returnMediaPrompt.setText("Felaktig streckkod");
			} else {
				lm.returnLoan(barcodeTxt.getText());
				int invID = lm.invIdToUpdateLoan(barcodeTxt.getText());
				lm.updateLoan(invID);
				System.out.println(invID);
				returnMediaPrompt.setText("Exemplar återlämnat");
				barcodeTxt.clear();
		}
		
	}
	
	public void goBack(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/adminStartPage.fxml").openStream());
			    Scene scene = new Scene(root); 
			    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    primaryStage.setScene(scene);
			    primaryStage.show();
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
