package application;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddPaperController implements Initializable{
	
	PaperModel paperModel = new PaperModel();
	
	@FXML
	private TextField title;
	@FXML
	private TextField subject;
	@FXML
	private TextField publisherName;
	@FXML
	private TextField country;
	@FXML
	private TextField releaseYear;
	@FXML
	private TextField releaseMonth;
	@FXML
	private TextField releaseDay;
	@FXML
	private TextArea description;
	@FXML
	private TextField number;
	
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
	
	
	public void addPaperTitle(ActionEvent event) throws ParseException {

		try {
			
			
			paperModel.addPaperAndPublisher(subject.getText(),number.getText(),publisherName.getText());
			paperModel.addTitle(title.getText(), description.getText(), country.getText(), 0, releaseYear.getText(), releaseMonth.getText(), releaseDay.getText());
			
			
				
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/AddInventoryItem.fxml").openStream());
	                Scene scene = new Scene(root); 
	                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	                primaryStage.setScene(scene);
	                primaryStage.show();
	                
	              
			}
		  catch (SQLException e) {
				
			e.printStackTrace();
			
		} 
			catch (IOException f) {
			f.printStackTrace();
			
		}
}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
