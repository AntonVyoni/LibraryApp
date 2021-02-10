package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LateReturnsController implements Initializable{
	
	LoanModel lm = new LoanModel();
    @FXML
    private TableView<LoanModel> table;
    @FXML
    private TableColumn<LoanModel, String> columnTitle;
    @FXML
    private TableColumn<LoanModel, String> columnRentalDuration;
    @FXML
    private TableColumn<LoanModel, String> columnReturnDate;
    @FXML
    private TableColumn<LoanModel, String> columnEmail;
    @FXML
    private TableColumn<LoanModel, String> columnLoanDate;
    @FXML
    private TableColumn<LoanModel, String> columnBarcode;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		columnTitle.setCellValueFactory(new PropertyValueFactory<LoanModel, String>("title"));
		columnRentalDuration.setCellValueFactory(new PropertyValueFactory<LoanModel, String>("rentalDuration"));
		columnReturnDate.setCellValueFactory(new PropertyValueFactory<LoanModel, String>("returnDate"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<LoanModel, String>("email"));
		columnLoanDate.setCellValueFactory(new PropertyValueFactory<LoanModel, String>("loanDate"));
		columnBarcode.setCellValueFactory(new PropertyValueFactory<LoanModel, String>("barcode"));

		lm.getLateReturns();
		table.setItems(lm.printLateReturns());

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

}
