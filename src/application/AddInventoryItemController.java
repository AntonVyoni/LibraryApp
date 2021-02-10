package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddInventoryItemController implements Initializable{
	
	ItemModel item = new ItemModel();
	
	@FXML
	private TextField barcode;
	
	
	@FXML
	public void addInventoryItems(ActionEvent event) throws SQLException {
			item.addItem(barcode.getText());
			barcode.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void goBacktoChooseMediaType(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/chooseMediaType.fxml").openStream());
			    Scene scene = new Scene(root); 
			    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    primaryStage.setScene(scene);
			    primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void backToMain(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/chooseMediaType.fxml").openStream());
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
